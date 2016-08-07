package com.pro3

class Clarification {
    String description

    static belongsTo = [rfq: Rfq]

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
