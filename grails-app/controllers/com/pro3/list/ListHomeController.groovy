package com.pro3.list

import com.pro3.domain.main.PurchaseOrder
import com.pro3.domain.user.User
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
            indexUser()
        }
        if (SpringSecurityUtils.ifAllGranted('ROLE_VENDOR')) {
            indexVendor(max)
        }
    }

    private void indexVendor() {
        def quoteList = authVendorService.obtainAllQuotes()
        render view: 'indexVendor', model: [
                quoteList : quoteList,
                quoteCount: quoteList.size()]
    }

    private void indexUser() {
        def poData = [:]

        def poList = authUserService.obtainAllPos()
        def projectList = authUserService.obtainAllProjects()
        poData.ytd = poList?.size() // @TODO : YTD must be done
        poData.all = poList?.size()
        poData.allValue = 0
        poList?.each {poData.allValue += it?.quote?.obtainQuoteValue()}
        poData.ytdValue = poData.allValue // @TODO : YTD must be done

        render view: 'indexUser', model: [poList: poList, poData: poData, projectList: projectList]
    }

}
