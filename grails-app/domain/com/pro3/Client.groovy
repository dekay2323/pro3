package com.pro3

import com.pro3.user.User

class Client {
    String name

    Date dateCreated
    Date lastUpdated

    static hasMany = [projects: Project, users: User]

    static constraints = {
        name nullable: false, blank: false
        projects nullable: true, blank: true
        users nullable: true, blank: true

        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${name}"
    }
}
