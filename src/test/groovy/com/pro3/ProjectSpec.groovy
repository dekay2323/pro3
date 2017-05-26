package com.pro3

import com.pro3.main.MaterialRequest
import com.pro3.main.Project
import com.pro3.user.Client
import grails.test.mixin.Mock
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(DomainClassUnitTestMixin)
@Mock(Project)
class ProjectSpec extends Specification {
    def setup() {
    }

    def cleanup() {
    }

    def "constraint errors"() {
        when:
        def obj = new Project()
        then:
        obj.validate() == false
        obj.hasErrors() == true
        obj.errors.errorCount == 3
        obj.errors['projectNumber']?.objectName == 'com.pro3.main.Project'
        obj.errors['name']?.objectName == 'com.pro3.main.Project'
        obj.errors['client']?.objectName == 'com.pro3.main.Project'
    }


    def "can save minimal object"() {
        when:
        def obj = new Project(
                id: 1,
                projectNumber: "projectNumber",
                name: "name",
                client: Mock(Client))
        then:
        obj.validate() == true
        obj.hasErrors() == false
        obj.errors.errorCount == 0
    }

    def "getBudget() adds up all the request budgets"() {
        when:
        Project project = new Project(id: 1)
        project.requests = [new MaterialRequest(budget: 10), new MaterialRequest(budget: 5)]
        then:
        project.getBudget() == 15
    }

}