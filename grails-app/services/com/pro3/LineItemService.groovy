package com.pro3

import com.pro3.aux.LineItem
import com.pro3.list.Wbs
import grails.transaction.Transactional

@Transactional(readOnly = true)
class LineItemService {

    @Transactional
    LineItem updateLineItem(LineItem lineItem, def code, def wbsId, def description, def quantity, def unitOfMeasure) {
        LineItem resultLineItem = lineItem

/*
        // @TODO : Request needs to be attached also
        def tempLineItem = new LineItem(code: code, wbs: Wbs.get(wbsId?.id), description: description, quantity: quantity, unitOfMeasure: unitOfMeasure)
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
        resultLineItem.description = description
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
