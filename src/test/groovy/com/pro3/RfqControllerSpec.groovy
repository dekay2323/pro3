package com.pro3

import com.pro3.crud.RfqController
import grails.test.mixin.*
import spock.lang.*

@TestFor(RfqController)
@Mock(Rfq)
class RfqControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null

        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
        assert false, "TODO: Provide a populateValidParams() implementation for this generated test suite"
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.rfqList
            model.rfqCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.rfq!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def rfq = new Rfq()
            rfq.validate()
            controller.save(rfq)

        then:"The create view is rendered again with the correct model"
            model.rfq!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            rfq = new Rfq(params)

            controller.save(rfq)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/rfq/show/1'
            controller.flash.message != null
            Rfq.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def rfq = new Rfq(params)
            controller.show(rfq)

        then:"A model is populated containing the domain instance"
            model.rfq == rfq
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def rfq = new Rfq(params)
            controller.edit(rfq)

        then:"A model is populated containing the domain instance"
            model.rfq == rfq
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/rfq/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def rfq = new Rfq()
            rfq.validate()
            controller.update(rfq)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.rfq == rfq

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            rfq = new Rfq(params).save(flush: true)
            controller.update(rfq)

        then:"A redirect is issued to the show action"
            rfq != null
            response.redirectedUrl == "/rfq/show/$rfq.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/rfq/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def rfq = new Rfq(params).save(flush: true)

        then:"It exists"
            Rfq.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(rfq)

        then:"The instance is deleted"
            Rfq.count() == 0
            response.redirectedUrl == '/rfq/index'
            flash.message != null
    }
}
