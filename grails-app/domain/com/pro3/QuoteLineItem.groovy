package com.pro3

class QuoteLineItem {
    BigDecimal price
    Date shipDate

    Date dateCreated
    Date lastUpdated
    
    static belongsTo = [quote: Quote, lineItem: LineItem]

    static constraints = {
        price nullable: false, scale: 2
        shipDate nullable: false
        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${price} ${shipDate}"
    }
}
