package com.pro3

class PurchaseOrder {
    Date dateCreated
    Date lastUpdated

    static belongsTo = [rfq: Rfq, quote: Quote]

    static constraints = {
        rfq nullable: false, blank: false
        quote nullable: false, blank: false
    }

    public String toString() {
        "${rfq} ${quote}"
    }
}
