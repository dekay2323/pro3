package com.pro3.list

import com.pro3.AuthUserService
import com.pro3.AuthVendorService
import com.pro3.main.MaterialRequest
import com.pro3.main.Project
import com.pro3.main.Quote
import grails.plugin.springsecurity.SpringSecurityUtils
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.*

@TestFor(ListHomeController)
@Mock([AuthUserService, AuthVendorService, MaterialRequest])
class ListHomeControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "index() for an ROLE_ADMIN user should show indexUser"() {
        setup:
        params.id = 1
        SpringSecurityUtils.metaClass.'static'.ifAllGranted = { String role ->
            (role == 'ROLE_ADMIN') ? true : false
        }
        controller.authUserService = Mock(AuthUserService)
        when:
        controller.index()
        then:
        1*controller.authUserService.obtainAllProjects(1) >> [Mock(Project),Mock(Project)]
        view == '/listHome/indexUser'
        model.projectList.size() == 2
        model.projectCount == 2
        model.poData.ytd == 1
        model.poData.ytdValue == 1
        model.poData.all == 1
        model.poData.allValue == 1
    }

    void "index() for an ROLE_USER user should show indexUser"() {
        setup:
        params.id = 1
        SpringSecurityUtils.metaClass.'static'.ifAllGranted = { String role ->
            (role == 'ROLE_USER') ? true : false
        }
        controller.authUserService = Mock(AuthUserService)
        when:
        controller.index()
        then:
        1*controller.authUserService.obtainAllProjects(1) >> [Mock(Project), Mock(Project)]
        view == '/listHome/indexUser'
        model.projectList.size() == 2
        model.projectCount == 2
        model.poData.ytd == 1
        model.poData.ytdValue == 1
        model.poData.all == 1
        model.poData.allValue == 1
    }

    void "index() for an ROLE_VENDOR user should show indexUser"() {
        setup:
        params.id = 1
        SpringSecurityUtils.metaClass.'static'.ifAllGranted = { String role ->
            (role == 'ROLE_VENDOR') ? true : false
        }
        controller.authVendorService = Mock(AuthVendorService)
        when:
        controller.index()
        then:
        1*controller.authVendorService.obtainAllQuotes() >> [Mock(Quote), Mock(Quote)]
        view == '/listHome/indexVendor'
        model.quoteList.size() == 2
        model.quoteCount == 2
    }
}