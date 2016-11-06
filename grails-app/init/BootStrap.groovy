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
                password: 'admin23',
                enabled: true).save(failOnError: true)
        def adminUserRole = UserRole.findByUser(adminUser) ?: new UserRole(
                user: adminUser,
                role: adminRole).save(failOnError: true)
        Account account = Account.findByName('Swat') ?: new Account(
                name: 'Swat').save(failOnError: true)
        User userUser = User.findByUsername('user') ?: new User(
                username: 'user',
                password: 'user23',
                account: account,
                enabled: true).save(failOnError: true)
        def userUserRole = UserRole.findByUser(userUser) ?: new UserRole(
                user: userUser,
                role: userRole).save(failOnError: true)
        def vendorUser = User.findByUsername('vendor') ?: new User(
                username: 'vendor',
                password: 'vendor23',
                enabled: true).save(failOnError: true)
        def vendorUserRole = UserRole.findByUser(vendorUser) ?: new UserRole(
                user: vendorUser,
                role: vendorRole).save(failOnError: true)

        def joesFloor = Vendor.findOrSaveByName('Joe\'s Flooring')
        joesFloor.addToUsers(vendorUser)

        Vendor.findOrSaveByName('Demian\'s Hardwood')
    }
    def destroy = {
    }
}
