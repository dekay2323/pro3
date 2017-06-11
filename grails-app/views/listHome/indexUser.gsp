<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Pro3</title>
</head>
<body>

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

    <h1>Critical Procurement Status Update</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <table>
        <thead>
        <tr>
            <g:sortableColumn property="client" title="Client" />
            <g:sortableColumn property="project" title="Project" />
            <g:sortableColumn property="po" title="PO#" />
            <g:sortableColumn property="description" title="Description" />
            <g:sortableColumn property="rasDate" title="RAS Date" />
            <g:sortableColumn property="deltaWeeks" title="Delta Weeks" />
        </tr>
        </thead>
        <tbody>
        <g:each in="${projectList}" var="project" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${project?.client}</td>
                <td><g:link controller="listMaterialRequest" action="index" id="${project?.id}">${project?.name}</g:link></td>
                <td></td>
                <td><f:display bean="${project}" property="description" /></td>
                <td></td>
                <td></td>
            </tr>
        </g:each>
        </tbody>
    </table>

</div>
</body>
</html>