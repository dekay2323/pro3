<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
</head>
<body>

<div id="create-lineItem" class="container" role="main">
    <h1>Create Vddr</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.vddr}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.vddr}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form action="saveVddr">
        <fieldset class="form">
            <g:hiddenField name="request" value="${materialRequestId}" />
            <f:field property="vddr.code" />
            <f:field property="vddr.number" />
            <f:field property="vddr.description" />
            <f:field property="vddr.copies" />
            <f:field property="vddr.copiesForReview" />
            <f:field property="vddr.copiesFinal" />
        </fieldset>

        <fieldset class="buttons">
            <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
        </fieldset>
    </g:form>
</div>
</body>
</html>
