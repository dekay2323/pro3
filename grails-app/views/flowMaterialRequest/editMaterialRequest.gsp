<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Edit Material Request</title>
</head>
<body>
<g:render template="/template/dropdownNav" />
<g:render template="/template/topNavUser" />

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


    <g:form action="updateMaterialRequest" controller="flowMaterialRequest" id="${materialRequest.id}"  method="PUT">
        <g:hiddenField name="version" value="${materialRequest?.version}" />

        <g:render template="template/mrEditGeneral" model="[materialRequest: materialRequest, client: client, readonly: true]" />

        <g:render template="template/mrEditBidders" model="[materialRequest: materialRequest]" />

        <g:render template="template/mrEditLineItems" model="[materialRequest: materialRequest]" />

        <g:render template="template/mrEditTechnicalInstructions" model="[materialRequest: materialRequest]" />
        
        <g:render template="template/mrEditVDDR" model="[materialRequest: materialRequest]" />

        <g:render template="template/mrFilesList" model="[files: files, addFileButton: true]" />

        <fieldset class="buttons">
            <input class="save" type="submit" value="Update" />
        </fieldset>
    </g:form>
</div>
</body>
</html>
