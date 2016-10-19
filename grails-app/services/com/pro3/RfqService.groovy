package com.pro3

import com.pro3.embedded.MaterialRequest
import com.pro3.embedded.Quote
import com.pro3.embedded.QuoteLineItem
import com.pro3.selectors.RequestStatus
import grails.transaction.Transactional

@Transactional
class RfqService {

    @Transactional
    def Rfq createRfqAndQuotes(def materialRequestId) {
        log.debug("createRfqs ${materialRequestId}")
        MaterialRequest materialRequest = MaterialRequest.get(materialRequestId)

        def rfq = new Rfq(materialRequest: materialRequest)
        if (materialRequest?.bidders?.isEmpty()) {
            throw new Pro3Exception('No bidders')
        }

        materialRequest?.bidders?.each {vendor->
            Quote quote = new Quote(rfq: rfq, vendor: vendor)
            rfq.name = "${materialRequest?.reqNumber} - ${materialRequest?.description}"
            rfq.addToQuotes(quote)
            rfq.save failOnError: true
            quote.save failOnError: true
            if (!materialRequest?.lineItems) {
                throw new Pro3Exception('No line items')
            }
            materialRequest?.lineItems?.each {lineItem->
                QuoteLineItem quoteLineItem = new QuoteLineItem(lineItem: lineItem, quote:quote)
                quoteLineItem.save failOnError: true
                quote.addToQuoteLineItems(quoteLineItem)
            }
        }
        materialRequest.setStatus(RequestStatus.findByName('RFQ Issued'))
        materialRequest.save failOnError: true
        rfq
    }
}
