<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Create Vendor</title>
</head>

<body>
<g:render template="/template/topNavUser"/>

<div id="create-project" class="content scaffold-create" role="main">
    <h1>Create New Vendor</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <ol class="property-list strategy">
        <li class="fieldcontain">
            <span id="posProject-label" class="property-label">Project</span>
            <div class="property-value" aria-labelledby="posYtd-label">${project?.name}</div>
        </li>
        <li class="fieldcontain">
            <span id="posAccount-label" class="property-label">Account</span>
            <div class="property-value" aria-labelledby="posYtd-label">${account?.name}</div>
        </li>
    </ol>

    <g:form action="createNewUser">
        <g:hiddenField name="projectId" value="${project.id}"/>
        <g:hiddenField name="accountId" value="${account.id}"/>
        <g:hiddenField name="materialRequestId" value="${materialRequestId}"/>
        
        <div class="fieldcontain">
            <label for="userName">User Name</label>
            <g:textField name="username" value="${username}"/>
        </div>

        <div class="fieldcontain">
            <label for="userName">Email</label>
            <g:textField name="email" value="${email}"/>
        </div>
        
        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:form>


</div>
</body>
</html>

