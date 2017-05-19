package com.pro3

class Client {
    String name
    String contactName
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
        address nullable: true, blank: true
        phoneNumber nullable: true, blank: true
        
        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${name}"
    }
}
