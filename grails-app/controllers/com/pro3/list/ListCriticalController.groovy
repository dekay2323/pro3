package com.pro3.list

import com.pro3.MaterialRequest
import grails.transaction.Transactional

import static org.springframework.http.HttpStatus.*

@Transactional(readOnly = true)
class ListCriticalController {

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond MaterialRequest.list(params), model:[materialRequestCount: MaterialRequest.count()]
    }

}
