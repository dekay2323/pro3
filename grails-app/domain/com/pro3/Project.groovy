package com.pro3

import com.pro3.user.User

class Project {
    String projectNumber
    String name
    String shortDescription

    Date dateCreated
    Date lastUpdated

    BigDecimal getBudget() {
        requests.sum {it.budget ?: 0}
    }
    BigDecimal committed
    BigDecimal accrued
    BigDecimal incurred

    static hasMany = [
            requests: MaterialRequest,
            managers: User,
            internalApprovers: User
    ]
    static belongsTo = [client: Client]
    static transients = ['budget', 'committed', 'accrued', 'incurred']

    static constraints = {
        projectNumber nullable: false, size: 0..25
        name nullable: false, size: 0..50
        shortDescription nullable: true, size: 0..50
        client nullable: false
        requests nullable: true
        
        managers nullable: true
        internalApprovers nullable: true

        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${projectNumber} - ${name}"
    }
}
