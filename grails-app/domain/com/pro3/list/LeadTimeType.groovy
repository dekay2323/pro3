package com.pro3.list

class LeadTimeType {
    String name
    String help

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name nullable: false, blank: false, unique: true, size: 0..25
        name nullable: false, blank: false
        dateCreated display: false
        lastUpdated display: false
    }

    static mapping = {
        sort id: 'asc'
    }
    
    public String toString() {
        "${name}"
    }
}
