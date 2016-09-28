package com.pro3

class Rfq {
    String name
    Date dateCreated
    Date lastUpdated


    static hasMany = [quotes: Quote]
    static belongsTo = [materialRequest: MaterialRequest]

    static constraints = {
        name nullable: true, blank: true
        quotes nullable: false
        materialRequest nullable: false

        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${name}"
    }
}
