<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'materialRequest.label', default: 'MaterialRequest')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<g:render template="/template/dropdownNav" />

<a href="#list-materialRequest" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}">Home</a></li>
        <li><g:link controller="listProject" action="index">Project List</g:link></li>
    </ul>
</div>
<div id="list" class="content scaffold-list" role="main">
    <h1>Project List</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <g:each in="${clientList}" var="client">
        <h2>${client?.name}</h2>
        <table>
            <thead>
            <tr>
                <g:sortableColumn property="projectNumber" title="Project #" />
                <g:sortableColumn property="name" title="Name" />
                <g:sortableColumn property="budget" title="Budget" />
                <g:sortableColumn property="committed" title="Committed" />
                <g:sortableColumn property="accrued" title="Accrued" />
                <g:sortableColumn property="incurred" title="Incurred" />
            </tr>
            </thead>
            <tbody>
            <g:each in="${client?.projects}" var="project" status="i">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td><g:link controller="listMaterialRequest" action="index" id="${project?.id}">${project?.projectNumber}</g:link></td>
                    <td><g:link controller="listMaterialRequest" action="index" id="${project?.id}">${project?.name}</g:link></td>
                    <td><f:display bean="${project}" property="budget" /></td>
                    <td><f:display bean="${project}" property="committed" /></td>
                    <td><f:display bean="${project}" property="accrued" /></td>
                    <td><f:display bean="${project}" property="incurred" /></td>
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



    <div class="pagination">
        <g:paginate total="${clientCount ?: 0}" />
    </div>
</div>
</body>
</html>