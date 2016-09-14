<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'lineItem.label', default: 'LineItem')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>
<a href="#create-lineItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}">Home</a>
        <li><g:link controller="listProject" action="index">Project List</g:link></li>
    </ul>
</div>
<div id="create-lineItem" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]" /></h1>
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
                <f:field property="code" />
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
