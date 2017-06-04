package com.pro3.list

import com.pro3.main.PurchaseOrder
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

/**
 * Created by demian on 2017-05-14.
 */
@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class ListPurchaseOrderController {
    def authUserService

    def index(Integer max) {
        log.debug("${max}")
        
        params.max = Math.min(max ?: 10, 100)
        List<PurchaseOrder> purchaseOrderList = authUserService.obtainAllPos()
        respond purchaseOrderList
    }
}
