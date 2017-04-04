<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Bid</title>
</head>

<body>
<g:render template="/template/dropdownNav"/>
<g:render template="/template/topNavUser"/>

<div id="edit-materialRequest" class="content scaffold-edit" role="main">
    <h1>Bid</h1>
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

    <g:hiddenField name="version" value="${rfq?.materialRequest?.version}"/>

    <g:render template="template/mrShowGeneral" model="[materialRequest: rfq?.materialRequest, client: client]"/>

    <g:render template="template/mrShowBidders" model="[materialRequest: rfq?.materialRequest]"/>

    <h2>Technical Instructions</h2>

    <div class="fieldcontain"><label>Technical Instructions</label>${rfq?.materialRequest?.technicalInstructions}</div>

    <g:render template="template/mrShowVDDR" model="[materialRequest: rfq?.materialRequest]"/>


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
                <th>In PO</th>
            </tr>
            </thead>
            <tbody>
            <g:form controller="flowBid" action="selectVendor">
                <g:each in="${rfq?.materialRequest?.lineItems}" var="lineItem" status="i">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <td>${lineItem.code}</td>
                        <td>${lineItem.wbs}</td>
                        <td>${lineItem.description}</td>
                        <td>${lineItem.quantity}</td>
                        <td>${lineItem.unitOfMeasure}</td>
                        <g:each var="quote" in="${rfq?.quotes}">
                            <g:if test="${quote.isBid() || quote.isPO()}">
                                <g:set var="quoteLineItem" value="${quote.getQuoteForLineItem(lineItem?.id)}"/>
                                <g:if test="${quoteLineItem?.checkOff}">
                                    <td>${quoteLineItem?.price}</td>
                                    <td>${quoteLineItem?.extendedPrice}</td>
                                </g:if>
                                <g:else>
                                    <td>---</td>
                                    <td>---</td>
                                </g:else>
                            </g:if>
                            <g:else>
                                <td colspan="2">No Bid Yet</td>
                            </g:else>
                        </g:each>
                        <td><g:checkBox name="inPO-${quoteLineItem?.id}" value="${quoteLineItem?.inPO}"/></td>
                    </tr>
                </g:each>

                <tr>
                    <td colspan="5"></td>
                    <g:each var="quote" in="${rfq?.quotes}">
                        <g:if test="${quote.isBid()}">
                            <g:hiddenField name="quoteId" value="${quote.id}" />
                            <td colspan="2"><g:submitButton name="Award PO" /></td>
                        </g:if>
                        <g:elseif test="${quote.isPO()}">
                            <td colspan="2">PO Awarded</td>
                        </g:elseif>
                        <g:else>
                            <td colspan="2"></td>
                        </g:else>
                    </g:each>
                </tr>
            </g:form>
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
                <g:if test="${quote.isBid()}">
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
                </g:if>
            </g:each>

            </tbody>
        </table>
    </fieldset>
</div>
</body>
</html>

