package com.pro3.list

import com.pro3.embedded.MaterialRequest
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class ListHomeController {
    def authService

    // @TODO : do poData
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def poData = [:]
        poData.ytd = 0
        poData.ytdValue = 0
        poData.all = 0
        poData.allValue = 0
        def projectList = authService.obtainProjects()
        respond projectList, model:[projectCount: MaterialRequest.count(), poData: poData]
    }

}
