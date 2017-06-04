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
    
    BigDecimal committed
    BigDecimal accrued
    BigDecimal incurred
    
    static constraints = {
        projectNumber nullable: false, unique: true, size: 0..25
        name nullable: false, size: 0..50
        shortDescription nullable: true, size: 0..50
    
        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${projectNumber} - ${name}"
    }
}
