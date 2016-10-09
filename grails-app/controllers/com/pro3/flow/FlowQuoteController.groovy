package com.pro3.flow

import com.pro3.Constants
import com.pro3.Quote
import com.pro3.QuoteLineItem
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured([Constants.ROLE_ADMIN, Constants.ROLE_USER])
@Transactional(readOnly = true)
class FlowQuoteController {

    def editQuote() {
        log.debug "editQuote() ${params}"

        respond Quote.get(params?.id)
    }

    def saveQuote(Quote quote) {
        log.debug "saveQuote() ${params}"

        quote.quoteLineItems.each {qLineItem->
            String price = params.get("price-" + qLineItem.id)
            if (price.number) {
                qLineItem.price = new BigDecimal(price)
                qLineItem.save failOnError: true
            }
        }

        redirect action: 'editQuote', id: quote?.id
    }

}
