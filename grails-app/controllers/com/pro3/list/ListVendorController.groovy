package com.pro3.list

import com.pro3.Constants
import com.pro3.MaterialRequest
import com.pro3.Project
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured([Constants.ROLE_ADMIN, Constants.ROLE_USER])
@Transactional(readOnly = true)
class ListVendorController {

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        respond M.list(params), model:[projectCount: MaterialRequest.count(), poData: poData]
    }

}
