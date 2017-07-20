<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
</head>
<body>

<div id="list" class="container" role="main">
    <h1>Project List</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:each in="${this.clientList}" var="client">
        <h2>${client?.name}</h2>
        <table>
            <thead>
            <tr>
                <g:sortableColumn property="code" title="Project #" />
                <g:sortableColumn property="name" title="Name" />
                <g:sortableColumn property="budget" title="Budget" />
                <g:sortableColumn property="committed" title="Committed" />
                <g:sortableColumn property="accrued" title="Accrued" />
                <g:sortableColumn property="incurred" title="Incurred" />
                <th>Managers</th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${client?.projects}" var="project" status="i">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td>${project?.id}</td>
                    <td><g:link controller="listMaterialRequest" action="index" id="${project?.id}">${project?.name}</g:link></td>
                    <td><f:display bean="${project}" property="budget" /></td>
                    <td><f:display bean="${project}" property="committed" /></td>
                    <td><f:display bean="${project}" property="accrued" /></td>
                    <td><f:display bean="${project}" property="incurred" /></td>
                    <td><g:link controller="flowProject" action="editProject" id="${project?.id}">Edit</g:link></td>
                </tr>
            </g:each>
            </tbody>
        </table>
        <div class="nav" role="navigation">
            <ul>
                <li><g:link class="create" controller="flowProject" action="createProject" params="[clientId: client?.id]">Create Project</g:link></li>
            </ul>
        </div>
    </g:each>
    <g:if test="${!this.clientList}">
        <div class="alert alert-warning" role="alert">
            No Client, <g:link controller="flowClient" action="createClient">Create Client</g:link>
        </div>
    </g:if>
</div>
</body>
</html>