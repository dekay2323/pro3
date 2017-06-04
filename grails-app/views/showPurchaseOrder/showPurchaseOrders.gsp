<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Edit Material Request</title>
</head>
<body>

<div id="edit-materialRequest" class="content scaffold-edit" role="main">
    <h1>Edit Material Request</h1>
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

    <g:hiddenField name="version" value="${materialRequest?.version}" />

    <g:render template="template/mrShowLineItems" model="[materialRequest: materialRequest]" />
</div>
</body>
</html>
