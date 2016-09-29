<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'project.label', default: 'Project')}" />
    <title><g:message code="default.create.label" args="[entityName]" /></title>
</head>
<body>
<g:render template="/template/dropdownNav" />

<a href="#create-project" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<g:render template="/template/topNav" />

<div id="create-project" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]" /></h1>
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
            <f:with bean="project">
                <f:field property="projectNumber" />
                <f:field property="name" />
                <f:field property="shortDescription" />
                <div class="fieldcontain"><label>Client</label>${project?.client}</div>
                <g:hiddenField name="client" value="${project?.client?.id}" />
            </f:with>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
        </fieldset>
    </g:form>
</div>
</body>
</html>
