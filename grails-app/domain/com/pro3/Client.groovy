package com.pro3

import com.pro3.user.User

class Client {
    String name
    String contactName
    String address
    String phoneNumber
    

    Date dateCreated
    Date lastUpdated

    static hasMany = [projects: Project]

    static constraints = {
        projects nullable: true, blank: true

        name nullable: false, blank: false
        name contactName: true, blank: true
        name address: true, blank: true
        name phoneNumber: true, blank: true
        
        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${name}"
    }
}
