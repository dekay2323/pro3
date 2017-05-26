package com.pro3.list

import com.pro3.main.MaterialRequest

class Vddr {
    String code
    String number
    String description
    Integer copies
    Integer copiesForReview
    Integer copiesFinal

    static belongsTo = MaterialRequest

    Date dateCreated
    Date lastUpdated

    static constraints = {
        code nullable: false, blank: false, size: 0..50
        number nullable: true
        description nullable: true
        copies nullable: true
        copiesForReview nullable: true
        copiesFinal nullable: true
        dateCreated display: false
        lastUpdated display: false
    }

    static mapping = {
        sort id: 'asc'
    }

    public String toString() {
        "${code}"
    }
}
