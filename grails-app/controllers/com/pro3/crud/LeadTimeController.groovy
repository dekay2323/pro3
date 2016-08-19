package com.pro3.crud

import com.pro3.LeadTime

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class LeadTimeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond LeadTime.list(params), model:[leadTimeCount: LeadTime.count()]
    }

    def show(LeadTime leadTime) {
        respond leadTime
    }

    def create() {
        respond new LeadTime(params)
    }

    @Transactional
    def save(LeadTime leadTime) {
        if (leadTime == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (leadTime.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond leadTime.errors, view:'create'
            return
        }

        leadTime.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'leadTime.label', default: 'LeadTime'), leadTime.id])
                redirect leadTime
            }
            '*' { respond leadTime, [status: CREATED] }
        }
    }

    def edit(LeadTime leadTime) {
        respond leadTime
    }

    @Transactional
    def update(LeadTime leadTime) {
        if (leadTime == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (leadTime.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond leadTime.errors, view:'edit'
            return
        }

        leadTime.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'leadTime.label', default: 'LeadTime'), leadTime.id])
                redirect leadTime
            }
            '*'{ respond leadTime, [status: OK] }
        }
    }

    @Transactional
    def delete(LeadTime leadTime) {

        if (leadTime == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        leadTime.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'leadTime.label', default: 'LeadTime'), leadTime.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'leadTime.label', default: 'LeadTime'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
