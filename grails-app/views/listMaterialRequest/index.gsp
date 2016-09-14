<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'materialRequest.label', default: 'MaterialRequest')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<a href="#list-materialRequest" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
    </ul>
</div>

<div id="stats" class="content scaffold-list" role="main">
    <h1>Procurement Plan</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <ol class="property-list strategy">

        <li class="fieldcontain">
            <span id="project-label" class="property-label">Project</span>
            <div class="property-value" aria-labelledby="project-label">${project}</div>
        </li>
        <li class="fieldcontain">
            <span id="client-label" class="property-label">Client</span>
            <div class="property-value" aria-labelledby="posYtd-label">${client}</div>
        </li>
    </ol>


    <div id="list" class="content scaffold-list" role="main">
        <h1>Material Requests</h1>
        <table>
            <thead>
            <tr>
                <g:sortableColumn property="reqNumber" title="Request #" />
                <g:sortableColumn property="description" title="Description" />
                <g:sortableColumn property="po" title="PO #" />
                <g:sortableColumn property="budget" title="Budget" />
                <g:sortableColumn property="rasDate" title="RAS Date" />
                <g:sortableColumn property="shipDate" title="Estimated Delivery" />
                <g:sortableColumn property="strategy" title="Strategy" />
                <g:sortableColumn property="approved" title="Status" />
            </tr>
            </thead>
            <tbody>
            <g:each in="${materialRequestList}" var="materialRequest" status="i">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td><g:link controller="flowMaterialRequest" action="editMaterialRequest" id="${materialRequest.id}" >${materialRequest.reqNumber}</g:link></td>
                    <td><g:link controller="flowMaterialRequest" action="editMaterialRequest" id="${materialRequest.id}" >${materialRequest.description}</g:link></td>
                    <td></td>
                    <td><f:display bean="${materialRequest}" property="budget" /></td>
                    <td><f:display bean="${materialRequest}" property="rasDate" /></td>
                    <td><f:display bean="${materialRequest}" property="shipDate" /></td>
                    <td>${materialRequest.strategy}</td>
                    <td>${materialRequest.status}</td>
                </tr>
            </g:each>
            </tbody>
        </table>

        <div class="pagination">
            <g:paginate total="${materialRequestCount ?: 0}" />
        </div>

        <div class="nav" role="navigation">
            <ul>
                <li><g:link class="create" controller="flowMaterialRequest" action="createMaterialRequest" params="[projectId : project?.id]">Add Request</g:link></li>
            </ul>
        </div>

    </div>

</div>
</body>
</html>