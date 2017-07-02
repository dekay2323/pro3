package com.pro3.flow

import com.pro3.aux.OptionLineItem
import com.pro3.list.LeadTimeType
import com.pro3.main.Quote
import com.pro3.list.QuoteStatus
import com.pro3.list.RequestStatus
import com.pro3.main.Rfq
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_VENDOR', 'ROLE_USER'])
@Transactional(readOnly = true)
// @TODO : Too much logic in services
class FlowQuoteController {
    def authUserService
    
    def editQuote() {
        log.debug "editQuote() ${params}"
        assert params?.id
        Quote quote = Quote.get(params?.id)
        respond quote, [model: [readonly: quote.isReadOnly()]] 
    }

    @Transactional
    def saveQuote(Quote quote) {
        log.debug "saveQuote() ${params}"

        def user = authUserService.obtainCurrentUser()
        
        quote.quoteLineItems.each {qLineItem->
            def price = params.get("price-" + qLineItem.id)
            qLineItem.price = price ? new BigDecimal(price) : null

            def leadTime = params.get("leadTime-" + qLineItem.id)
            qLineItem.leadTime = leadTime ? Integer.valueOf(leadTime) : null

            def leadTimeType = params.get("leadTimeType-" + qLineItem.id)
            qLineItem.leadTimeType = leadTimeType ? LeadTimeType.findById(leadTimeType) : null

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


        redirect controller: 'listHome', view: 'index'
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
            response.sendError(404, 'Could not find Optional LineItem')
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
