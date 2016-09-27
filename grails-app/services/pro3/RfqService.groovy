package pro3

import com.pro3.MaterialRequest
import com.pro3.RequestStatus
import com.pro3.Rfq
import grails.transaction.Transactional

@Transactional
class RfqService {

    def createRfqs(def materialRequestId, def rfqName) {
        // Modify the Material Request
        def materialRequest = MaterialRequest.get(materialRequestId)
        materialRequest.setRfqName(rfqName)
        materialRequest.setStatus(RequestStatus.findByName('RFQ Issued'))
        materialRequest.save flush:true

        materialRequest.bidders.each {vendor->
            def rfq = new Rfq(vendor: vendor, )
        }

        materialRequest
    }
}
