package com.pro3.domain.aux

import com.pro3.domain.main.Rfq

class Clarification {
    String name

    static belongsTo = [rfq: Rfq]

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name nullable: false, blank: false, unique: true, size: 0..25
        dateCreated display: false
        lastUpdated display: false
    }

    static mapping = {
        sort dateCreated: 'asc'
    }
    
    public String toString() {
        "${name}"
    }
}
