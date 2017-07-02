package com.pro3.main

import com.pro3.aux.Criteria
import com.pro3.aux.LineItem
import com.pro3.list.LeadTimeType
import com.pro3.list.RequestStatus
import com.pro3.list.Strategy
import com.pro3.list.Vddr
import com.pro3.user.Account
import com.pro3.user.User
import groovy.time.TimeCategory

class MaterialRequest {
    String code
    String name
    BigDecimal budget
    RequestStatus status
    String rasDate
    LeadTimeType leadTime
    Strategy strategy
    String technicalInstructions
    Rfq rfq
    PurchaseOrder purchaseOrder

    Date dateCreated
    Date lastUpdated

    Account getAccount() {
        project.getAccount()
    }
    
    static belongsTo = [project: Project]

    static hasMany = [
            bidders: User,
            lineItems: LineItem,
            vddrs: Vddr,
            criteria: Criteria
    ]

    def daysLeftTillRas() {
        if (rasDate) {
            use(TimeCategory) {
                def duration = new Date().parse('yyyy-MM-dd', rasDate) - new Date()
                duration.days > 0 ? "${duration?.days} days left" : 'closed'
            }
        } else {
            ''
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
        "${account}/${this?.project?.client?.name}/${this?.project?.code}/${this?.code}"
    }
    
    static constraints = {
        code nullable: true, size: 0..25
        project nullable: false
        name nullable: false, blank: false, size: 0..500
        budget nullable: true, scale: 2
        status nullable: false
        rasDate nullable: true
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

    static mapping = {
        sort code: 'asc'
        lineItems sort: 'code', order: 'asc'
        criteria sort: 'name', order: 'asc'
    }

    public String toString() {
        "${code} ${name}"
    }
}
