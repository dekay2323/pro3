package com.pro3.domain.main

import com.pro3.domain.user.Account

class PurchaseOrder {
    Date dateCreated
    Date lastUpdated

    Account getAccount() {
        rfq.getAccount()
    }
    
    static belongsTo = [rfq: Rfq, quote: Quote]

    static constraints = {
        rfq nullable: false, blank: false
        quote nullable: false, blank: false
    }

    static mapping = {
        sort dateCreated: 'asc'
    }

    public String toString() {
        "${rfq} ${quote}"
    }
}