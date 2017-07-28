package com.pro3.domain.main

import com.pro3.domain.user.Account
import com.pro3.domain.user.Client
import com.pro3.domain.user.User

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

    Client getClient() {
        this?.rfq?.materialRequest?.project?.client
    }
    
    User getVendor() {
        this?.quote?.vendor
        
    }
    
    public String toString() {
        "${rfq} ${quote}"
    }
}
