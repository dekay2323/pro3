package com.pro3

import com.pro3.user.User
import grails.transaction.Transactional

import java.awt.print.Book

/**
 * Only returns domain object this user is attached to
 */
@Transactional(readOnly = true)
class AuthService {
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
        User user = springSecurityService.getCurrentUser()
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
