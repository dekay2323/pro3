package com.pro3.flow

import com.pro3.MaterialRequest
import com.pro3.Quote
import com.pro3.QuoteStatus
import com.pro3.RequestStatus
import com.pro3.Rfq
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class FlowBidController {

    def index() {
        log.debug("flowBid() ${params}")

        Rfq rfq = Rfq.get(params.id)

        respond rfq
    }
    
    @Transactional
    def selectVendor() {
        log.debug("selectVendor() ${params}")
        Quote quote = Quote.get(params?.quoteId)
        
        quote.rfq.quotes.find {it.status == QuoteStatus.findByName(QuoteStatus.QuoteStatusEnum.BID)}
                .each { it.status = QuoteStatus.findByName(QuoteStatus.QuoteStatusEnum.BID_OVER) 
            it.save(failOnError: true, flush: true)
        }

        quote.status = QuoteStatus.findByName(QuoteStatus.QuoteStatusEnum.PO_ISSUED)
        quote.quoteLineItems.each {qLineItem->
            boolean inPO = params.get("inPO-" + qLineItem.id) ?: false
            qLineItem.inPO = inPO
        }
        quote.save(failOnError: true, flush: true)
        
        MaterialRequest mr = quote.rfq.materialRequest
        mr.status = RequestStatus.findByName(RequestStatus.RequestStatusEnum.PO_ISSUED)
        mr.save(failOnError: true, flush: true)
        
    }
}
