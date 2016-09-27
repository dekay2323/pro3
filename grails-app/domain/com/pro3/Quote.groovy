package com.pro3

class Quote {
    String name
    Vendor vendor

    Date dateCreated
    Date lastUpdated

    static belongsTo = [rfq: Rfq]
    static hasMany = [quoteLineItems: QuoteLineItem]

    static constraints = {
        name nullable: false, blank: false, unique: true, size: 0..25
        vendor nullable: false
        quoteLineItems nullable: true, blank: true
        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${name}"
    }
}
