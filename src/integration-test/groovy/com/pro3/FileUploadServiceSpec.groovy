package com.pro3

import com.pro3.service.FileUploadService
import grails.test.mixin.integration.Integration
import geb.spock.*

/**
 * See http://www.gebish.org/manual/current/ for more instructions
 */
@Integration
class FileUploadServiceSpec extends GebSpec {
    FileUploadService fileUploadService
    final static String account = 'test-account'

    def setup() {
        // Delete all the files
        def list = fileUploadService.listFilesForAccount(account)
        list.forEach { file ->
            boolean deleted = fileUploadService.removeFileForAccount(account, file?.filename)
            assert deleted
        }
    }

    def cleanup() {
    }

    def "testing create file then delete file"() {
        setup:
        File file = new File("test-file.txt")
        file << "Test upload\n"

        when:
        def list = fileUploadService.listFilesForAccount(account)
        
        then:
        list.size() == 0

        when:
        String url = fileUploadService.storeFileForAccount(account, file)

        then:
        url == "https://s3-us-west-2.amazonaws.com/procurableapp.files/test-account/test-file.txt"
        
        when:
        list = fileUploadService.listFilesForAccount(account)
        
        then:
        list.size() == 1
        list[0]?.url == 'https://s3-us-west-2.amazonaws.com/procurableapp.files/test-account/test-file.txt'
        list[0]?.key == 'test-account/test-file.txt'
        list[0]?.filename == 'test-file.txt'
        
        when:
        def result = fileUploadService.removeFileForAccount(account, file.name.toString())
        
        then:
        result == true

        when:
        list = fileUploadService.listFilesForAccount(account)

        then:
        list.size() == 0
    }
}
