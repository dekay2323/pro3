package com.pro3

import com.pro3.user.User

class Quote {
    User vendor
    QuoteStatus status
    String bidNumber
    String contactName
    String contactPhoneNumber
    String contactEmail
    Boolean bidding
    User changedBy
    String notBiddingReason

    Date dateCreated
    Date lastUpdated

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

    static constraints = {
        vendor nullable: false
        status nullable: false
        quoteLineItems nullable: true, blank: true
        bidNumber nullable: true
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
        sort bidNumber: 'asc'
        quoteLineItems sort: 'code', order: 'asc'
        optionLineItems sort: 'description', order: 'asc'
    }

    boolean canCreateBid() {
        status?.name == QuoteStatus.QuoteStatusEnum.START?.name()
    }

    public String toString() {
        "${rfq?.name} - ${vendor}"
    }
}
