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
        QuoteStatus.findOrSaveByName(QuoteStatus.QuoteStatusEnum.BID)

        Wbs.findOrSaveByCode('105.1')
        Wbs.findOrSaveByCode('105.2')
        Wbs.findOrSaveByCode('106.1')

        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
        def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
        def vendorRole = Role.findByAuthority('ROLE_VENDOR') ?: new Role(authority: 'ROLE_VENDOR').save(failOnError: true)

        def adminUser = User.findByUsername('admin') ?: new User(
                username: 'admin',
                password: 'admin',
                enabled: true).save(failOnError: true)
        def adminUserRole = UserRole.findByUser(adminUser) ?: new UserRole(
                user: adminUser,
                role: adminRole).save(failOnError: true)

        Account account = Account.findByName('Swat') ?: new Account(
                name: 'Swat').save(failOnError: true)
        User userUser = User.findByUsername('user') ?: new User(
                username: 'user',
                password: 'user',
                account: account,
                enabled: true).save(failOnError: true)
        def userUserRole = UserRole.findByUser(userUser) ?: new UserRole(
                user: userUser,
                role: userRole).save(failOnError: true)

        Account account1 = Account.findByName('Suncor') ?: new Account(
                name: 'Suncor').save(failOnError: true)
        User userUser1 = User.findByUsername('user1') ?: new User(
                username: 'user1',
                password: 'user1',
                account: account1,
                enabled: true).save(failOnError: true)
        def userUserRole1 = UserRole.findByUser(userUser1) ?: new UserRole(
                user: userUser1,
                role: userRole).save(failOnError: true)

        def joesFloor = Vendor.findOrSaveByName('Joe\'s Flooring')
        def vendorUser1 = User.findByUsername('vendor1') ?: new User(
                username: 'vendor1',
                password: 'vendor1',
                vendor: joesFloor,
                enabled: true).save(failOnError: true)
        def vendorUserRole1 = UserRole.findByUser(vendorUser1) ?: new UserRole(
                user: vendorUser1,
                role: vendorRole).save(failOnError: true)

        def demiansHardwood = Vendor.findOrSaveByName('Demian\'s Hardwood')
        def vendorUser2 = User.findByUsername('vendor2') ?: new User(
                username: 'vendor2',
                password: 'vendor2',
                vendor: demiansHardwood,
                enabled: true).save(failOnError: true)
        def vendorUserRole2 = UserRole.findByUser(vendorUser1) ?: new UserRole(
                user: vendorUser2,
                role: vendorRole).save(failOnError: true)
    }

    def destroy = {
    }
}
