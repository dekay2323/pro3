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
        <div class="col-md-4">
            <div class="panel panel-default">
                <div class="panel-heading">Buyers Information (Invoice to)</div>
                <div class="panel-body">
                    ${purchaseOrder?.client?.name}<br />
                    ${purchaseOrder?.client?.address}
                    ${purchaseOrder?.client?.contactName}<br />
                    ${purchaseOrder?.client?.phoneNumber}<br />
                    ${purchaseOrder?.client?.email}<br />
                </div>
            </div>

            <div class="panel panel-default">
                <div class="panel-heading">Sellers Information</div>
                <div class="panel-body">
                    ${purchaseOrder?.vendor?.companyName}<br />
                    ${purchaseOrder?.vendor?.address}
                    ${purchaseOrder?.vendor?.contactName}<br />
                    ${purchaseOrder?.vendor?.phoneNumber}<br />
                    ${purchaseOrder?.vendor?.email}<br />
                </div>
            </div>
        </div>
        <div class="col-md-2">
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
                    <td>${purchaseOrder?.quote?.obtainLongestLeadTime()} weeks longest</td>
                </tr>
                <tr>
                    <td>Ship Date:</td>
                    <td>${purchaseOrder?.obtainShipDate()}</td>
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
            <em>Coming soon</em>
        </div>
    </div>
    
    <div class="row">
        <div class="col-md-12 text-center">
            <h1>Order Details</h1>
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Code</th>
                    <th>Tag No</th>
                    <th>Description</th>
                    <th>Qty</th>
                    <th>Unit</th>
                    <th>Unit Price</th>
                    <th>Total Price</th>
                </tr>
                </thead>
                <tbody>
                <g:each in="${purchaseOrder?.rfq?.materialRequest?.lineItems}" var="line">
                    <tr>
                        <td>${line?.code}</td>
                        <td></td>
                        <td>${line?.name}</td>
                        <td>${line?.quantity}</td>
                        <td>${line?.unitOfMeasure}</td>
                        <td>${purchaseOrder?.quote?.getQuoteForLineItem(line?.id)?.price}</td>
                        <td>${purchaseOrder?.quote?.getQuoteForLineItem(line?.id)?.extendedPrice}</td>
                    </tr>
                </g:each>
                <tr class="success">
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td><strong>TOTAL:</strong></td>
                    <td>${purchaseOrder?.quote?.obtainQuoteValue()}</td>
                </tr>

                <g:each in="${purchaseOrder?.quote?.optionLineItems}" var="line">
                    <tr class="warning">
                        <td></td>
                        <td></td>
                        <td>${line?.name}</td>
                        <td>${line?.quantity}</td>
                        <td>${line?.unitOfMeasure}</td>
                        <td>${line?.price}</td>
                        <td>${line?.extendedPrice}</td>
                    </tr>
                </g:each>

                </tbody>
            </table>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12 text-center">
            <h1>Special Instructions</h1>
            <em>Coming soon</em>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12 text-center">
            <h1>Payment Terms/Milestones</h1>
            <em>Coming soon</em>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12 text-center">
            <h1>Cancellations Terms/Milestones</h1>
            <em>Coming soon</em>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12 text-center">
            <h1>Inspection Level</h1>
            <em>Coming soon</em>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12 text-center">
            <h1>Warranty</h1>
            <em>Coming soon</em>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12 text-center">
            <div class="col-md-12 text-center">
                <h1>Attachments</h1>
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <g:sortableColumn property="filename" title="File"/>
                        <g:sortableColumn property="size" title="Size (KB)"/>
                        <g:sortableColumn property="lastModified" title="Last Modified"/>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${files}" var="file" status="i">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                            <td><g:link url="${file?.url}" target="_blank">${file?.filename}</g:link></td>
                            <td><g:formatNumber number="${file?.size/1000}" format="#,###,##0" /></td>
                            <td><g:formatDate format="yyyy-MM-dd" date="${file?.lastModified}"/></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>

