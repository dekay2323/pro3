package com.pro3

class Rfq {
    String name
    Date dateCreated
    Date lastUpdated

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

    public String toString() {
        "${name}"
    }
}
