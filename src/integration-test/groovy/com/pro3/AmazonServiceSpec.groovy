package com.pro3

import com.amazonaws.services.s3.model.ObjectListing
import grails.test.mixin.integration.Integration
import grails.transaction.*

import spock.lang.*
import geb.spock.*

/**
 * See http://www.gebish.org/manual/current/ for more instructions
 */
// @TODO : Test is very brittle
@Integration
class AmazonServiceSpec extends GebSpec {
    AmazonService amazonService
    
    def setup() {
    }

    def cleanup() {
    }

    def "testing create file then delete file"() {
        setup:
        File file = new File("test-file.txt")
        file << "Test upload\n"
        String account = 'test-account'

        when:
        def list = amazonService.listFilesForAccount(account)
        
        then:
        list.size() == 1

        when:
        String url = amazonService.storeFileForAccount(account, file)
        
        then:
        url == "https://s3-us-west-2.amazonaws.com/p3app/test-account/test-file.txt"
        
        when:
        list = amazonService.listFilesForAccount(account)
        
        then:
        list.size() == 2
        list[0] == 'https://s3-us-west-2.amazonaws.com/p3app/test-account/'
        list[1] == 'https://s3-us-west-2.amazonaws.com/p3app/test-account/test-file.txt'
        
        when:
        def result = amazonService.removeFileForAccount(account, file.name.toString())
        
        then:
        result == true

        when:
        list = amazonService.listFilesForAccount(account)

        then:
        list.size() == 1
    }
}
