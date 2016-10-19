package com.pro3.selectors

class RequestStatus {
    String name

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name nullable: false, blank: false, unique: true, size: 0..25
        dateCreated display: false
        lastUpdated display: false
    }

    static mapping = {
        collection 'selectors_requestStatus'
    }

    public String toString() {
        "${name}"
    }
}
