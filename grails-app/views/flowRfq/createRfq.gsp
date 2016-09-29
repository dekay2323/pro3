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
    <h1>Create Rfq</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.materialRequest}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.materialRequest}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form action="saveRfq">
        <fieldset class="form">
            <f:with bean="materialRequest">
                <g:hiddenField name="materialRequestId" value="${materialRequest?.id}" />
                <div class="fieldcontain"><label>Rfq Name</label><g:field type="text" name="rfqName" /></div>

                <div class="fieldcontain"><label>For Vendors</label>${vendors}</div>
            </f:with>
        </fieldset>

        <fieldset class="buttons">
            <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
        </fieldset>
    </g:form>
</div>
</body>
</html>
