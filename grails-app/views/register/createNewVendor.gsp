<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>

<body>

<div id="create-project" class="container" role="main">
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

    <g:form controller="register" action="createNewVendor">
        <g:hiddenField name="projectId" value="${project.id}"/>
        <g:hiddenField name="accountId" value="${account.id}"/>
        <g:hiddenField name="materialRequestId" value="${materialRequestId}"/>

        <div class="fieldcontain">
            <label for="companyName">Company Name</label>
            <g:textField name="companyName" value="${companyName}"/>
        </div>

        <div class="fieldcontain">
            <label for="userName">Username</label>
            <g:textField name="username" value="${username}"/>
        </div>

        <div class="fieldcontain">
            <label for="email">Email</label>
            <g:textField name="email" value="${email}"/>
        </div>

        <div class="fieldcontain">
            <label for="address">Address</label>
            <g:textArea name="address" value="${address}"/>
        </div>

        <div class="fieldcontain">
            <label for="phoneNumber">Phone Number</label>
            <g:textField name="phoneNumber" value="${phoneNumber}"/>
        </div>

        <br />
        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:form>


</div>
</body>
</html>

