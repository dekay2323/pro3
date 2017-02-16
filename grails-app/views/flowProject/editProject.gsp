<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Create Project</title>
</head>

<body>
<g:render template="/template/dropdownNav"/>
<g:render template="/template/topNavUser"/>

<div id="create-project" class="content scaffold-create" role="main">
    <h1>Edit Project</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <ol class="property-list strategy">

        <li class="fieldcontain">
            <span id="posNumber-label" class="property-label">Project Number</span>

            <div class="property-value" aria-labelledby="posYtd-label">${project?.projectNumber}</div>
        </li>
        <li class="fieldcontain">
            <span id="posName-label" class="property-label">Name</span>

            <div class="property-value" aria-labelledby="posYtd-label">${project?.name}</div>
        </li>
        <li class="fieldcontain">
            <span id="posDescr-label" class="property-label">Short description</span>

            <div class="property-value" aria-labelledby="posYtd-label">${project?.shortDescription}</div>
        </li>
        <li class="fieldcontain">
            <span id="posClient-label" class="property-label">Client</span>

            <div class="property-value" aria-labelledby="posYtd-label">${project?.client}</div>
        </li>
    </ol>

    <g:form action="saveEditManagers">
        <h2>Managers</h2>
        <fieldset class="form">
            <g:select name="managers" from="${userList}" value="${project?.managers*.id}" optionKey="id"
                      multiple="true"/>
        </fieldset>

        <g:hiddenField name="projectId" value="${project?.id}"/>
        <g:hiddenField name="client" value="${project?.client?.id}"/>

        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:form>

</div>
</body>
</html>
