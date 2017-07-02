package com.pro3.domain.aux

import com.pro3.domain.main.Quote

class OptionLineItem {
    String name
    Integer quantity
    String unitOfMeasure
    BigDecimal price
    Integer leadTime
    LineItem lineItem

    Date dateCreated
    Date lastUpdated

    BigDecimal getExtendedPrice() {
        (price && quantity) ? price * quantity : 0
    }

    def getLineItemsList() {
        quote?.quoteLineItems?.collect { quoteLineItem->
            quoteLineItem?.lineItem
        }
    }

    static belongsTo = [quote: Quote]

    static constraints = {
        name nullable: false, blank: false
        price nullable: true, blank: true, scale: 2
        quote nullable: false
        quantity nullable: true
        unitOfMeasure nullable: true, size: 0..25
        lineItem nullable: true
        leadTime nullable: true, blank: true

        dateCreated display: false
        lastUpdated display: false
    }

    static mapping = {
        sort description: 'asc'
    }

    public String toString() {
        "${name}"
    }
}
