package com.pro3

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class LineItemController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond LineItem.list(params), model:[lineItemCount: LineItem.count()]
    }

    def show(LineItem lineItem) {
        respond lineItem
    }

    def create() {
        respond new LineItem(params)
    }

    @Transactional
    def save(LineItem lineItem) {
        if (lineItem == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (lineItem.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond lineItem.errors, view:'create'
            return
        }

        lineItem.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'lineItem.label', default: 'LineItem'), lineItem.id])
                redirect lineItem
            }
            '*' { respond lineItem, [status: CREATED] }
        }
    }

    def edit(LineItem lineItem) {
        respond lineItem
    }

    @Transactional
    def update(LineItem lineItem) {
        if (lineItem == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (lineItem.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond lineItem.errors, view:'edit'
            return
        }

        lineItem.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'lineItem.label', default: 'LineItem'), lineItem.id])
                redirect lineItem
            }
            '*'{ respond lineItem, [status: OK] }
        }
    }

    @Transactional
    def delete(LineItem lineItem) {

        if (lineItem == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        lineItem.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'lineItem.label', default: 'LineItem'), lineItem.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'lineItem.label', default: 'LineItem'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
