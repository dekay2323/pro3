package com.pro3.embedded

import com.pro3.selectors.Wbs
import org.bson.types.ObjectId

import javax.persistence.Id

class LineItem {
    String code
    Wbs wbs
    String description
    Integer quantity
    String unitOfMeasure
    static embedded = ['wbs']

    Date dateCreated
    Date lastUpdated

    static constraints = {
        code nullable: false
        wbs nullable: true
        description nullable: true, size: 0..500
        quantity nullable: true
        unitOfMeasure nullable: true, size: 0..25
        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${code}"
    }
}
