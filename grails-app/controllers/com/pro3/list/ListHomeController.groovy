package com.pro3.list

import com.pro3.MaterialRequest
import com.pro3.Project
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class ListHomeController {

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def poData = [:]
        poData.ytd = 1
        poData.ytdValue = 1
        poData.all = 1
        poData.allValue = 1
        respond Project.list(params), model:[projectCount: MaterialRequest.count(), poData: poData]
    }

}
