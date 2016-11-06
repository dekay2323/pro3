package com.pro3

class QuoteStatus {

    public enum QuoteStatusEnum {
        START('Start'),
        BID('Bid')

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
