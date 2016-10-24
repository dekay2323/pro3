package com.pro3

class QuoteLineItem {
    String code
    BigDecimal price
    BigDecimal extendedPrice
    Date shipDate
    LineItem lineItem

    Date dateCreated
    Date lastUpdated

    BigDecimal getExtendedPrice() {
        (price && lineItem?.quantity) ? price * lineItem?.quantity : 0
    }

    static belongsTo = [quote: Quote]

    static transients = ['extendedPrice']

    static constraints = {
        price nullable: true, blank: true, scale: 2
        shipDate nullable: true, blank: true
        quote nullable: false
        lineItem nullable: false
        code nullable: false, blank: false
        dateCreated display: false
        lastUpdated display: false
    }

    def beforeValidate() {
        if (!lineItem) {
            code = lineItem?.code
        }
    }

    public String toString() {
        "${price} ${shipDate}"
    }
}
