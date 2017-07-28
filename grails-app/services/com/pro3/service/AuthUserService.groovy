package com.pro3.service

import com.pro3.domain.main.MaterialRequest
import com.pro3.domain.main.Project
import com.pro3.domain.user.Account
import com.pro3.domain.user.Role
import com.pro3.domain.user.User
import com.pro3.domain.user.UserRole
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
        def User user = springSecurityService.getCurrentUser()
        user
    }
    
    List<User> obtainVendorsList() {
        def role = Role.findByAuthority('ROLE_VENDOR')
        def list = User.findAllByAccount(obtainAccount())
        list.findAll({ UserRole.findByRoleAndUser(role, it)})
    }
    
    def obtainUsersList() {
        def role = Role.findByAuthority('ROLE_USER')
        def list = User.findAllByAccount(obtainAccount())
        list.findAll({ UserRole.findByRoleAndUser(role, it)})
    }

    // @TODO should be able to handle several accounts
    Account obtainAccount() {
        User user = obtainCurrentUser()
        assert user
        Account account = user?.account
        assert account
        account
    }
    
    def obtainAllClients() {
        Account account = obtainAccount()
        assert account
        account?.clients ? account?.clients.asList() : []
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
            project?.requests?.each { MaterialRequest request ->
                if (request?.showsInRFQList()) {
                    rfqList.add(request.rfq)
                }
            }
        }
        rfqList.asList()
    }
    
    def obtainAllPos() {
        def projects = obtainAllProjects()
        def materialRequests = projects.collect {
            Project project ->
                project?.requests?.findAll()
        }.flatten()
        def purchaseOrderList = materialRequests.findAll {MaterialRequest mr ->
            mr?.purchaseOrder
        }.collect {it?.purchaseOrder}

        purchaseOrderList
    }
}
