package com.pro3.domain.main

import com.pro3.domain.aux.Clarification
import com.pro3.domain.list.QuoteStatus
import com.pro3.domain.user.Account

class Rfq {
    String name
    Date dateCreated
    Date lastUpdated

    Account getAccount() {
        materialRequest.getAccount()    
    }
    
    int getBidsReceived() {
        quotes.findAll {quote->
            quote?.status?.name == QuoteStatus.QuoteStatusEnum.BID.name()
        }?.size()
    }

    int getBidsOut() {
        quotes.size()
    }

    static hasMany = [quotes: Quote, clarifications: Clarification]
    static belongsTo = [materialRequest: MaterialRequest]

    static constraints = {
        name nullable: true, blank: true
        quotes nullable: false
        clarifications nullable: true
        materialRequest nullable: false

        dateCreated display: false
        lastUpdated display: false
    }
    
    static mapping = {
        sort name: 'asc'
        materialRequest sort: 'code', order: 'asc'
        quotes sort: 'code', order: 'asc'
    }
    
    public String toString() {
        "${name}"
    }
}
