package com.pro3

class QuoteLineItem {
    BigDecimal price
    BigDecimal extendedPrice
    Date shipDate
    LineItem lineItem

    Date dateCreated
    Date lastUpdated

    BigDecimal getExtendedPrice() {
        if (price && lineItem?.quantity)
            price * lineItem?.quantity
        else
            0
    }

    static belongsTo = [quote: Quote]

    static transients = ['extendedPrice']

    static constraints = {
        price nullable: true, blank: true, scale: 2
        shipDate nullable: true, blank: true
        quote nullable: false
        lineItem nullable: false
        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${price} ${shipDate}"
    }
}
