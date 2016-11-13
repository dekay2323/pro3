package com.pro3

import grails.test.mixin.Mock
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(DomainClassUnitTestMixin)
@Mock([Rfq])
class RfqSpec extends Specification {
    def setup() {
    }

    def cleanup() {
    }

    def "constraint errors"() {
        when:
        def validateable = new Rfq()
        then:
        validateable.validate() == false
        validateable.hasErrors() == true
        validateable.errors.errorCount == 2
        validateable.errors['quotes']?.objectName == 'com.pro3.Rfq'
        validateable.errors['materialRequest']?.objectName == 'com.pro3.Rfq'
    }


    def "can save minimal object"() {
        when:
        def validateable = new Rfq()
        validateable.materialRequest = Mock(MaterialRequest)
        validateable.quotes = [Mock(Quote)]
        then:
        validateable.validate() == true
        validateable.hasErrors() == false
        validateable.errors.errorCount == 0
    }

    def "getBidsReceived() return number of recieved bids"() {
        setup:
        def rfq = new Rfq()
        when:
        rfq.quotes = []
        then:
        rfq.getBidsReceived() == 0
        when:
        rfq.quotes = [new Quote(status: new QuoteStatus(name: QuoteStatus.QuoteStatusEnum.START.name()))]
        then:
        rfq.getBidsReceived() == 0
        when:
        rfq.quotes = [new Quote(status: new QuoteStatus(name: QuoteStatus.QuoteStatusEnum.BID.name()))]
        then:
        rfq.getBidsReceived() == 1
        when:
        rfq.quotes = [
                new Quote(status: new QuoteStatus(name: QuoteStatus.QuoteStatusEnum.BID.name())),
                new Quote(status: new QuoteStatus(name: QuoteStatus.QuoteStatusEnum.BID.name())),
                new Quote(status: new QuoteStatus(name: QuoteStatus.QuoteStatusEnum.START.name()))]
        then:
        rfq.getBidsReceived() == 2
    }

    def "getBidsOut() should return number total number of quotes"() {
        setup:
        def rfq = new Rfq()
        when:
        rfq.quotes = []
        then:
        rfq.getBidsOut() == 0
        when:
        rfq.quotes = [new Quote(status: new QuoteStatus(name: QuoteStatus.QuoteStatusEnum.START.name()))]
        then:
        rfq.getBidsOut() == 1
        when:
        rfq.quotes = [new Quote(status: new QuoteStatus(name: QuoteStatus.QuoteStatusEnum.BID.name()))]
        then:
        rfq.getBidsOut() == 1
        when:
        rfq.quotes = [
                new Quote(status: new QuoteStatus(name: QuoteStatus.QuoteStatusEnum.BID.name())),
                new Quote(status: new QuoteStatus(name: QuoteStatus.QuoteStatusEnum.BID.name())),
                new Quote(status: new QuoteStatus(name: QuoteStatus.QuoteStatusEnum.START.name()))]
        then:
        rfq.getBidsOut() == 3
    }
}