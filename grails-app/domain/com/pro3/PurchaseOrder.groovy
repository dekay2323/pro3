package com.pro3

class PurchaseOrder {
    Date dateCreated
    Date lastUpdated
    Rfq rfq
    Quote quote

    static constraints = {
        rfq nullable: false, blank: false
        quote nullable: false, blank: false
    }

    public String toString() {
        "${rfq} ${quote}"
    }
}
