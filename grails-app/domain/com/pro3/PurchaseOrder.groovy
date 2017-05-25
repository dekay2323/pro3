package com.pro3

class PurchaseOrder {
    Date dateCreated
    Date lastUpdated

    static belongsTo = [rfq: Rfq, quote: Quote]

    static constraints = {
        rfq nullable: false, blank: false
        quote nullable: false, blank: false
    }

    static mapping = {
        sort dateCreated: 'asc'
    }

    public String toString() {
        "${rfq} ${quote}"
    }
}
