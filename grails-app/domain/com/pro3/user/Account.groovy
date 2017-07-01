package com.pro3.user

/** 
 * This is the top level
 */
class Account {
    String name

    Date dateCreated
    Date lastUpdated

    static hasMany = [
            clients: Client,
            users: User
    ]

    static constraints = {
        name nullable: false, blank: false
        clients nullable: true
        users nullable: true

        dateCreated display: false
        lastUpdated display: false
    }

    static mapping = {
        sort name: 'asc'
        clients sort: 'name', order: 'asc'
        users sort: 'username', order: 'asc'
    }

    public String toString() {
        "${name}"
    }
}
