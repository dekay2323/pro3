package com.pro3.list

import com.pro3.MaterialRequest
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class ListHomeController {
    def authService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def poData = [:]
        poData.ytd = 1
        poData.ytdValue = 1
        poData.all = 1
        poData.allValue = 1
        def projectList = authService.obtainAllProjects(params?.id)
        respond projectList, model:[projectCount: MaterialRequest.count(), poData: poData]
    }

}
