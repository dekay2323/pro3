<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'materialRequest.label', default: 'MaterialRequest')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-materialRequest" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}">Home</a></li>
            </ul>
        </div>
        <div id="edit-materialRequest" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
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


            <g:form action="updateMaterialRequest" controller="flowMaterialRequest" id="${materialRequest.id}"  method="PUT">
                <g:hiddenField name="version" value="${materialRequest?.version}" />

                <g:render template="template/mrEditGeneral" model="[materialRequest: materialRequest, client: client]" />

                <g:render template="template/mrEditBidders" model="[materialRequest: materialRequest]" />

                <g:render template="template/mrEditLineItems" model="[materialRequest: materialRequest]" />

                <h2>Technical Instructions</h2>
                <f:field bean="materialRequest" property="technicalInstructions" />

                <g:render template="template/mrEditVDDR" model="[materialRequest: materialRequest]" />

                <fieldset class="buttons">
                    <input class="save" type="submit" value="${message(code: 'default.button.update.label', default: 'Update')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
