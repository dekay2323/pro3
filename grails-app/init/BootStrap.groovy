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

        RequestStatus.findOrSaveByName('Add to Plan')
        RequestStatus.findOrSaveByName('Start')
        RequestStatus.findOrSaveByName('Approved to Plan')
        RequestStatus.findOrSaveByName('RFQ Issued')
        RequestStatus.findOrSaveByName('Bids Recieved')
        RequestStatus.findOrSaveByName('Evaluation Complete')
        RequestStatus.findOrSaveByName('PO Issued')

        Vendor.findOrSaveByName('Joe\'s Flooring')
        Vendor.findOrSaveByName('Demian\'s Hardwood')

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
        def userUser = User.findByUsername('user') ?: new User(
                username: 'user',
                password: 'user23',
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
    }
    def destroy = {
    }
}
