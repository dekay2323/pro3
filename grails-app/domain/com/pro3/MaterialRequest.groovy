package com.pro3

import groovy.time.TimeCategory

class MaterialRequest {
    String reqNumber
    String description
    BigDecimal budget
    RequestStatus status
    Date rasDate
    Date estLeadTime
    LeadTime leadTime
    Strategy strategy
    String technicalInstructions
    Rfq rfq

    Date dateCreated
    Date lastUpdated

    static belongsTo = [project: Project]

    static hasMany = [
            bidders: Vendor,
            lineItems: LineItem,
            vddrs: Vddr,
            criteria: Criteria
    ]

    Date getShipDate() {
        if (estLeadTime) {
            use(TimeCategory) {
                return estLeadTime + 2.week
            }
        }
    }

    boolean canCreateRFQ() {
        if (bidders != null) {
            if (bidders.size() == 0)
                return false
        }
        if (lineItems != null) {
            if (lineItems.size() == 0)
                return false
        }
        status?.name == RequestStatus.RequestStatusEnum.START.name()
    }

    boolean readOnlyRFQ() {
        status?.name != RequestStatus.RequestStatusEnum.RFQ_ISSUED.name()
    }

    static constraints = {
        reqNumber nullable: true, size: 0..25
        project nullable: false
        description nullable: true, blank: false, size: 0..500
        budget nullable: true, scale: 2
        status nullable: false
        rasDate nullable: true
        estLeadTime nullable: true, size: 0..25
        leadTime nullable: true
        strategy nullable: true

        technicalInstructions nullable: true, size: 0..255, widget: 'textarea'

        rfq nullable: true
        bidders nullable: true
        lineItems nullable: true
        vddrs nullable: true
        criteria nullable: true

        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${reqNumber} ${description}"
    }
}
