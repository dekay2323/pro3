package com.pro3

class Quote {
    Vendor vendor
    QuoteStatus status
    String bidNumber
    String contactName
    String contactPhoneNumber
    String contactEmail

    Date dateCreated
    Date lastUpdated

    static belongsTo = [rfq: Rfq]

    static hasMany = [quoteLineItems: QuoteLineItem]

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
        contactName nullable: false
        contactPhoneNumber nullable: false
        contactEmail nullable: false

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
