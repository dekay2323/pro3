package com.pro3.list

class Strategy {
    String name

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name nullable: false, blank: false, unique: true, size: 0..25
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
