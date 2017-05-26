package com.pro3.aux

import com.pro3.main.MaterialRequest

class Criteria {
    String name
    String weighting

    Date dateCreated
    Date lastUpdated

    static belongsTo = [request: MaterialRequest]

    static constraints = {
        name nullable: false, blank: false
        weighting nullable: true
        dateCreated display: false
        lastUpdated display: false
    }

    static mapping = {
        sort name: 'asc'
    }

    public String toString() {
        "${name}"
    }
}
