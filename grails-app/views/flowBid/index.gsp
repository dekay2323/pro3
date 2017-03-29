<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Edit Material Request</title>
</head>

<body>
<g:render template="/template/dropdownNav"/>
<g:render template="/template/topNavUser"/>

<div id="edit-materialRequest" class="content scaffold-edit" role="main">
    <h1>Bids</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.rfq}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.rfq}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    
    <h2>Detailed Item Pricing</h2>
    <fieldset class="form">
    <table>
        <thead>
        <tr>
            <th colspan="5"></th>
            <g:each var="quote" in="${rfq?.quotes}">
                <th colspan="2">${quote?.vendor}</th>
            </g:each>
        </tr>
        <tr>
            <th>Code</th>
            <th>Wbs</th>
            <th>Description</th>
            <th>Quantity</th>
            <th>UoM</th>
            <g:each var="quote" in="${rfq?.quotes}">
                <th>Unit Price</th>
                <th>Total</th>
            </g:each>
        </tr>
        </thead>
        <tbody>
        <g:each in="${rfq?.materialRequest?.lineItems}" var="lineItem" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${lineItem.code}</td>
                <td>${lineItem.wbs}</td>
                <td>${lineItem.description}</td>
                <td>${lineItem.quantity}</td>
                <td>${lineItem.unitOfMeasure}</td>
                <g:each var="quote" in="${rfq?.quotes}">
                    <g:set var="quoteLineItem" value="${quote.getQuoteForLineItem(lineItem?.id)}"/>
                    <td>${quoteLineItem?.price}</td>
                    <td>${quoteLineItem?.extendedPrice}</td>
                </g:each>
            </tr>
        </g:each>
        </tbody>
    </table>
    </fieldset>

    <h2>Optional Line Items</h2>
    <fieldset class="form">
    <table>
        <thead>
        <th>Vendor</th>
        <th>Description</th>
        <th>Quantity</th>
        <th>UoM</th>
        <th>Unit Price</th>
        <th>Total</th>
        </thead>
        <tbody>
        <g:each in="${rfq?.quotes}" var="quote">
            <g:each in="${quote?.optionLineItems}" var="optionLineItem" status="i">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td>${quote?.vendor}</td>
                    <td>${optionLineItem.description}</td>
                    <td>${optionLineItem.quantity}</td>
                    <td>${optionLineItem.unitOfMeasure}</td>
                    <td>${optionLineItem.price}</td>
                    <td>${optionLineItem.extendedPrice}</td>
                </tr>
            </g:each>
        </g:each>

        </tbody>
    </table>
    </fieldset>

</div>
</body>
</html>
