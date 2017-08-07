<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
</head>
<body>

<div id="stats" class="container" role="main">
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

    <table class="table table-bordered">
        <thead>
        <tr>
            <g:sortableColumn property="client" title="Client" />
            <g:sortableColumn property="project" title="Project" />
            <g:sortableColumn property="po" title="PO#" />
            <g:sortableColumn property="description" title="Description" />
            <g:sortableColumn property="rasDate" title="RAS Date" />
            <g:sortableColumn property="deltaWeeks" title="Delta" />
        </tr>
        </thead>
        <tbody>
        <g:each in="${poList}" var="po" status="i">
            <tr>
                <td>${po?.client}</td>
                <td>${po.rfq?.materialRequest?.project}</td>
                <td>${po?.id}</td>
                <td></td>
                <td>${po?.rfq?.materialRequest?.rasDate}</td>
                <td>${po?.rfq?.materialRequest?.daysLeftTillRas()}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
    <g:if test="${!projectList}">
        <div class="alert alert-warning" role="alert">
            No Project, <g:link controller="listProject" action="index">Create Project</g:link>
        </div>
    </g:if>
</div>
</body>
</html>