import com.pro3.embedded.Account
import com.pro3.selectors.LeadTime
import com.pro3.selectors.RequestStatus
import com.pro3.embedded.Vendor
import com.pro3.selectors.Strategy
import com.pro3.selectors.Wbs
import com.pro3.Role
import com.pro3.User
import com.pro3.UserRole

class BootStrap {

    def init = { servletContext ->
        Strategy.findOrSaveByName('Sole Source')
        Strategy.findOrSaveByName('Competitive Bid')

        LeadTime.findOrSaveByName('ARO')
        LeadTime.findOrSaveByName('ARD')

        RequestStatus.findOrSaveByName('Add to Plan')
        RequestStatus.findOrSaveByName('Start')
        RequestStatus.findOrSaveByName('Approved to Plan')
        RequestStatus.findOrSaveByName('RFQ Issued')
        RequestStatus.findOrSaveByName('Bids Recieved')
        RequestStatus.findOrSaveByName('Evaluation Complete')
        RequestStatus.findOrSaveByName('PO Issued')


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
                name: 'Swat')
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
