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


    boolean canCreateBid() {
        Rfq rfq = rfq
        MaterialRequest materialRequest = rfq?.materialRequest
        RequestStatus requestStatus = materialRequest?.status
        requestStatus.name == 'RFQ Issued'
    }

    public String toString() {
        "${rfq?.name} - ${vendor}"
    }
}
