package com.pro3.main

import com.pro3.user.Account
import com.pro3.user.Client
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
    
    Account getAccount() {
        client?.account
    }
    
    BigDecimal committed
    BigDecimal accrued
    BigDecimal incurred
    
    static hasMany = [
            requests: MaterialRequest,
            managers: User
    ]
    static belongsTo = [client: Client]
    static transients = ['budget', 'committed', 'accrued', 'incurred']

    static constraints = {
        projectNumber nullable: false, unique: true, size: 0..25
        name nullable: false, size: 0..50
        shortDescription nullable: true, size: 0..50
        client nullable: false
        requests nullable: true
        
        managers nullable: true

        dateCreated display: false
        lastUpdated display: false
    }

    static mapping = {
        sort projectNumber: 'asc'
        requests sort: 'reqNumber', order: 'asc'
    }
    
    public String toString() {
        "${projectNumber} - ${name}"
    }
}