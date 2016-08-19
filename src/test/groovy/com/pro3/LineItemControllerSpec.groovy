package com.pro3

import grails.test.mixin.*
import spock.lang.*

@TestFor(LineItemController)
@Mock(LineItem)
class LineItemControllerSpec extends Specification {

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
            !model.lineItemList
            model.lineItemCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.lineItem!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'POST'
            def lineItem = new LineItem()
            lineItem.validate()
            controller.save(lineItem)

        then:"The create view is rendered again with the correct model"
            model.lineItem!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            lineItem = new LineItem(params)

            controller.save(lineItem)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/lineItem/show/1'
            controller.flash.message != null
            LineItem.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def lineItem = new LineItem(params)
            controller.show(lineItem)

        then:"A model is populated containing the domain instance"
            model.lineItem == lineItem
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def lineItem = new LineItem(params)
            controller.edit(lineItem)

        then:"A model is populated containing the domain instance"
            model.lineItem == lineItem
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'PUT'
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/lineItem/index'
            flash.message != null

        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def lineItem = new LineItem()
            lineItem.validate()
            controller.update(lineItem)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.lineItem == lineItem

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            lineItem = new LineItem(params).save(flush: true)
            controller.update(lineItem)

        then:"A redirect is issued to the show action"
            lineItem != null
            response.redirectedUrl == "/lineItem/show/$lineItem.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            request.method = 'DELETE'
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/lineItem/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def lineItem = new LineItem(params).save(flush: true)

        then:"It exists"
            LineItem.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(lineItem)

        then:"The instance is deleted"
            LineItem.count() == 0
            response.redirectedUrl == '/lineItem/index'
            flash.message != null
    }
}
