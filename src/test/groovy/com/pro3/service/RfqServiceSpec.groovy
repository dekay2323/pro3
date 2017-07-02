package com.pro3.service

import com.pro3.Pro3Exception
import com.pro3.domain.aux.LineItem
import com.pro3.domain.aux.QuoteLineItem
import com.pro3.domain.list.QuoteStatus
import com.pro3.domain.list.RequestStatus
import com.pro3.domain.main.MaterialRequest
import com.pro3.domain.main.Project
import com.pro3.domain.main.Quote
import com.pro3.domain.main.Rfq
import com.pro3.domain.user.User
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
        MaterialRequest materialRequest = new MaterialRequest(name: 'mr')
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
        User user = new User()
        when:
        Rfq rfq = service.createRfqAndQuotes(user, materialRequest, quoteStatus)
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
        User user = new User()
        when:
        service.createRfqAndQuotes(user, materialRequest, quoteStatus)
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
        User user = new User()
        when:
        service.createRfqAndQuotes(user, materialRequest, quoteStatus)
        then:
        Pro3Exception ex = thrown()
        ex.getMessage() == 'No line items'

    }
}