package com.pro3

class LineItem {
    String code
    Wbs wbs
    String description
    Integer quantity
    String unitOfMeasure

    static belongsTo = [request: MaterialRequest]

    Date dateCreated
    Date lastUpdated

    static constraints = {
        code nullable: false
        wbs nullable: true
        description nullable: false, blank: false, size: 0..500
        quantity nullable: false, blank: false
        unitOfMeasure nullable: false, blank: false, size: 0..25
        dateCreated display: false
        lastUpdated display: false
    }

    def beforeValidate() {
        if (!code) {
            code = request?.lineItems?.size()+1
        }
    }

    public String toString() {
        "${code}"
    }
}
