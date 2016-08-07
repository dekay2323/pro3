import com.pro3.type.*
import com.pro3.*

class BootStrap {

    def init = { servletContext ->
        def strategy = Strategy.findOrSaveByName('Sole Source')
        Strategy.findOrSaveByName('Competitive Bid')
        def client = Client.findOrSaveByName('ACME Oilfield Ltd')
        Client.findOrSaveByName('Charlie Construction')
        def leadTime = LeadTime.findOrSaveByName('ARO')
        LeadTime.findOrSaveByName('ARD')
        def status = RequestStatus.findOrSaveByName('Add to Plan')
        RequestStatus.findOrSaveByName('Approved to Plan')
        RequestStatus.findOrSaveByName('RFQ Issued')
        RequestStatus.findOrSaveByName('Bids Recieved')
        RequestStatus.findOrSaveByName('Evaluation Complete')
        RequestStatus.findOrSaveByName('PO Issued')
        def vendor = Vendor.findOrSaveByName('Joe\'s Flooring')
        Vendor.findOrSaveByName('Demian\'s Hardwood')
        def wbs = Wbs.findOrSaveByCode('105.1')
        Wbs.findOrSaveByCode('105.2')
        Wbs.findOrSaveByCode('106.1')
    }
    def destroy = {
    }
}
