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

    User obtainCurrentUser() {
        springSecurityService.getCurrentUser()
    }

    def obtainAllClients() {
        User user = obtainCurrentUser()
        def accounts = Account.createCriteria().list{ 
            users{ 
                eq('id', user.id) 
            }	
        }

        // @TODO should be able to handle several accounts
        if (accounts[0]?.clients)
            accounts[0]?.clients?.asList()
        else
            []
    }

    def obtainAllProjects(def projectId) {
        def projectList = []
        obtainAllClients().each {client->
            if (client?.projects)
                projectList.addAll client?.projects
        }
        projectList.asList()
    }

    def obtainAllRfqs() {
        def rfqList = []
        obtainAllProjects().each {project->
            project?.requests?.each {MaterialRequest request ->
                if (request?.showsInRFQList()) {
                    rfqList.add(request.rfq)
                }
            }
        }
        rfqList.asList()
    }

}
