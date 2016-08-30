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
        <div id="list" class="content scaffold-list" role="main">
            <h1>Project List</h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

            <g:each in="${clientList}">
                <h2>${it?.name}</h2>
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
                    <g:each in="${it?.projects}" var="project" status="i">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                            <td><f:display bean="${project}" property="projectNumber" /></td>
                            <td><f:display bean="${project}" property="name" /></td>
                            <td><f:display bean="${project}" property="budget" /></td>
                            <td><f:display bean="${project}" property="committed" /></td>
                            <td><f:display bean="${project}" property="accrued" /></td>
                            <td><f:display bean="${project}" property="incurred" /></td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </g:each>



            <div class="pagination">
                <g:paginate total="${clientCount ?: 0}" />
            </div>
        </div>
    </body>
</html>