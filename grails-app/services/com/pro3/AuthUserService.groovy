package com.pro3

import com.pro3.user.Account
import com.pro3.user.Role
import com.pro3.user.User
import com.pro3.user.UserRole
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
        Account.createCriteria().list{
            users{
                eq('id', obtainCurrentUser()?.id)
            }
        }[0]    
    }
    
    def obtainAllClients() {
        obtainAccount()?.clients ? obtainAccount()?.clients.asList() : []
    }

    def obtainAllProjects(def projectId) {
        def projectList = []
        obtainAllClients().each {client->
            if (client?.projects)
                projectList.addAll client?.projects
        }
        projectList.asList()
    }

}
