package com.pro3

import com.pro3.user.User
import grails.transaction.Transactional

import java.awt.print.Book

/**
 * Only returns domain object this user is attached to
 */
@Transactional
class AuthService {
    static transactional = false
    def springSecurityService

    def obtainCurrentUser() {
        springSecurityService.getCurrentUser()
    }

    def obtainProjects(def projectId) {
        User user = springSecurityService.getCurrentUser()
        Account account = user?.account

        def projectList = []
        account?.clients.each {client->
            projectList.addAll client.projects
        }
        projectList.asList()
    }

    def obtainClients() {
        User user = springSecurityService.getCurrentUser()
        Account account = user?.account

        account.clients.asList()
    }

}
