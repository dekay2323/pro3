<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Create Line Item</title>
</head>
<body>
<g:render template="/template/dropdownNav" />
<g:render template="/template/topNav" />

<div id="create-lineItem" class="content scaffold-create" role="main">
    <h1>Create Line Item</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.lineItem}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.lineItem}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form action="saveLineItem">
        <fieldset class="form">
            <f:with bean="lineItem">
                <g:hiddenField name="request" value="${lineItem?.request?.id}" />
                <f:field property="wbs" />
                <f:field property="description" />
                <f:field property="quantity" />
                <f:field property="unitOfMeasure" />
            </f:with>

        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
        </fieldset>
    </g:form>
</div>
</body>
</html>
