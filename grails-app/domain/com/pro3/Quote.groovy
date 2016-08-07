package com.pro3

class Quote {
    String name

    Date dateCreated
    Date lastUpdated

    static belongsTo = [request: RequestItem]
    static hasMany = [quoteLineItems: QuoteLineItem]

    static constraints = {
        name nullable: false, blank: false, unique: true, size: 0..25
        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${name}"
    }
}
