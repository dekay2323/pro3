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
            <h1>Procurement Plan</h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>

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
                    <g:sortableColumn property="approved" title="Approved in Plan" />
                </tr>
                </thead>
                <tbody>
                <g:each in="${materialRequestList}" var="bean" status="i">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <td><f:display bean="${bean}" property="reqNumber" /></td>
                        <td><f:display bean="${bean}" property="description" /></td>
                        <td></td>
                        <td><f:display bean="${bean}" property="budget" /></td>
                        <td><f:display bean="${bean}" property="rasDate" /></td>
                        <td><f:display bean="${bean}" property="shipDate" /></td>
                        <td><f:display bean="${bean}" property="strategy" /></td>
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