<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>

<body>
<br />
<br />
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h1>PO Number : ${purchaseOrder?.id}</h1>
        </div>
    </div>
    
    <div class="row">
        <div class="col-md-4">
        </div>
        <div class="col-md-4">
        </div>
        <div class="col-md-4 text-right">
            <h1>${purchaseOrder?.account}</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12 text-center">
            <h1>Purchase Order</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6">
            <h2>Buyers Information (Invoice to)</h2><br />
            ${purchaseOrder?.client?.name}<br />
            ${purchaseOrder?.client?.address}
            ${purchaseOrder?.client?.contactName}<br />
            ${purchaseOrder?.client?.phoneNumber}<br />
            ${purchaseOrder?.client?.email}<br />

            <h2>Sellers Information</h2><br />
            [Company Name]<br />
            ${purchaseOrder?.vendor?.address}
            ${purchaseOrder?.vendor?.contactName}<br />
            ${purchaseOrder?.vendor?.phoneNumber}<br />
            ${purchaseOrder?.vendor?.email}<br />
        </div>
        <div class="col-md-6">
            <table class="table table-bordered">
                <tr>
                    <td>Date:</td>
                    <td>${purchaseOrder?.dateCreated}</td>
                </tr>
                <tr>
                    <td>PO Number:</td>
                    <td>${purchaseOrder?.id}</td>
                </tr>
                <tr>
                    <td>Project:</td>
                    <td>${purchaseOrder?.rfq?.materialRequest?.account?.name}</td>
                </tr>
                <tr>
                    <td>Lead Time:</td>
                    <td>[This should be the longest lead from vendors quote]</td>
                </tr>
                <tr>
                    <td>Ship Date:</td>
                    <td>[This is a calculation. PO date plus max lead time ]</td>
                </tr>
                <tr>
                    <td>Inco Terms:</td>
                    <td></td>
                </tr>
                <tr>
                    <td>Pickup Location:</td>
                    <td></td>
                </tr>
                <tr>
                    <td>Ship to:</td>
                    <td></td>
                </tr>
                <tr>
                    <td>AFE:</td>
                    <td></td>
                </tr>
                <tr>
                    <td>G/L Code:</td>
                    <td></td>
                </tr>
                <tr>
                    <td>Quote No:</td>
                    <td></td>
                </tr>
            </table>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12 text-center">
            Seller has read and agrees to be bound by the Purchase Order including [Client Name]'s Terms and Conditions. 
            Sellers’s written acceptance, shipment of any Goods or commencement of performance of work hereunder shall be deemed acceptance of this Purchase Order.
        </div>
    </div>

    <div class="row">
        <div class="col-md-12 text-center">
            <h1>Approvals</h1>
        </div>
    </div>
    
    <div class="row">
        <div class="col-md-12 text-center">
            <h1>Order Details</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12 text-center">
            <h1>Special Instructions</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12 text-center">
            <h1>Payment Terms/Milestones</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12 text-center">
            <h1>Cancellations Terms/Milestones</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12 text-center">
            <h1>Inspection Level</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12 text-center">
            <h1>Warranty</h1>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12 text-center">
            <h1>Attachments</h1>
        </div>
    </div>
</div>

</body>
</html>

