package com.pro3

class Quote {
    Vendor vendor
    QuoteStatus status

    Date dateCreated
    Date lastUpdated


    static belongsTo = [rfq: Rfq]

    static hasMany = [quoteLineItems: QuoteLineItem]

    static constraints = {
        vendor nullable: false
        status nullable: false
        quoteLineItems nullable: true, blank: true
        dateCreated display: false
        lastUpdated display: false
    }


    boolean canCreateBid() {
        status.name == 'Start'
    }

    public String toString() {
        "${rfq?.name} - ${vendor}"
    }
}
