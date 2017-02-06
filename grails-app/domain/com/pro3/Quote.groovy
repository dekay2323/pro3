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
    String notBiddingReason

    Date dateCreated
    Date lastUpdated

    static belongsTo = [rfq: Rfq]

    static hasMany = [quoteLineItems: QuoteLineItem, optionLineItems: OptionLineItem]

    QuoteLineItem getQuoteForLineItem(Long lineItemId) {
        log.debug("getQuoteForLineItem() ${lineItemId}")
        quoteLineItems.find {quoteLineItem->
            quoteLineItem?.lineItem?.id == lineItemId
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
        notBiddingReason nullable: true

        dateCreated display: false
        lastUpdated display: false
    }

    boolean canCreateBid() {
        status?.name == QuoteStatus.QuoteStatusEnum.START?.name()
    }

    public String toString() {
        "${rfq?.name} - ${vendor}"
    }
}
