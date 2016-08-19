package com.pro3

import groovy.time.TimeCategory

class RequestItem {
    String reqNumber
    String description
    BigDecimal budget
    RequestStatus status
    Date rasDate
    String estLeadTime
    LeadTime leadTime
    Date shipDate
    Strategy strategy
    String technicalInstructions

    Date dateCreated
    Date lastUpdated

    static transients = ['shipDate']
    static belongsTo = [project: Project]
    static hasMany = [
            bidders: Vendor,
            lineItems: LineItem,
            vddrs: Vddr,
            criteria: Criteria,
            rfqs: Rfq
    ]

    Date getShipDate() {
        if (rasDate) {
            use(TimeCategory) {
                return estLeadTime + 2.week
            }
        }
    }

    static constraints = {
        reqNumber nullable: true, unique: true, size: 0..25
        project nullable: false
        description nullable: true, blank: false, size: 0..500
        budget nullable: true, scale: 2
        status nullable: false
        rasDate nullable: true
        estLeadTime nullable: true, size: 0..25
        leadTime nullable: true
        strategy nullable: true

        technicalInstructions nullable: true, size: 0..255, widget: 'textarea'

        bidders nullable: true
        lineItems nullable: true
        vddrs nullable: true
        criteria nullable: true
        rfqs nullable: true

        dateCreated display: false
        lastUpdated display: false
    }

    public String toString() {
        "${reqNumber}"
    }
}
