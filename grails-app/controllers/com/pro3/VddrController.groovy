package com.pro3

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class VddrController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Vddr.list(params), model:[vddrCount: Vddr.count()]
    }

    def show(Vddr vddr) {
        respond vddr
    }

    def create() {
        respond new Vddr(params)
    }

    @Transactional
    def save(Vddr vddr) {
        if (vddr == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (vddr.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond vddr.errors, view:'create'
            return
        }

        vddr.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'vddr.label', default: 'Vddr'), vddr.id])
                redirect vddr
            }
            '*' { respond vddr, [status: CREATED] }
        }
    }

    def edit(Vddr vddr) {
        respond vddr
    }

    @Transactional
    def update(Vddr vddr) {
        if (vddr == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (vddr.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond vddr.errors, view:'edit'
            return
        }

        vddr.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'vddr.label', default: 'Vddr'), vddr.id])
                redirect vddr
            }
            '*'{ respond vddr, [status: OK] }
        }
    }

    @Transactional
    def delete(Vddr vddr) {

        if (vddr == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        vddr.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'vddr.label', default: 'Vddr'), vddr.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'vddr.label', default: 'Vddr'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
