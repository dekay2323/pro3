<%@ page import="com.pro3.Vendor" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Material Request</title>
</head>
<body>
<g:render template="/template/dropdownNav" />
<g:render template="/template/topNavUser" />

<div id="create-materialRequest" class="content scaffold-create" role="main">
    <h1>Material Request</h1>
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
    <g:form controller="flowMaterialRequest" action="saveMaterialRequest">
        <g:render template="template/mrEditGeneral" model="[materialRequest: materialRequest, client: client]" />

        <fieldset class="buttons">
            <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
        </fieldset>
    </g:form>
</div>
</body>
</html>
