<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Edit Quote</title>
</head>
<body>
<g:render template="/template/dropdownNav" />
<g:render template="/template/topNavVendor" />

<div id="create-lineItem" class="content scaffold-create" role="main">
    <h1>Edit Quote</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.quote}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.quote}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>

    <g:form action="saveQuote" id="${quote.id}"  method="PUT">
        <g:hiddenField name="version" value="${quote?.version}" />

        <g:render template="template/mrViewGeneral" model="[materialRequest: quote?.rfq?.materialRequest, client: quote?.rfq?.materialRequest?.project?.client]" />

        <g:render template="template/mrEditQuote" model="[quote: quote]" />

        <g:render template="template/mrEditQuoteLineItems" model="[quote: quote]" />

        <h2>Technical Instructions</h2>
        <div class="fieldcontain"><label>Technical Instructions</label>${quote?.rfq?.materialRequest?.technicalInstructions}</div>

        <g:render template="template/mrViewVDDR" model="[materialRequest: quote?.rfq?.materialRequest]" />

        <fieldset class="buttons">
            <input class="save" type="submit" value="Update" />
        </fieldset>
    </g:form>
</div>
</body>
</html>
