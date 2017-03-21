<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Procurement Plan</title>
</head>

<body>
<g:render template="/template/dropdownNav"/>
<g:render template="/template/topNavUser"/>

<div id="stats" class="content scaffold-list" role="main">
    <h1>Procurement Plan</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:if test="${flash.error}">
        <div class="errors" role="status">${flash.error}</div>
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
                <g:sortableColumn property="reqNumber" title="Request #"/>
                <g:sortableColumn property="description" title="Description"/>
                <g:sortableColumn property="po" title="PO #"/>
                <g:sortableColumn property="budget" title="Budget"/>
                <g:sortableColumn property="rasDate" title="RAS Date"/>
                <g:sortableColumn property="shipDate" title="Estimated Delivery"/>
                <g:sortableColumn property="closingDate" title="Closing Date"/>
                <g:sortableColumn property="strategy" title="Strategy"/>
                <g:sortableColumn property="approved" title="Status"/>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <g:each in="${materialRequestList}" var="materialRequest" status="i">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td>${materialRequest.reqNumber}</td>
                    <g:if test="${materialRequest.readOnlyRFQ()}">
                        <td>
                            <g:link controller="flowMaterialRequest" action="editMaterialRequest"
                                    id="${materialRequest.id}">
                                ${materialRequest.description}
                            </g:link>
                        </td>
                    </g:if>
                    <g:else>
                        <td>
                            <g:link controller="showMaterialRequest" action="showMaterialRequest"
                                    id="${materialRequest.id}">
                                ${materialRequest.description}
                            </g:link>
                        </td>
                    </g:else>
                    <td></td>
                    <td><f:display bean="${materialRequest}" property="budget"/></td>
                    <td><f:display bean="${materialRequest}" property="rasDate"/></td>
                    <td><f:display bean="${materialRequest}" property="shipDate"/></td>
                    <td><f:display bean="${materialRequest}" property="closingDate"/></td>
                    <td>${materialRequest.strategy}</td>
                    <td>${materialRequest.status}</td>
                    <td>
                        <g:if test="${materialRequest.canCreateRFQ()}">
                            <g:link controller="flowRfq" action="createRfq"
                                    id="${materialRequest?.id}">Create Rfq</g:link>
                        </g:if>
                    </td>
                </tr>
            </g:each>
            </tbody>
        </table>

        <div>
            <g:link class="btn btn-success" controller="flowMaterialRequest" action="createMaterialRequest"
                    params="[projectId: project?.id]">
                <i class="fa fa-plus-square fa-lg" aria-hidden="true"></i> Create Request</g:link>
        </div>
    </div>

</div>
</body>
</html>
