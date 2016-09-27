package com.pro3

import javax.sound.sampled.Line

class QuoteLineItem {
    BigDecimal price
    Date shipDate
    LineItem lineItem

    Date dateCreated
    Date lastUpdated
    
    static belongsTo = [quote: Quote]

    static constraints = {
        price nullable: false, scale: 2
        shipDate nullable: false
        dateCreated display: false
        lastUpdated display: false
        quote nullable: false
        lineItem nullable: false
    }

    public String toString() {
        "${price} ${shipDate}"
    }
}
