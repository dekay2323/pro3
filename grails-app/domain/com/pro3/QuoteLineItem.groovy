package com.pro3

class QuoteLineItem {
    String code
    BigDecimal price
    LineItem lineItem
    Boolean checkOff = false
    Integer leadTime

    Date dateCreated
    Date lastUpdated

    BigDecimal getExtendedPrice() {
        (price && lineItem?.quantity) ? price * lineItem?.quantity : 0
    }

    static belongsTo = [quote: Quote]

    static constraints = {
        price nullable: true, blank: true, scale: 2
        quote nullable: false
        lineItem nullable: false
        code nullable: false, blank: false
        checkOff nullable: false
        leadTime nullable: true, blank: true

        dateCreated display: false
        lastUpdated display: false
    }

    static mapping = {
        sort code: 'asc'
    }

    def beforeValidate() {
        if (!lineItem) {
            code = lineItem?.code
        }
    }

    public String toString() {
        "${lineItem?.description}"
    }
}
