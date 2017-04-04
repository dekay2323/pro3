package pro3

import com.pro3.*
import com.pro3.user.Role
import com.pro3.user.User
import com.pro3.user.UserRole

class BootStrap {

    def init = { servletContext ->
        Strategy.findOrSaveByName('Sole Source')
        Strategy.findOrSaveByName('Competitive Bid')

        LeadTime.findOrSaveByName('ARO')
        LeadTime.findOrSaveByName('ARD')

        RequestStatus.findOrSaveByName(RequestStatus.RequestStatusEnum.ADD_TO_PLAN)
        RequestStatus.findOrSaveByName(RequestStatus.RequestStatusEnum.START)
        RequestStatus.findOrSaveByName(RequestStatus.RequestStatusEnum.APPROVED_TO_PLAN)
        RequestStatus.findOrSaveByName(RequestStatus.RequestStatusEnum.RFQ_ISSUED)
        RequestStatus.findOrSaveByName(RequestStatus.RequestStatusEnum.BIDS_RECIEVED)
        RequestStatus.findOrSaveByName(RequestStatus.RequestStatusEnum.EVALUATION_COMPLETE)
        RequestStatus.findOrSaveByName(RequestStatus.RequestStatusEnum.PO_ISSUED)

        QuoteStatus.findOrSaveByName(QuoteStatus.QuoteStatusEnum.START)
        QuoteStatus.findOrSaveByName(QuoteStatus.QuoteStatusEnum.INTENTION_TO_BID)
        QuoteStatus.findOrSaveByName(QuoteStatus.QuoteStatusEnum.BID)
        QuoteStatus.findOrSaveByName(QuoteStatus.QuoteStatusEnum.NOT_BIDDING)
        QuoteStatus.findOrSaveByName(QuoteStatus.QuoteStatusEnum.PO_ISSUED)
        QuoteStatus.findOrSaveByName(QuoteStatus.QuoteStatusEnum.BID_OVER)

        Wbs.findOrSaveByCode('105.1')
        Wbs.findOrSaveByCode('105.2')
        Wbs.findOrSaveByCode('106.1')

        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
        def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
        def vendorRole = Role.findByAuthority('ROLE_VENDOR') ?: new Role(authority: 'ROLE_VENDOR').save(failOnError: true)

        Account accountSwat = Account.findByName('Swat') ?:
                new Account(name: 'Swat').save(failOnError: true)
        def adminUser = User.findByUsername('admin') ?: new User(
                username: 'admin',
                password: 'admin',
                account: accountSwat,
                enabled: true).save(failOnError: true)
        UserRole.findByUser(adminUser) ?: new UserRole(
                user: adminUser,
                role: adminRole).save(failOnError: true)

        User userUser = User.findByUsername('user1') ?: new User(
                username: 'user1',
                password: 'user1',
                account: accountSwat,
                enabled: true).save(failOnError: true)
        UserRole.findByUser(userUser) ?: new UserRole(
                user: userUser,
                role: userRole).save(failOnError: true)
        
        def vendorUser1 = User.findByUsername('vendor1') ?: new User(
                username: 'vendor1',
                password: 'vendor1',
                account: accountSwat,
                enabled: true).save(failOnError: true)
        UserRole.findByUser(vendorUser1) ?: new UserRole(
                user: vendorUser1,
                role: vendorRole).save(failOnError: true)
        
        def vendorUser2 = User.findByUsername('vendor2') ?: new User(
                username: 'vendor2',
                password: 'vendor2',
                account: accountSwat,
                enabled: true).save(failOnError: true)
        UserRole.findByUser(vendorUser2) ?: new UserRole(
                user: vendorUser2,
                role: vendorRole).save(failOnError: true)
        accountSwat.addToUsers(adminUser)
        accountSwat.addToUsers(userUser)
        accountSwat.addToUsers(vendorUser1)
        accountSwat.addToUsers(vendorUser2)
        accountSwat.save(failOnError: true)
    }

    def destroy = {
    }
}
