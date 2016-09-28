package com.pro3.list

import com.pro3.Rfq
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ListRfqController {

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Rfq.list(params), model:[rfqCount: Rfq.count()]
    }

}
