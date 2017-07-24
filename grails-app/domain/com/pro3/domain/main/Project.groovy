package com.pro3.domain.main

import com.pro3.domain.user.Client
import com.pro3.domain.user.User
import com.pro3.domain.user.Account

class Project {
    String code
    String name
    String description

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
        code nullable: false, size: 0..25
        name nullable: false, size: 0..50
        description nullable: true, size: 0..50
        client nullable: false
        requests nullable: true
        
        managers nullable: true

        dateCreated display: false
        lastUpdated display: false
    }

    static mapping = {
        sort projectNumber: 'asc'
        requests sort: 'code', order: 'asc'
    }
    
    public String toString() {
        "${code} - ${name}"
    }
}
