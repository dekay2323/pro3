package com.pro3

import com.pro3.aux.LineItem
import com.pro3.aux.QuoteLineItem
import com.pro3.list.QuoteStatus
import com.pro3.list.RequestStatus
import com.pro3.main.MaterialRequest
import com.pro3.main.Project
import com.pro3.main.Quote
import com.pro3.main.Rfq
import com.pro3.user.User
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

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
        setup:
        MaterialRequest materialRequest = new MaterialRequest()
        materialRequest.project = Mock(Project)
        materialRequest.status = new RequestStatus(name: RequestStatus.RequestStatusEnum.APPROVED_TO_PLAN.name())
        QuoteStatus quoteStatus = new QuoteStatus(name: QuoteStatus.QuoteStatusEnum.START.name())
        quoteStatus.save()
        User vendor = new User()
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

    def "createRfqAndQuotes() should throw exception if no bidder"() {
        setup:
        MaterialRequest materialRequest = new MaterialRequest()
        materialRequest.project = Mock(Project)
        materialRequest.status = new RequestStatus(name: RequestStatus.RequestStatusEnum.APPROVED_TO_PLAN.name())
        QuoteStatus quoteStatus = new QuoteStatus(name: QuoteStatus.QuoteStatusEnum.START.name())
        RequestStatus requestStatus = new RequestStatus(name: RequestStatus.RequestStatusEnum.RFQ_ISSUED.name())
        when:
        Rfq rfq = service.createRfqAndQuotes(materialRequest, quoteStatus)
        then:
        Pro3Exception ex = thrown()
        ex.getMessage() == 'No bidders'

    }

    def "createRfqAndQuotes() should throw exception if no line items"() {
        setup:
        MaterialRequest materialRequest = new MaterialRequest()
        materialRequest.project = Mock(Project)
        materialRequest.status = new RequestStatus(name: RequestStatus.RequestStatusEnum.APPROVED_TO_PLAN.name())
        QuoteStatus quoteStatus = new QuoteStatus(name: QuoteStatus.QuoteStatusEnum.START.name())
        User vendor = new User()
        materialRequest.bidders = [vendor]
        RequestStatus requestStatus = new RequestStatus(name: RequestStatus.RequestStatusEnum.RFQ_ISSUED.name())
        when:
        Rfq rfq = service.createRfqAndQuotes(materialRequest, quoteStatus)
        then:
        Pro3Exception ex = thrown()
        ex.getMessage() == 'No line items'

    }
}