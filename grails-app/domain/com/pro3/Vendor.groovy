package com.pro3

import com.pro3.user.User

class Vendor {
    String name

    List users
    static hasMany = [users: User]

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name nullable: false, blank: false, size: 0..50
        users nullable: true, blank: true

        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${name}"
    }
}
