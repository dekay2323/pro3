package com.pro3.embedded

import org.bson.types.ObjectId

import javax.persistence.Id

class Criteria {
    String name
    String weighting

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name nullable: false, blank: false
        weighting nullable: true
        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${name}"
    }
}
