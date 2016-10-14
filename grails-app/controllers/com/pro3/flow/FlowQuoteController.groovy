package com.pro3.flow

import com.pro3.Quote
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class FlowQuoteController {

    def editQuote() {
        log.debug "editQuote() ${params}"

        respond Quote.get(params?.id)
    }

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

}
