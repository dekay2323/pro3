package com.pro3.service

import com.pro3.domain.user.Account
import com.pro3.domain.user.Role
import com.pro3.domain.user.User
import com.pro3.domain.user.UserRole
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserService {

    @Transactional
    public User createNewUser(String username, String email, String accountId) {
        log.debug("createNewUser() ${username}, ${email}, ${accountId}")
        assert accountId
        assert username
        assert email
        def userRole = Role.findByAuthority('ROLE_USER')

        User user = new User(
                username: username,
                email: email,
                password: 'temp',
                account: Account.get(accountId)
        ).save(failOnError: true, flush: true)
        UserRole.findByUser(user) ?: new UserRole(
                user: user,
                role: userRole).save(failOnError: true)
        user
    }


}
