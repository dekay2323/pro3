package com.pro3

import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.Specification

import javax.sound.sampled.Line

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(RfqService)
@Mock([MaterialRequest, Rfq, Quote, QuoteStatus, QuoteLineItem, RequestStatus])
class RfqServiceSpec extends Specification {
    def setup() {
    }

    def cleanup() {
    }

    def "createRfqAndQuotes() should create a simple rfq with quote attached"() {
        MaterialRequest materialRequest = new MaterialRequest()
        materialRequest.project = Mock(Project)
        materialRequest.status = new RequestStatus(name: RequestStatus.RequestStatusEnum.APPROVED_TO_PLAN.name())
        QuoteStatus quoteStatus = new QuoteStatus(name: QuoteStatus.QuoteStatusEnum.START.name())
        quoteStatus.save()
        Vendor vendor = new Vendor()
        materialRequest.bidders = [vendor]
        LineItem lineItem = new LineItem(code: "code")
        materialRequest.lineItems = [lineItem]
        RequestStatus requestStatus = new RequestStatus(name: RequestStatus.RequestStatusEnum.RFQ_ISSUED.name())
        requestStatus.save()
        when:
        Rfq rfq = service.createRfqAndQuotes(materialRequest, quoteStatus)
        then:
        rfq != null
        rfq.quotes?.size() == 1
        rfq.quotes[0]?.status?.name == QuoteStatus.QuoteStatusEnum.START.name()
        rfq.quotes[0]?.quoteLineItems?.size() == 1
        rfq.quotes[0]?.quoteLineItems[0].lineItem == lineItem
    }
}