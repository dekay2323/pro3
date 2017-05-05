<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Upload File</title>
</head>
<body>
<g:render template="/template/dropdownNav" />
<g:render template="/template/topNavUser" />

<div id="create-lineItem" class="content scaffold-create" role="main">
    <h1>Upload File</h1>
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
    
    <g:form action="uploadFile" enctype="multipart/form-data" useToken="true">
        <fieldset class="form">
            <g:hiddenField name="materialRequestId" value="${materialRequestId}" />
            <div class="fieldcontain">
                <input type="file" name="file" />
            <g:submitButton name="save" class="save" value="Upload File" />
            </div>
        </fieldset>
    </g:form>

    <g:render template="template/mrFilesList" model="[files: files]" />
</div>
</body>
</html>
