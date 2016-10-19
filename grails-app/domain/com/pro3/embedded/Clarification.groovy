package com.pro3.embedded

import org.bson.types.ObjectId

import javax.persistence.Id

class Clarification {
    String description

    Date dateCreated
    Date lastUpdated

    static constraints = {
        description nullable: false, blank: false, unique: true, size: 0..25
        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${description}"
    }
}
