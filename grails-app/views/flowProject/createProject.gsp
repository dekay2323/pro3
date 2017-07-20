<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
</head>
<body>

<div id="create-project" class="container" role="main">
    <h1>Create Project</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.project}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.project}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form action="saveProject">
        <fieldset class="form">
            <ol class="property-list strategy">
                <li class="fieldcontain">
                    <pro3:labelfield type="text" label="Code" name="code" value="${project?.code}" required="true" />
                </li>
                <li class="fieldcontain">
                    <pro3:labelfield type="text" label="Name" name="name" value="${project?.name}" required="true" />
                </li>
                <li class="fieldcontain">
                    <label>Description</label>
                    <g:textArea name="description" value="${project?.description}"/>
                </li>
                <li class="fieldcontain">
                    <pro3:labelfield type="textArea" label="Client" name="client" value="${project?.client}" readonly="true" />
                </li>
            </ol>
            <g:hiddenField name="client" value="${project?.client?.id}"/>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
        </fieldset>
    </g:form>
</div>
</body>
</html>
