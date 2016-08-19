package com.pro3

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class QuoteLineItemController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond QuoteLineItem.list(params), model:[quoteLineItemCount: QuoteLineItem.count()]
    }

    def show(QuoteLineItem quoteLineItem) {
        respond quoteLineItem
    }

    def create() {
        respond new QuoteLineItem(params)
    }

    @Transactional
    def save(QuoteLineItem quoteLineItem) {
        if (quoteLineItem == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (quoteLineItem.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond quoteLineItem.errors, view:'create'
            return
        }

        quoteLineItem.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'quoteLineItem.label', default: 'QuoteLineItem'), quoteLineItem.id])
                redirect quoteLineItem
            }
            '*' { respond quoteLineItem, [status: CREATED] }
        }
    }

    def edit(QuoteLineItem quoteLineItem) {
        respond quoteLineItem
    }

    @Transactional
    def update(QuoteLineItem quoteLineItem) {
        if (quoteLineItem == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (quoteLineItem.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond quoteLineItem.errors, view:'edit'
            return
        }

        quoteLineItem.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'quoteLineItem.label', default: 'QuoteLineItem'), quoteLineItem.id])
                redirect quoteLineItem
            }
            '*'{ respond quoteLineItem, [status: OK] }
        }
    }

    @Transactional
    def delete(QuoteLineItem quoteLineItem) {

        if (quoteLineItem == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        quoteLineItem.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'quoteLineItem.label', default: 'QuoteLineItem'), quoteLineItem.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'quoteLineItem.label', default: 'QuoteLineItem'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
