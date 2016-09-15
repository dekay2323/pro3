package com.pro3.list

import com.pro3.MaterialRequest
import com.pro3.Project
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ListVendorController {

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)

        respond M.list(params), model:[projectCount: MaterialRequest.count(), poData: poData]
    }

}
