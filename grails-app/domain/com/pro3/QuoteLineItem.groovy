package com.pro3

class QuoteLineItem {
    String code
    BigDecimal price
    String shipDate
    LineItem lineItem
    Boolean checkOff
    Boolean inPO
    
    Date dateCreated
    Date lastUpdated

    BigDecimal getExtendedPrice() {
        (price && lineItem?.quantity) ? price * lineItem?.quantity : 0
    }

    static belongsTo = [quote: Quote]

    static constraints = {
        price nullable: true, blank: true, scale: 2
        shipDate nullable: true, blank: true
        quote nullable: false
        lineItem nullable: false
        code nullable: false, blank: false
        checkOff nullable: false
        inPO nullable: true
        
        dateCreated display: false
        lastUpdated display: false
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
