package com.pro3.embedded

import com.pro3.User
import org.bson.types.ObjectId

import javax.persistence.Id

class Vendor {
    String name

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
