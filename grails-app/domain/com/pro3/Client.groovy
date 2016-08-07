package com.pro3

class Client {
    String name

    Date dateCreated
    Date lastUpdated

    static hasMany = [projects: Project]

    static constraints = {
        name nullable: false, blank: false
        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${name}"
    }
}
