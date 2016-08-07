package com.pro3

class Project {
    String projectNumber
    String name
    String shortDescription
    Client client

    Date dateCreated
    Date lastUpdated

    BigDecimal budget
    BigDecimal getBudget() {
        requests.sum {it.budget ?: 0}
    }

    static hasMany = [requests: RequestItem]

    static transients = ['budget']

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
