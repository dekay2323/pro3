package com.pro3

import groovy.time.TimeCategory

class MaterialRequest {
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
    Rfq rfq

    Date dateCreated
    Date lastUpdated

    static transients = ['shipDate']
    static belongsTo = [project: Project]

    List bidders
    List lineItems
    List vddrs
    List criteria
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
