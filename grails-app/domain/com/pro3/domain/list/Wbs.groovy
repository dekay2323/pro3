package com.pro3.domain.list

class Wbs {
    String code
    String description

    Date dateCreated
    Date lastUpdated

    static constraints = {
        code nullable: false, blank: false, unique: true, size: 0..25
        description nullable: true
        dateCreated display: false
        lastUpdated display: false
    }

    static mapping = {
        sort id: 'asc'
    }

    public String toString() {
        "${code}"
    }
}
