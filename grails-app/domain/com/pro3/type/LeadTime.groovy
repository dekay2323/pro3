package com.pro3.type

class LeadTime {
    String name

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name nullable: false, blank: false, unique: true, size: 0..25
        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${name}"
    }
}
