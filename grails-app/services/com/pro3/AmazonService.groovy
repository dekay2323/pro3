package com.pro3

import com.amazonaws.services.s3.model.ObjectListing
import com.amazonaws.services.s3.model.S3ObjectSummary
import grails.plugin.awssdk.s3.AmazonS3Service
import grails.transaction.Transactional
import org.springframework.web.multipart.MultipartFile

@Transactional(readOnly = true)
class AmazonService extends AmazonS3Service {
    final String BUCKET_NAME = 'p3app'

    List<FileCommand> listFilesForAccount(String accountName) {
        assert accountName
        ObjectListing objectListing = this.listObjects(BUCKET_NAME, "${accountName}/")
        objectListing.getObjectSummaries().collect {S3ObjectSummary obj->
            new FileCommand(
                    url: "https://s3-us-west-2.amazonaws.com/${objectListing.getBucketName()}/${obj.getKey()}",
                    key: obj.getKey(),
                    filename: filenameOf(obj.getKey()),
                    size: obj.getSize(),
                    lastModified: obj.getLastModified()
            )
            
        }
    }
    
    def filenameOf = { String str->
        def match = str =~ /.*\/(.*?)$/
        if (match) {
            return match[0][1]
        } else {
            return "UNKNOWN";
        }
    }
    
    String storeFileForAccount(String accountName, File file) {
        assert file
        assert accountName
        this.storeFile(BUCKET_NAME, "${accountName}/${file.name}", file)
    }

    String storeMultiPartFileForAccount(String accountName, String fileName, MultipartFile file) {
        assert file
        assert accountName
        this.storeMultipartFile(BUCKET_NAME, "${accountName}/${fileName}", file)
    }
    
    boolean removeFileForAccount(String fileName) {
        assert fileName
        this.deleteFile(BUCKET_NAME, "${fileName}")
    }
}
