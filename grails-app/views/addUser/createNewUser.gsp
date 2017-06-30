<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>

<body>

<div id="create-project" class="content scaffold-create" role="main">
    <h1>Existing Users</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <table>
        <thead>
        <tr>
            <g:sortableColumn property="username" title="Username"/>
            <g:sortableColumn property="email" title="Email"/>
            <g:sortableColumn property="role" title="Role"/>
        </tr>
        </thead>
        <tbody>
        <g:each in="${userList}" var="user" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${user?.username}</td>
                <td>${user?.email}</td>
                <td>${user.getAuthorityNames()}</td>
            </tr>
        </g:each>
        </tbody>
    </table>


    <h1>Create New User</h1>
    <ol class="property-list strategy">
        <li class="fieldcontain">
            <span id="posAccount-label" class="property-label">Account</span>
            <div class="property-value" aria-labelledby="posYtd-label">${account?.name}</div>
        </li>
    </ol>
    <g:form action="createNewUser">
        <g:hiddenField name="accountId" value="${account?.id}"/>
        
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

