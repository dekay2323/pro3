package com.pro3

import grails.test.mixin.Mock
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.Specification
import spock.lang.Unroll

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
        def validateable = new Project()
        then:
        validateable.validate() == false
        validateable.hasErrors() == true
        validateable.errors.errorCount > 0
    }


    def "can save minimal object"() {
        when:
        def validateable = new Project(
                id: 1,
                projectNumber: "projectNumber",
                name: "name",
                client: Mock(Client))
        then:
        validateable.validate() == true
        validateable.hasErrors() == false
        validateable.errors.errorCount == 0
    }

    def "getBudget() adds up all the request budgets"() {
        when:
        Project project = new Project(id: 1)
        project.requests = [new MaterialRequest(budget: 10), new MaterialRequest(budget: 5)]
        then:
        project.getBudget() == 15
    }

}