package com.pro3

class Criteria {
    String name
    String weighting

    Date dateCreated
    Date lastUpdated

    static belongsTo = [request: RequestItem]

    static constraints = {
        name nullable: false, blank: false
        weighting nullable: true
        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${name}"
    }
}
