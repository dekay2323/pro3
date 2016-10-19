package com.pro3.embedded

import com.pro3.Project
import org.bson.types.ObjectId

import javax.persistence.Id

class Client {
    String name

    Date dateCreated
    Date lastUpdated

    static hasMany = [projects: Project]

    static constraints = {
        name nullable: false, blank: false
        projects nullable: true, blank: true

        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${name}"
    }
}
