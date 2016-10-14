package com.pro3

class Project {
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

    public String toString() {
        "${projectNumber} - ${name}"
    }
}
