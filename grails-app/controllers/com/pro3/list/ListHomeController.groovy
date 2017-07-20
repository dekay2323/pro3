package com.pro3.list

import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

@Secured(['ROLE_ADMIN', 'ROLE_USER', 'ROLE_VENDOR'])
@Transactional(readOnly = true)
class ListHomeController {
    def authUserService
    def authVendorService

    def index(Integer max) {
        log.debug("index()")
        if (SpringSecurityUtils.ifAllGranted('ROLE_ADMIN') || SpringSecurityUtils.ifAllGranted('ROLE_USER')) {
            params.max = Math.min(max ?: 10, 100)
            def poData = [:]
            poData.ytd = 1
            poData.ytdValue = 1
            poData.all = 1
            poData.allValue = 1
            def projectList = authUserService.obtainAllProjects(params?.id)
            render view: 'indexUser', model:[
                    projectList: projectList,
                    projectCount: projectList.size(),
                    poData: poData]
        }
        if (SpringSecurityUtils.ifAllGranted('ROLE_VENDOR')) {
            params.max = Math.min(max ?: 10, 100)
            def quoteList = authVendorService.obtainAllQuotes()
            render view: 'indexVendor', model:[
                    quoteList: quoteList,
                    quoteCount: quoteList.size()]
        }
    }

}
