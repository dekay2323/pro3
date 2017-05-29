package com.pro3.aux

import com.pro3.main.Rfq

class Clarification {
    String description

    static belongsTo = [rfq: Rfq]

    Date dateCreated
    Date lastUpdated

    static constraints = {
        description nullable: false, blank: false, unique: true, size: 0..25
        dateCreated display: false
        lastUpdated display: false
    }

    static mapping = {
        sort dateCreated: 'asc'
    }
    
    public String toString() {
        "${description}"
    }
}