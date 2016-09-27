package com.pro3

class Rfq {
    Date dateCreated
    Date lastUpdated

    static hasMany = [quotes: Quote]
    static belongsTo = [materialRequest: MaterialRequest]

    static constraints = {
        dateCreated display: false
        lastUpdated display: false

        quotes nullable: false
        materialRequest nullable: false
    }

    public String toString() {
        ""
    }
}
