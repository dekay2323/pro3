<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>

<body>

<div id="create-project" class="content scaffold-create" role="main">
    <h1>Add Managers</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <ol class="property-list strategy">

        <li class="fieldcontain">
            <span id="posNumber-label" class="property-label">Project Number</span>
            <div class="property-value" aria-labelledby="posYtd-label">${project?.code}</div>
        </li>
        <li class="fieldcontain">
            <span id="posName-label" class="property-label">Name</span>
            <div class="property-value" aria-labelledby="posYtd-label">${project?.name}</div>
        </li>
        <li class="fieldcontain">
            <span id="posDescr-label" class="property-label">Description</span>
            <div class="property-value" aria-labelledby="posYtd-label">${project?.description}</div>
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

        <div class="nav" role="navigation">
            <ul>
                <li><g:link class="create" action="createNewUser" params="[projectId: project?.id]">Create New User</g:link></li>
            </ul>
        </div>

        <fieldset class="buttons">
            <g:submitButton name="create" class="save" value="Update"/>
        </fieldset>
    </g:form>


</div>
</body>
</html>
