package com.pro3

class RequestStatus {

    public enum RequestStatusEnum {
        ADD_TO_PLAN('Add to Plan'),
        START('Started'),
        APPROVED_TO_PLAN('Approved to Plan'),
        RFQ_ISSUED('RFQ Issued'),
        BIDS_RECIEVED('Bids Recieved'),
        EVALUATION_COMPLETE('Evaluation Complete'),
        PO_ISSUED('PO Issued')

        private RequestStatusEnum(String id) { this.id = id }
        final String id

        static RequestStatusEnum byId(String id) {
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
