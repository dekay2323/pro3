package com.pro3

import com.pro3.user.User
import groovy.time.TimeCategory

class MaterialRequest {
    String reqNumber
    String description
    BigDecimal budget
    RequestStatus status
    Date rasDate
    Date estLeadTime
    Date closingDate
    LeadTime leadTime
    Strategy strategy
    String technicalInstructions
    Rfq rfq

    Date dateCreated
    Date lastUpdated

    static belongsTo = [project: Project]

    static hasMany = [
            bidders: User,
            lineItems: LineItem,
            vddrs: Vddr,
            criteria: Criteria
    ]

    def daysLeftTillClose() {
        if (closingDate) {
            use(groovy.time.TimeCategory) {
                def duration = closingDate - new Date()
                duration.days > 0 ? "${duration?.days} days left" : "closed"
            }
        } else {
            ''
        }
    }

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
        if (!closingDate) {
            return false
        }
        status?.name == RequestStatus.RequestStatusEnum.START.name()
    }

    boolean showsInRFQList() {
        status?.name != RequestStatus.RequestStatusEnum.START.name()
    }

    boolean readOnlyRFQ() {
        status?.name != RequestStatus.RequestStatusEnum.RFQ_ISSUED.name()
    }

    String obtainFileDirectory(String account) {
        assert account
        "${account}/${this?.project?.client?.name}/${this?.project?.projectNumber}/${this?.reqNumber}"
    }
    
    static constraints = {
        reqNumber nullable: true, size: 0..25
        project nullable: false
        description nullable: true, blank: false, size: 0..500
        budget nullable: true, scale: 2
        status nullable: false
        rasDate nullable: true
        estLeadTime nullable: true, size: 0..25
        closingDate nullable: true
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
