package com.pro3.flow

import com.pro3.OptionLineItem
import com.pro3.Quote
import com.pro3.QuoteLineItem
import com.pro3.QuoteStatus
import com.pro3.RequestStatus
import com.pro3.Rfq
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_VENDOR', 'ROLE_USER'])
@Transactional(readOnly = true)
// @TODO : Too much logic in services
class FlowQuoteController {
    def authUserService
    
    def editQuote() {
        log.debug "editQuote() ${params}"

        respond Quote.get(params?.id)
    }

    @Transactional
    def saveQuote(Quote quote) {
        log.debug "saveQuote() ${params}"

        def user = authUserService.obtainCurrentUser()
        
        quote.quoteLineItems.each {qLineItem->
            def price = params.get("price-" + qLineItem.id)
            BigDecimal bPrice = price ? new BigDecimal(price) : 0
            qLineItem.price = new BigDecimal(bPrice)

            def leadTime = params.get("leadTime-" + qLineItem.id)
            qLineItem.leadTime = Integer.valueOf(leadTime)

            def checkOff = params.get("checkOff-" + qLineItem.id) ?: false
            qLineItem.checkOff = checkOff

        }
        quote.changedBy = user
        
        if (params?.bidding) {
            quote.bidding = true
            //quote.status = new QuoteStatus(name: QuoteStatus.QuoteStatusEnum.INTENTION_TO_BID.name())
        } else {
            if (params?.bidding != null) {
                quote.bidding = false
                //quote.status = new QuoteStatus(name: QuoteStatus.QuoteStatusEnum.NOT_BIDDING.name())
            }
        }
        quote.save failOnError: true

        if (user.equals(quote.vendor)) {
            redirect url: '/'
        } else {
            redirect controller: 'listAllQuotes', action: 'index'
        }
    }

    @Transactional
    def createBid(Quote quote) {
        log.debug "createBid() ${params}"

        quote.status = QuoteStatus.findByName(QuoteStatus.QuoteStatusEnum.BID)
        quote.save failOnError: true
        // @TODO: Bids out and recieved seems odd
        Rfq rfq = quote?.rfq
        if (rfq.bidsOut <= rfq.bidsReceived) {
            rfq?.materialRequest?.status = RequestStatus.findByName(RequestStatus.RequestStatusEnum.BIDS_RECIEVED)
        }


        redirect url: '/'
    }

    def createOptionLineItem() {
        log.debug("createOptionLineItem() ${params}")
        params.quote = params?.quoteId
        respond new OptionLineItem(params)
    }

    @Transactional
    def saveOptionLineItem(OptionLineItem optionLineItem) {
        log.debug "saveOptionLineItem() ${optionLineItem}"
        if (optionLineItem == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (optionLineItem.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond optionLineItem.errors, view:'createOptionLineItem'
            return
        }

        optionLineItem.save flush:true
        redirect action: 'editQuote', id: optionLineItem?.quote?.id
    }

}
