package com.pro3

import com.pro3.list.RequestStatus
import com.pro3.main.MaterialRequest
import com.pro3.main.Project
import com.pro3.main.Rfq
import com.pro3.user.Client
import com.pro3.user.Account
import com.pro3.user.User
import grails.plugin.springsecurity.SpringSecurityService
import grails.test.mixin.TestFor
import spock.lang.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(AuthUserService)
class AuthUserServiceSpec extends Specification {
    def setup() {
        service.springSecurityService = Mock(SpringSecurityService)
    }

    def cleanup() {
    }

    void "obtainCurrentUser() should return logged on user"() {
        setup:
        User user = new User()
        user.id = 1
        when:
        service.obtainCurrentUser()
        2*service.springSecurityService.getCurrentUser() >> user
        then:
        service.obtainCurrentUser() == user
        service.obtainCurrentUser()?.id == 1
    }

    void "obtainCurrentUser() should null if no user logged on"() {
        when:
        service.obtainCurrentUser()
        then:
        service.obtainCurrentUser() == null
    }

    void "obtainAllClients() should return all clients on a user"() {
        setup:
        User user = new User()
        user.id = 1
        Account account = new Account()
        account.clients = [new Client(), new Client()]
        user.account = account
        when:
        1*service.springSecurityService.getCurrentUser() >> user
        then:
        service.obtainAllClients()?.size() == 2
    }

    void "obtainAllClients() should return empty list if no user or account"() {
        setup:
        User user = new User()
        user.id = 1
        Account account = new Account()
        expect:
        service.obtainAllClients()?.size() == 0
        when:
        2*service.springSecurityService.getCurrentUser() >> user
        then:
        service.obtainAllClients()?.size() == 0
        when:
        user.account = account
        then:
        service.obtainAllClients()?.size() == 0
    }

    void "obtainAllProjects() show all projects for all clients"() {
        setup:
        User user1 = new User()
        user1.id = 1
        Client client1 = new Client()
        Client client2 = new Client()
        client1.projects = [new Project(), new Project()]
        client2.projects = [new Project()]
        Account account = new Account()
        account.clients = [client1, client2]
        user1.account = account
        when:
        1*service.springSecurityService.getCurrentUser() >> user1
        then:
        service.obtainAllProjects()?.size() == 3
    }

    void "obtainAllProjects() handle client with no projects"() {
        setup:
        User user1 = new User()
        user1.id = 1
        Client client1 = new Client()
        Client client2 = new Client()
        client1.projects = [new Project(), new Project()]
        Account account = new Account()
        account.clients = [client1, client2]
        user1.account = account
        when:
        1*service.springSecurityService.getCurrentUser() >> user1
        then:
        service.obtainAllProjects()?.size() == 2
    }

    // @TODO : Why can I not do the create in constructor
    void "obtainAllRfqs() get all rfqs on a project"() {
        setup:
        User user1 = new User()
        user1.id = 1
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
        user1.account = account
        when:
        1*service.springSecurityService.getCurrentUser() >> user1
        then:
        service.obtainAllRfqs()?.size() == 2
    }

    void "obtainAllRfqs() should not show RFQ's that are on a MR in wrong status"() {
        setup:
        User user1 = new User()
        user1.id = 1
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
        user1.account = account
        when:
        1*service.springSecurityService.getCurrentUser() >> user1
        then:
        service.obtainAllRfqs()?.size() == 1
    }
}