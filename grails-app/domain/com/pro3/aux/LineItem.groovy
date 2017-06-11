package com.pro3.aux

import com.pro3.list.Wbs
import com.pro3.main.MaterialRequest

class LineItem {
    String code
    Wbs wbs
    String name
    Integer quantity
    String unitOfMeasure

    static belongsTo = [request: MaterialRequest]

    Date dateCreated
    Date lastUpdated

    static constraints = {
        code nullable: false
        wbs nullable: true
        name nullable: false, blank: false, size: 0..500
        quantity nullable: false, blank: false
        unitOfMeasure nullable: false, blank: false, size: 0..25
        dateCreated display: false
        lastUpdated display: false
    }
    
    static mapping = {
        sort code: 'asc'
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
