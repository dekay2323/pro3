package com.pro3.domain.user

import com.pro3.domain.main.Project

class Client {
    String name
    String contactName
    String email
    String address
    String phoneNumber

    Date dateCreated
    Date lastUpdated

    static hasMany = [projects: Project]
    static belongsTo = [account: Account]
    
    static constraints = {
        projects nullable: true, blank: true

        name nullable: false, blank: false
        contactName nullable: true, blank: true
        email nullable: true, email: true
        address nullable: true, blank: true
        phoneNumber nullable: true, blank: true
        
        dateCreated display: false
        lastUpdated display: false
    }

    static mapping = {
        sort name: 'asc'
        projects sort: 'code', order: 'asc'
    }

    public String toString() {
        "${name}"
    }
}
