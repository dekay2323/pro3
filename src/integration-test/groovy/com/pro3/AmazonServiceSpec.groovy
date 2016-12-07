package com.pro3

import com.amazonaws.services.s3.model.ObjectListing
import grails.test.mixin.integration.Integration
import grails.transaction.*

import spock.lang.*
import geb.spock.*

/**
 * See http://www.gebish.org/manual/current/ for more instructions
 */
@Integration
class AmazonServiceSpec extends GebSpec {
    def amazonService
    
    def setup() {
    }

    def cleanup() {
    }

    def "testing create file"() {
        setup:
        File file = new File("test-file.txt")
        file << "Test upload\n"
        String account = 'test-account'
        
        when:
        String url = amazonService.storeFileForAccount(account, file)
        
        then:
        url == "https://s3-us-west-2.amazonaws.com/p3app/test-account/test-file.txt"
        
        when:
        def list = amazonService.listFilesForAccount(account)
        
        then:
        list.size() == 2
        list[0] == 'test-account/'
        list[1] == 'test-account/test-file.txt'
    }
}
