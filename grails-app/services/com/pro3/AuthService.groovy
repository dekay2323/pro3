package com.pro3

import com.pro3.embedded.Account
import grails.transaction.Transactional

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

    // @TODO : check projectid and user
    def obtainProjects() {
        User user = springSecurityService.getCurrentUser()
        Account account = user?.account

        def projectList = []
        account?.clients.each {client->
            projectList.addAll client.projects
        }
        projectList
    }

}
