package com.pro3

class Rfq {
    Date dateCreated
    Date lastUpdated

    static belongsTo = [vendor: Vendor]
    static hasMany = [quotes: Quote]

    static constraints = {
        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${vendor}"
    }
}
