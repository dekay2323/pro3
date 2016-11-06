package com.pro3.flow

import com.pro3.Quote
import com.pro3.QuoteStatus
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class FlowQuoteController {

    def editQuote() {
        log.debug "editQuote() ${params}"

        respond Quote.get(params?.id)
    }

    @Transactional
    def saveQuote(Quote quote) {
        log.debug "saveQuote() ${params}"

        quote.quoteLineItems.each {qLineItem->
            String price = params.get("price-" + qLineItem.id)
            BigDecimal bPrice = price ? new BigDecimal(price) : 0
            qLineItem.price = new BigDecimal(bPrice)
            
            def date = params.get("shipDate-" + qLineItem.id)
            qLineItem.shipDate = date
        }
        quote.save failOnError: true

        redirect controller: 'listQuote', action: 'index'
    }

    @Transactional
    def createBid(Quote quote) {
        log.debug "createBid() ${params}"

        quote.status = QuoteStatus.findByName(QuoteStatus.QuoteStatusEnum.BID)

        quote.save failOnError: true

        redirect controller: 'listQuote', action: 'index'
    }
}
