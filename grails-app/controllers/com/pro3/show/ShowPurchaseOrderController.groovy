package com.pro3.show

import com.pro3.main.PurchaseOrder
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class ShowPurchaseOrderController {

    def showPurchaseOrder() {
        log.debug("show() ${params}")
        assert params?.id
        respond PurchaseOrder.get(params?.id)
    }
}
