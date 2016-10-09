<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Create Vddr</title>
</head>
<body>
<g:render template="/template/dropdownNav" />

<a href="#create-lineItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<g:render template="/template/topNav" />

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

        <g:render template="template/mrViewLineItems" model="[materialRequest: quote?.rfq?.materialRequest]" />

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
