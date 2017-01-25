package com.pro3

import grails.test.mixin.integration.Integration
import spock.lang.Specification


/**
 * Created by demian on 2016-12-15.
 */
@Integration
class MailSpec extends Specification {
    def mailService
    
    def "Check mailing works"() {
        expect:
        sendMail {
            from "admin@mysystem.com"
            subject "New user"
            text "A new user has been created"
        }
    }
}