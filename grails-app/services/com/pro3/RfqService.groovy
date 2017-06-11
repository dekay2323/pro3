package com.pro3

import com.pro3.aux.QuoteLineItem
import com.pro3.list.QuoteStatus
import com.pro3.list.RequestStatus
import com.pro3.main.MaterialRequest
import com.pro3.main.Quote
import com.pro3.main.Rfq
import com.pro3.user.User
import grails.transaction.Transactional

@Transactional
class RfqService {
    
    // @TODO why do we have to pass quotestatus in
    @Transactional
    def Rfq createRfqAndQuotes(User user, MaterialRequest materialRequest, QuoteStatus quoteStatus) {
        log.debug("createRfqs() ${materialRequest}")

        Rfq rfq = new Rfq(materialRequest: materialRequest)
        if (!materialRequest?.bidders) {
            throw new Pro3Exception('No bidders')
        }
        
        materialRequest?.bidders?.each {vendor->
            Quote quote = new Quote(rfq: rfq, vendor: vendor, status: quoteStatus, changedBy: user)
            rfq.name = "${materialRequest?.code} - ${materialRequest?.name}"
            rfq.addToQuotes(quote)
            rfq.save failOnError: true
            quote.save failOnError: true
            if (!materialRequest?.lineItems) {
                throw new Pro3Exception('No line items')
            }
            materialRequest?.lineItems?.each {lineItem->
                QuoteLineItem quoteLineItem = new QuoteLineItem(lineItem: lineItem, quote:quote, code: lineItem.code)
                quoteLineItem.save failOnError: true
                quote.addToQuoteLineItems(quoteLineItem)
            }
        }
        materialRequest.setStatus(RequestStatus.findByName(RequestStatus.RequestStatusEnum.RFQ_ISSUED))
        materialRequest.save failOnError: true
        rfq
    }
}
