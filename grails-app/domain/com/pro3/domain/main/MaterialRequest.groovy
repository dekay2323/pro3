package com.pro3.domain.main

import com.pro3.NumberAwareSorter
import com.pro3.domain.aux.LineItem
import com.pro3.domain.aux.ProcurementType
import com.pro3.domain.list.ProcurementType
import com.pro3.domain.list.RequestStatus
import com.pro3.domain.list.Strategy
import com.pro3.domain.aux.Criteria
import com.pro3.domain.list.LeadTimeType
import com.pro3.domain.list.Vddr
import com.pro3.domain.user.Account
import com.pro3.domain.user.User
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
    ProcurementType procurementType

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

    // @TODO : Should be in .yml
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

    def sortedLineItems() {
        def lineItems = lineItems.toList();
        Collections.sort(lineItems, new NumberAwareSorter())
        lineItems
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
        procurementType nullable: true

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
