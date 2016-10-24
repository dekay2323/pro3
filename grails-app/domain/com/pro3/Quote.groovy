package com.pro3

class Quote {
    Vendor vendor

    Date dateCreated
    Date lastUpdated


    static belongsTo = [rfq: Rfq]

    static hasMany = [quoteLineItems: QuoteLineItem]

    static constraints = {
        vendor nullable: false
        quoteLineItems nullable: true, blank: true
        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${rfq?.name} - ${vendor}"
    }
}
