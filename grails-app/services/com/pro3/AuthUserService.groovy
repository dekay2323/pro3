package com.pro3

import com.pro3.user.User
import grails.plugin.springsecurity.annotation.Secured
import grails.transaction.Transactional

/**
 * Authenticating obtains for user
 */
@Secured(['ROLE_ADMIN', 'ROLE_USER'])
@Transactional(readOnly = true)
class AuthUserService {
    def springSecurityService

    def obtainCurrentUser() {
        springSecurityService.getCurrentUser()
    }

    def obtainAllProjects(def projectId) {
        def projectList = []
        obtainAllClients().each {client->
            projectList.addAll client.projects
        }
        projectList.asList()
    }

    def obtainAllClients() {
        User user = obtainCurrentUser()
        Account account = user?.account

        account.clients.asList()
    }

    def obtainAllRfqs() {
        def rfqList = []
        obtainAllProjects().each {project->
            rfqList.addAll(project.requests?.rfq)
        }
        rfqList.asList()
    }

}
