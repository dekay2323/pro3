package com.pro3.show

import com.pro3.domain.main.PurchaseOrder
import com.pro3.domain.user.User
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class ShowPurchaseOrderController {
    def authUserService

    def showPurchaseOrder() {
        log.debug("show() ${params}")
        assert params?.id
        User user = authUserService.obtainCurrentUser()
        respond PurchaseOrder.get(params?.id), model: [user: user]
    }
}
