package pro3

import com.pro3.domain.aux.ProcurementType
import com.pro3.domain.list.LeadTimeType
import com.pro3.domain.list.ProcurementType
import com.pro3.domain.list.QuoteStatus
import com.pro3.domain.list.RequestStatus
import com.pro3.domain.list.Strategy
import com.pro3.domain.list.Wbs
import com.pro3.domain.user.Account
import com.pro3.domain.user.Role
import com.pro3.domain.user.User
import com.pro3.domain.user.UserRole

class BootStrap {

    def init = { servletContext ->
        Strategy.findOrSaveByName('Sole Source')
        Strategy.findOrSaveByName('Competitive Bid')

        LeadTimeType.findOrSaveByNameAndHelp('In Stock', 'Item is in stock no lead time')
        LeadTimeType.findOrSaveByNameAndHelp('ARO', 'After receipt of order')
        LeadTimeType.findOrSaveByNameAndHelp('ARAD', 'After receipt of approved drawing')

        ProcurementType.findOrSaveByName('Material')
        ProcurementType.findOrSaveByName('Service')
        ProcurementType.findOrSaveByName('Other')

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
        QuoteStatus.findOrSaveByName(QuoteStatus.QuoteStatusEnum.PO)
        QuoteStatus.findOrSaveByName(QuoteStatus.QuoteStatusEnum.PO_LOST)

        Wbs.findOrSaveByCode('105.1')
        Wbs.findOrSaveByCode('105.2')
        Wbs.findOrSaveByCode('106.1')

        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN', name: 'Admin').save(failOnError: true)
        def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER', name: 'User').save(failOnError: true)
        def vendorRole = Role.findByAuthority('ROLE_VENDOR') ?: new Role(authority: 'ROLE_VENDOR', name: 'Vendor').save(failOnError: true)

        Account testAccount = Account.findByName('TestAccount') ?:
                new Account(name: 'TestAccount').save(failOnError: true)
        def adminUser = User.findByUsername('admin') ?: new User(
                username: 'admin',
                email: 'contact@procurableapp.com',
                password: '23admin23',
                account: testAccount,
                enabled: true).save(failOnError: true)
        UserRole.findByUser(adminUser) ?: new UserRole(
                user: adminUser,
                role: adminRole).save(failOnError: true)

        User userUser1 = User.findByUsername('user1') ?: new User(
                username: 'user1',
                email: 'contact@procurableapp.com',
                password: 'user1',
                companyName: 'TestCompany', 
                contactName: 'Test',
                address: 'Brookpark DR\nCalgary',
                phoneNumber: '1234567',
                account: testAccount,
                enabled: true).save(failOnError: true)
        UserRole.findByUser(userUser1) ?: new UserRole(
                user: userUser1,
                role: userRole).save(failOnError: true)

        User userUser2 = User.findByUsername('user2') ?: new User(
                username: 'user2',
                email: 'contact@procurableapp.com',
                password: 'user2',
                companyName: 'TestCompany',
                contactName: 'Test',
                address: 'Brookpark DR\nCalgary',
                phoneNumber: '1234567',
                account: testAccount,
                enabled: true).save(failOnError: true)
        UserRole.findByUser(userUser2) ?: new UserRole(
                user: userUser2,
                role: userRole).save(failOnError: true)

        def vendorUser1 = User.findByUsername('vendor1') ?: new User(
                username: 'vendor1',
                email: 'contact@procurableapp.com',
                password: 'vendor1',
                companyName: 'TestVendor',
                contactName: 'Test',
                address: 'Brookpark DR\nCalgary',
                phoneNumber: '1234567',
                account: testAccount,
                enabled: true).save(failOnError: true)
        UserRole.findByUser(vendorUser1) ?: new UserRole(
                user: vendorUser1,
                role: vendorRole).save(failOnError: true)
        
        def vendorUser2 = User.findByUsername('vendor2') ?: new User(
                username: 'vendor2',
                email: 'contact@procurableapp.com',
                password: 'vendor2',
                account: testAccount,
                companyName: 'TestVendor',
                contactName: 'Test',
                address: 'Brookpark DR\nCalgary',
                phoneNumber: '1234567',
                enabled: true).save(failOnError: true)
        UserRole.findByUser(vendorUser2) ?: new UserRole(
                user: vendorUser2,
                role: vendorRole).save(failOnError: true)
        testAccount.addToUsers(adminUser)
        testAccount.addToUsers(userUser2)
        testAccount.addToUsers(vendorUser1)
        testAccount.addToUsers(vendorUser2)
        testAccount.save(failOnError: true)
    }

    def destroy = {
    }
}
