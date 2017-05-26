package com.pro3

import com.pro3.list.QuoteStatus
import com.pro3.main.MaterialRequest
import com.pro3.main.Quote
import com.pro3.main.Rfq
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
        def obj = new Rfq()
        then:
        obj.validate() == false
        obj.hasErrors() == true
        obj.errors.errorCount == 2
        obj.errors['quotes']?.objectName == 'com.pro3.main.Rfq'
        obj.errors['materialRequest']?.objectName == 'com.pro3.main.Rfq'
    }


    def "can save minimal object"() {
        when:
        def obj = new Rfq()
        obj.materialRequest = Mock(MaterialRequest)
        obj.quotes = [Mock(Quote)]
        then:
        obj.validate() == true
        obj.hasErrors() == false
        obj.errors.errorCount == 0
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