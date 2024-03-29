package com.pro3.service

import com.pro3.domain.aux.LineItem
import com.pro3.domain.list.Wbs
import grails.transaction.Transactional

@Transactional(readOnly = true)
class LineItemService {

    @Transactional
    LineItem updateLineItem(LineItem lineItem, def code, def wbsId, def description, def quantity, def unitOfMeasure) {
        LineItem resultLineItem = lineItem

/*
        // @TODO : Request needs to be attached also
        def tempLineItem = new LineItem(code: code, wbs: Wbs.get(wbsId?.id), name: name, quantity: quantity, unitOfMeasure: unitOfMeasure)
        def valid = tempLineItem.validate()
        if (!valid) {
            // @TODO : This error message does not work yet
            respond tempLineItem.errors, view:'createLineItem'
            return
        }
*/

        resultLineItem.code = code
        if (wbsId?.id != 'null') {
            resultLineItem.wbs = Wbs.get(wbsId?.id)
        }
        resultLineItem.name = description
        if (quantity) {
            resultLineItem.quantity = new Integer(quantity)
        }
        resultLineItem.unitOfMeasure = unitOfMeasure

        resultLineItem.validate()
        if (resultLineItem.hasErrors()) {
            transactionStatus.setRollbackOnly()
        } else {
            resultLineItem.save(failOnError: true, flush: true)
        }
        
        resultLineItem
    }

}
