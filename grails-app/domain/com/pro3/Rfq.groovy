package com.pro3

import com.pro3.embedded.Clarification
import com.pro3.embedded.MaterialRequest
import com.pro3.embedded.Quote
import org.bson.types.ObjectId

import javax.persistence.Id

class Rfq {
    @Id
    ObjectId _id

    String name
    Date dateCreated
    Date lastUpdated

    int bidsReceived = 0
    static transients = ['bidsReceived']

    static hasMany = [quotes: Quote, clarifications: Clarification]
    static embedded = ['quotes', 'clarifications']
    static belongsTo = [materialRequest: MaterialRequest]


    static constraints = {
        name nullable: true, blank: true
        quotes nullable: false
        clarifications nullable: true
        materialRequest nullable: false

        dateCreated display: false
        lastUpdated display: false
    }
    def beforeInsert() {
        if (_id == null) {
            _id = new ObjectId()
        }
    }
    public String toString() {
        "${name}"
    }
}
