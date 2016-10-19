package com.pro3

import com.pro3.embedded.Client
import com.pro3.embedded.MaterialRequest
import org.bson.types.ObjectId

import javax.persistence.Id

class Project {
    @Id
    ObjectId _id

    String projectNumber
    String name
    String shortDescription

    Date dateCreated
    Date lastUpdated

    BigDecimal budget
    BigDecimal getBudget() {
        if (requests) {
            requests.sum { it.budget ?: 0 }
        } else {
            0
        }
    }
    BigDecimal committed
    BigDecimal accrued
    BigDecimal incurred

    static hasMany = [requests: MaterialRequest]
    static embedded = ['requests']
    static belongsTo = [client: Client]
    static transients = ['budget', 'committed', 'accrued', 'incurred']

    static constraints = {
        projectNumber nullable: false, size: 0..25
        name nullable: false, size: 0..50
        shortDescription nullable: true, size: 0..50
        client nullable: false

        dateCreated display: false
        lastUpdated display: false
    }
    def beforeInsert() {
        if (_id == null) {
            _id = new ObjectId()
        }
    }
    public String toString() {
        "${projectNumber} - ${name}"
    }
}
