package com.pro3

import com.pro3.user.User

class Account {
    String name

    Date dateCreated
    Date lastUpdated

    static hasMany = [clients: Client]
    static embedded = ['clients']

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
