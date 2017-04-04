package com.pro3

class QuoteStatus {
    public enum QuoteStatusEnum {
        START('Start'),
        INTENTION_TO_BID('Intention To Bid'),
        NOT_BIDDING('Not Bidding'),
        BID('Bid'),
        PO_ISSUED('PO Issued'),
        BID_OVER('Bid Over')

        private QuoteStatusEnum(String id) { this.id = id }
        final String id

        static QuoteStatusEnum byId(String id) {
            values().find { it.id == id }
        }
    }

    String name

    Date dateCreated
    Date lastUpdated

    static constraints = {
        name nullable: false, blank: false, unique: true, size: 0..25
        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${name}"
    }
}
