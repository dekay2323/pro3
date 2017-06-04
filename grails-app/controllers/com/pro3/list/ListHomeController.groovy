package com.pro3.list

import com.pro3.main.Project
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_VENDOR'])
@Transactional(readOnly = true)
class ListHomeController {
    def authUserService
    def authVendorService

    def index(Integer max) {
        log.debug("index 2() ${max}")
        params.max = Math.min(max ?: 10, 100)
        Project project = Project.get(1)
        render view: 'indexUser', model:[project: project]
    }

}
