package com.pro3

class Rfq {
    String name

    Date dateCreated
    Date lastUpdated

    static belongsTo = [vendor: Vendor]
    static hasMany = [quotes: Quote]

    static constraints = {
        name nullable: false, blank: false, unique: true, size: 0..25
        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${name}"
    }
}
