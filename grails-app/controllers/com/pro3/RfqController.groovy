package com.pro3

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class RfqController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Rfq.list(params), model:[rfqCount: Rfq.count()]
    }

    def show(Rfq rfq) {
        respond rfq
    }

    def create() {
        respond new Rfq(params)
    }

    @Transactional
    def save(Rfq rfq) {
        if (rfq == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (rfq.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond rfq.errors, view:'create'
            return
        }

        rfq.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'rfq.label', default: 'Rfq'), rfq.id])
                redirect rfq
            }
            '*' { respond rfq, [status: CREATED] }
        }
    }

    def edit(Rfq rfq) {
        respond rfq
    }

    @Transactional
    def update(Rfq rfq) {
        if (rfq == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (rfq.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond rfq.errors, view:'edit'
            return
        }

        rfq.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'rfq.label', default: 'Rfq'), rfq.id])
                redirect rfq
            }
            '*'{ respond rfq, [status: OK] }
        }
    }

    @Transactional
    def delete(Rfq rfq) {

        if (rfq == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        rfq.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'rfq.label', default: 'Rfq'), rfq.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'rfq.label', default: 'Rfq'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
