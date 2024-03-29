package com.pro3.domain.main

import com.pro3.domain.aux.OptionLineItem
import com.pro3.domain.aux.QuoteLineItem
import com.pro3.domain.list.QuoteStatus
import com.pro3.domain.user.Account
import com.pro3.domain.user.User

class Quote {
    User vendor
    QuoteStatus status
    String code
    String contactName
    String contactPhoneNumber
    String contactEmail
    Boolean bidding
    User changedBy
    String notBiddingReason

    Date dateCreated
    Date lastUpdated

    Account getAccount() {
        rfq.getAccount()
    }
    
    static belongsTo = [rfq: Rfq]

    static hasMany = [quoteLineItems: QuoteLineItem, optionLineItems: OptionLineItem]

    public Project getProject() {
        rfq?.materialRequest?.project
    }
    
    QuoteLineItem getQuoteForLineItem(Long lineItemId) {
        log.debug("getQuoteForLineItem() ${lineItemId}")
        quoteLineItems.find {quoteLineItem->
            quoteLineItem?.lineItem?.id == lineItemId
        }
    }

    BigDecimal obtainQuoteValue() {
        BigDecimal valueLineItem = 0
        quoteLineItems.each {valueLineItem += it?.getExtendedPrice()}
        BigDecimal valueOptionalLineItem = 0
        optionLineItems.each {valueOptionalLineItem += it?.getExtendedPrice()}
        valueLineItem + valueOptionalLineItem
    }

    Integer obtainLongestLeadTime() {
        def lineItemMax = quoteLineItems.max {it?.leadTime}?.leadTime
        def optionLineItemMax = optionLineItems.max {it?.leadTime}?.leadTime
        lineItemMax > optionLineItemMax ? lineItemMax : optionLineItemMax
    }

    User getUser() {
        rfq.materialRequest.user   
    }
    
    boolean isBid() {
        if (status?.name == QuoteStatus.QuoteStatusEnum.BID.name()) {
            return true;
        } else {
            return false;
        }
    }

    boolean isPO() {
        if (status?.name == QuoteStatus.QuoteStatusEnum.PO.name()) {
            return true;
        } else {
            return false;
        }
    }
    
    boolean isReadOnly() {
        if (status?.name == QuoteStatus.QuoteStatusEnum.PO.name() 
                || status?.name == QuoteStatus.QuoteStatusEnum.PO_LOST.name() 
                || status?.name == QuoteStatus.QuoteStatusEnum.BID.name()) {
            return true;
        } else {
            return false;
        }
    }

    static constraints = {
        vendor nullable: false
        status nullable: false
        quoteLineItems nullable: true, blank: true
        code nullable: true
        contactName nullable: true
        contactPhoneNumber nullable: true
        contactEmail nullable: true
        bidding nullable: true
        changedBy nullable: true
        notBiddingReason nullable: true

        dateCreated display: false
        lastUpdated display: false
    }

    static mapping = {
        sort code: 'asc'
        quoteLineItems sort: 'code', order: 'asc'
        optionLineItems sort: 'name', order: 'asc'
    }

    boolean canCreateBid() {
        status?.name == QuoteStatus.QuoteStatusEnum.START?.name()
    }

    public String toString() {
        "${rfq?.name} - ${vendor}"
    }
}
