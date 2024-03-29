package com.pro3.flow

import com.pro3.domain.user.Client
import com.pro3.domain.main.MaterialRequest
import com.pro3.domain.main.PurchaseOrder
import com.pro3.domain.main.Quote
import com.pro3.domain.list.QuoteStatus
import com.pro3.domain.list.RequestStatus
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class FlowPurchaseOrderController {
    
    def later() {
        log.debug("create() ${createPurchaseOrder}")
        if (params?.clientId) {
            params.client = Client.get(params?.clientId)
        }
        respond null
    }
    
    @Transactional
    def createPurchaseOrder(Quote quote) {
        log.debug("savePurchaseOrder() ${params}")
        
        quote.status = QuoteStatus.findByName(QuoteStatus.QuoteStatusEnum.PO)
        quote.save(failOnError: true, flush: true)

        // Close other quotes
        quote?.rfq?.quotes.each {Quote q ->
            if (quote?.id != q?.id) {
                q.status = QuoteStatus.findByName(QuoteStatus.QuoteStatusEnum.PO_LOST)
                q.save(failOnError: true, flush: true)
            }
        }

        PurchaseOrder purchaseOrder = new PurchaseOrder()
        purchaseOrder.rfq = quote.rfq
        purchaseOrder.quote = quote
        purchaseOrder.save(failOnError: true, flush: true)

        MaterialRequest mr = quote.rfq.materialRequest
        mr.status = RequestStatus.findByName(RequestStatus.RequestStatusEnum.PO_ISSUED)
        mr.purchaseOrder = purchaseOrder
        mr.save(failOnError: true, flush: true)

        flash.message = "Purchase order issued"

        redirect controller: "listMaterialRequest", action: "index", id: mr?.id
    }
}
