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
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-materialRequest" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <table>
                <thead>
                <tr>
                    <g:sortableColumn property="client" title="Client" />
                    <g:sortableColumn property="project" title="Project" />
                    <g:sortableColumn property="reqNumber" title="PO#" />
                    <g:sortableColumn property="description" title="Description" />
                    <g:sortableColumn property="rasDate" title="RAS Date" />
                    <g:sortableColumn property="shipDate" title="Ship Date" />
                    <g:sortableColumn property="deltaWeeks" title="Delta Weeks" />
                </tr>
                </thead>
                <tbody>
                <g:each in="${materialRequestList}" var="bean" status="i">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <td>${bean?.project?.client}</td>
                        <td>${bean.project}</td>
                        <td>${bean.reqNumber}</td>
                        <td>${bean.description}</td>
                        <td>${bean.rasDate}</td>
                        <td>${bean.shipDate}</td>
                        <td></td>
                    </tr>
                </g:each>
                </tbody>
            </table>

            <div class="pagination">
                <g:paginate total="${materialRequestCount ?: 0}" />
            </div>
        </div>
    </body>
</html>