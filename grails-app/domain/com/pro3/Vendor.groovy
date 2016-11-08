package com.pro3

import com.pro3.user.User

class Vendor {
    String name

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name nullable: false, blank: false, size: 0..50

        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${name}"
    }
}
