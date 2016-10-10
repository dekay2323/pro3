package com.pro3.flow

import com.pro3.Constants
import com.pro3.Quote
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

import java.text.DateFormat

@Secured([Constants.ROLE_ADMIN, Constants.ROLE_USER])
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

//            String date = params.get("shipDate-" + qLineItem.id)
//            Date dDate = date ? Date.parse("yyyy-MM-dd", date): null
            def date = params.get("shipDate-" + qLineItem.id)
            qLineItem.shipDate = date
        }
        quote.save failOnError: true

        redirect controller: 'listQuote', action: 'index'
    }

}
