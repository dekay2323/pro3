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
        ObjectListing list = amazonService.listFilesForAccount(account)
        
        then:
        list.getPrefix() == 'test-account/'
        list.getObjectSummaries().size() == 1

        when:
        String url = amazonService.storeFileForAccount(account, file)
        
        then:
        url == "https://s3-us-west-2.amazonaws.com/p3app/test-account/test-file.txt"
        
        when:
        list = amazonService.listFilesForAccount(account)
        
        then:
        list.getObjectSummaries().size() == 2
        list.getObjectSummaries()[0].getKey() == 'test-account/'
        list.getObjectSummaries()[1].getKey() == 'test-account/test-file.txt'
        
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
