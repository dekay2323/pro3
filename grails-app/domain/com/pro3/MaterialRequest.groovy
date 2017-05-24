package com.pro3

import com.pro3.user.User
import groovy.time.TimeCategory

class MaterialRequest {
    String reqNumber
    String description
    BigDecimal budget
    RequestStatus status
    String rasDate
    String estLeadTime
    String closingDate
    LeadTime leadTime
    Strategy strategy
    String technicalInstructions
    Rfq rfq
    PurchaseOrder purchaseOrder

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
            use(TimeCategory) {
                def duration = new Date().parse('yyyy-MM-dd', closingDate) - new Date()
                duration.days > 0 ? "${duration?.days} days left" : 'closed'
            }
        } else {
            ''
        }
    }

    Date getShipDate() {
        if (estLeadTime) {
            use(TimeCategory) {
                return new Date(estLeadTime) + 2.week
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
        if (status?.name == RequestStatus.RequestStatusEnum.RFQ_ISSUED.name() 
        || status?.name == RequestStatus.RequestStatusEnum.BIDS_RECIEVED.name()
        || status?.name == RequestStatus.RequestStatusEnum.EVALUATION_COMPLETE.name()
        || status?.name == RequestStatus.RequestStatusEnum.PO_ISSUED.name()) {
            return true
        } else {
            return false
        }
    }

    String obtainFileDirectory(String account) {
        assert account
        "${account}/${this?.project?.client?.name}/${this?.project?.projectNumber}/${this?.reqNumber}"
    }
    
    static constraints = {
        reqNumber nullable: true, size: 0..25
        project nullable: false
        description nullable: false, blank: false, size: 0..500
        budget nullable: true, scale: 2
        status nullable: false
        rasDate nullable: true
        estLeadTime nullable: true, size: 0..25
        closingDate nullable: true
        leadTime nullable: true
        strategy nullable: true

        technicalInstructions nullable: true, size: 0..255, widget: 'textarea'

        rfq nullable: true
        purchaseOrder nullable: true
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
