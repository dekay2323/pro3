package com.pro3.service

import grails.plugin.springsecurity.ui.RegisterCommand
import grails.plugin.springsecurity.ui.RegistrationCode
import grails.plugin.springsecurity.ui.ResetPasswordCommand
import grails.plugin.springsecurity.ui.strategy.DefaultRegistrationCodeStrategy
import grails.plugin.springsecurity.ui.strategy.RegistrationCodeStrategy
import groovy.transform.CompileStatic

/**
 * Created by demian on 2017-07-26.
 */
@CompileStatic
class RegistrationStrategyService extends DefaultRegistrationCodeStrategy {

    SecurityUiService securityUiService
    
    RegistrationCode sendEmailLink(String username, String emailAddress, String subject, Closure emailBodyGenerator) {
        securityUiService.sendEmailLink(username, emailAddress, subject, emailBodyGenerator)
    }
}
