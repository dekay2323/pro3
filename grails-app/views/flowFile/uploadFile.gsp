<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
</head>
<body>

<div id="files" class="container" role="main">
    <h1>Files</h1>
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
    
    <g:render template="template/mrFilesList" model="[materialRequestId: materialRequestId, files: files]" />

    <g:form action="uploadFile" enctype="multipart/form-data" useToken="true">
        <fieldset class="form">
            <g:hiddenField name="materialRequestId" value="${materialRequestId}" />
            <input type="file" name="file" />
            
            <br />
            <g:submitButton name="save" class="save" value="Upload File" />
        </fieldset>
    </g:form>
    
    <fieldset class="buttons">
        <g:link controller="flowMaterialRequest" action="editMaterialRequest" id="${materialRequestId}">Back</g:link>
    </fieldset>
</div>
</body>
</html>
