package com.pro3

class Account {
    String name

    Date dateCreated
    Date lastUpdated

    List clients
    static hasMany = [clients: Client]

    static constraints = {
        name nullable: false, blank: false
        clients nullable: true, blank: true

        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${name}"
    }
}
