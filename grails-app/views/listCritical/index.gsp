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
            <h1>Procurement Statistics</h1>
            <ol class="property-list strategy">

                <li class="fieldcontain">
                    <span id="posYtd-label" class="property-label">POs Issued YTD</span>
                    <div class="property-value" aria-labelledby="posYtd-label">${poData.ytd}</div>
                </li>

                <li class="fieldcontain">
                    <span id="posYtdValue-label" class="property-label">PO Value Issued YTD</span>
                    <div class="property-value" aria-labelledby="posYtdValue-label">${poData.ytdValue}</div>
                </li>

                <li class="fieldcontain">
                    <span id="pos-label" class="property-label">POs Issues All Time</span>
                    <div class="property-value" aria-labelledby="pos-label">${poData.all}</div>
                </li>

                <li class="fieldcontain">
                    <span id="posValue-label" class="property-label">PO Value Issue All Time</span>
                    <div class="property-value" aria-labelledby="posValue-label">${poData.allValue}</div>
                </li>

            </ol>
        </div>

        <div id="list" class="content scaffold-list" role="main">
            <h1>Critical Procurement Status Update</h1>
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
                        <td><f:display bean="${bean.project}" property="client" /></td>
                        <td><f:display bean="${bean}" property="project" /></td>
                        <td><f:display bean="${bean}" property="reqNumber" /></td>
                        <td><f:display bean="${bean}" property="description" /></td>
                        <td><f:display bean="${bean}" property="rasDate" /></td>
                        <td><f:display bean="${bean}" property="shipDate" /></td>
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