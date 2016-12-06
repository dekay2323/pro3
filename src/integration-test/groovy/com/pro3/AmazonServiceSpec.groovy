package com.pro3

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

    def "testing p3app bucket can be found"() {
        expect:
        true == amazonService.listAllBuckets().size() >= 1
    }
    
    def "testing p3app bucket has some files"() {
        expect:
        true == amazonService.listFiles().size() > 0
    }
}
