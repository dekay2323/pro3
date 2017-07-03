package com.pro3.service

import com.pro3.domain.list.RequestStatus
import com.pro3.domain.main.MaterialRequest
import com.pro3.domain.main.Project
import com.pro3.domain.main.Rfq
import com.pro3.domain.user.Client
import com.pro3.domain.user.Account
import com.pro3.domain.user.User
import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(AuthUserService)
@Mock([Account, User])
class AuthUserServiceSpec extends Specification {
    def setup() {
        service.springSecurityService = Mock(SpringSecurityService)
    }

    def cleanup() {
    }

    void "obtainCurrentUser() should return logged on user"() {
        setup:
        User user = new User()
        when:
        service.obtainCurrentUser()
        1*service.springSecurityService.getCurrentUser() >> user
        then:
        service.obtainCurrentUser() == user
    }

    void "obtainCurrentUser() should null if no user logged on"() {
        when:
        service.obtainCurrentUser()
        then:
        service.obtainCurrentUser() == null
    }
    
    void "obtainAccount() should return an account"() {
        User user = Mock()
        when:
        1*service.springSecurityService.getCurrentUser() >> user
        1*user.obtainAccount() >> new Account()
        Account account = service.obtainAccount()
        then:
        account
    }

    void "obtainAllClients() should return all clients on a user"() {
        setup:
        User user = Mock()
        Account account = new Account()
        account.clients = [new Client(), new Client()]
        account.addToUsers(user)
        when:
        1*service.springSecurityService.getCurrentUser() >> user
        1*user.obtainAccount() >> account
        then:
        service.obtainAllClients()?.size() == 2
    }

    // @TODO : Why can I not do the create in constructor
    void "obtainAllRfqs() get all rfqs on a project"() {
        setup:
        User user = Mock()
        Client client1 = new Client()
        Client client2 = new Client()
        Project project1 = new Project()
        MaterialRequest mr1 = new MaterialRequest()
        mr1.rfq = new Rfq()
        mr1.status = new RequestStatus(name: RequestStatus.RequestStatusEnum.RFQ_ISSUED.name())
        MaterialRequest mr2 = new MaterialRequest()
        mr2.rfq = new Rfq()
        mr2.status = new RequestStatus(name: RequestStatus.RequestStatusEnum.RFQ_ISSUED.name())
        project1.requests = [mr1]
        Project project2 = new Project()
        project2.requests = [mr2]
        client1.projects = [project1]
        client2.projects = [project2]
        Account account = new Account()
        account.clients = [client1, client2]
        when:
        1*service.springSecurityService.getCurrentUser() >> user
        1*user.obtainAccount() >> account
        then:
        service.obtainAllRfqs()?.size() == 2
    }

    void "obtainAllRfqs() should not show RFQ's that are on a MR in wrong status"() {
        setup:
        User user = Mock()
        user.id = 1
        Client client1 = new Client()
        Client client2 = new Client()
        Project project1 = new Project()
        MaterialRequest mr1 = new MaterialRequest()
        mr1.rfq = new Rfq()
        mr1.status = new RequestStatus(name: RequestStatus.RequestStatusEnum.START.name())
        MaterialRequest mr2 = new MaterialRequest()
        mr2.rfq = new Rfq()
        mr2.status = new RequestStatus(name: RequestStatus.RequestStatusEnum.RFQ_ISSUED.name())
        project1.requests = [mr1]
        Project project2 = new Project()
        project2.requests = [mr2]
        client1.projects = [project1]
        client2.projects = [project2]
        Account account = new Account()
        account.clients = [client1, client2]
        user.account = account
        when:
        1*service.springSecurityService.getCurrentUser() >> user
        1*user.obtainAccount() >> account
        then:
        service.obtainAllRfqs()?.size() == 1
    }
}