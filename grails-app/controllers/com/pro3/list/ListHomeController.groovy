package com.pro3.list

import com.pro3.main.Project
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ListHomeController {
    def authUserService
    def authVendorService

    def index(Integer max) {
        log.debug("CHANGE ME FOR BUG")
        
        params.max = Math.min(max ?: 10, 100)
        Project project = Project.get(1)
        render view: 'indexUser', model:[project: project]
    }

}
