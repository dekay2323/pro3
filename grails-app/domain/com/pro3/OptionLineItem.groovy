package com.pro3

class OptionLineItem {
    String description
    BigDecimal price
    Date shipDate
    LineItem lineItem

    Date dateCreated
    Date lastUpdated

    BigDecimal getExtendedPrice() {
        (price && lineItem?.quantity) ? price * lineItem?.quantity : 0
    }

    static belongsTo = [quote: Quote]

    static constraints = {
        description nullable: false, blank: false
        price nullable: true, blank: true, scale: 2
        shipDate nullable: true, blank: true
        quote nullable: false
        lineItem nullable: true

        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${price} ${shipDate}"
    }
}
