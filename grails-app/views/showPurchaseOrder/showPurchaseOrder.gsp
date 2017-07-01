<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>

<body>

<div id="edit-materialRequest" class="content scaffold-edit" role="main">
    <h1>Purchase Order</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${rfq?.materialRequest}">
        <ul class="errors" role="alert">
            <g:eachError bean="${rfq?.materialRequest}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>

    <g:hiddenField name="version" value="${purchaseOrder?.rfq?.materialRequest?.version}"/>

    <g:render template="template/mrShowGeneral" model="[materialRequest: purchaseOrder?.rfq?.materialRequest, client: client]"/>

    <g:render template="template/mrShowBidders" model="[materialRequest: purchaseOrder?.rfq?.materialRequest]"/>

    <h2>Technical Instructions</h2>

    <div class="fieldcontain"><label>Technical Instructions</label>${purchaseOrder?.rfq?.materialRequest?.technicalInstructions}</div>

    <g:render template="template/mrShowVDDR" model="[materialRequest: purchaseOrder?.rfq?.materialRequest]"/>


    <h2>Detailed Item Pricing</h2>
    <fieldset class="form">
        <table>
            <thead>
            <tr>
                <th>Code</th>
                <th>Wbs</th>
                <th>Description</th>
                <th>Quantity</th>
                <th>UoM</th>
                <th>Unit Price</th>
                <th>Total</th>
                <th>Lead Time</th>
                <th>Type</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${purchaseOrder?.quote?.quoteLineItems}" var="quoteLineItem" status="i">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td>${quoteLineItem?.code}</td>
                    <td>${quoteLineItem?.lineItem.wbs}</td>
                    <td>${quoteLineItem?.lineItem.name}</td>
                    <td>${quoteLineItem?.lineItem.quantity}</td>
                    <td>${quoteLineItem?.lineItem.unitOfMeasure}</td>
                    <td>${quoteLineItem?.price}</td>
                    <td>${quoteLineItem?.extendedPrice}</td>
                    <td>${quoteLineItem?.leadTime}</td>
                    <td>${quoteLineItem?.leadTimeType?.name}</td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </fieldset>

    <h2>Optional Line Items</h2>
    <fieldset class="form">
        <table>
            <thead>
            <tr>
                <th>Wbs</th>
                <th>Description</th>
                <th>Quantity</th>
                <th>UoM</th>
                <th>Unit Price</th>
                <th>Total</th>
                <th>Lead Time</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${purchaseOrder?.quote?.optionLineItems}" var="optionLineItem" status="i">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td>${optionLineItem?.lineItem?.wbs}</td>
                    <td>${optionLineItem?.lineItem?.name}</td>
                    <td>${optionLineItem?.lineItem?.quantity}</td>
                    <td>${optionLineItem?.lineItem?.unitOfMeasure}</td>
                    <td>${optionLineItem?.price}</td>
                    <td>${optionLineItem?.extendedPrice}</td>
                    <td>${optionLineItem?.leadTime}</td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </fieldset>
</div>
</body>
</html>

