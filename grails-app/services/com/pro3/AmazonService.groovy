package com.pro3

import com.amazonaws.services.s3.model.ObjectListing
import com.amazonaws.services.s3.model.S3ObjectSummary
import grails.plugin.awssdk.s3.AmazonS3Service
import org.springframework.web.multipart.MultipartFile

class AmazonService extends AmazonS3Service {
    final String BUCKET_NAME = 'p3app'

    ObjectListing listFilesForAccount(String accountName) {
        assert accountName
        this.listObjects(BUCKET_NAME, "${accountName}/")
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
    
    boolean removeFileForAccount(String accountName, String fileName) {
        assert accountName
        this.deleteFile(BUCKET_NAME, "${accountName}/${fileName}")
    }
}
