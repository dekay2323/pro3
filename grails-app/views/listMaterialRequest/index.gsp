<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Procurement Plan</title>
</head>

<body>
<g:render template="/template/topNavUser"/>

<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <g:if test="${flash.message}">
                <div class="alert alert-info">
                    ${flash.message}
                </div>
            </g:if>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <h2>Procurement Plan</h2>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-6">
            <div class="well bs-component form-horizontal">
                <fieldset>
                    <div class="row form-group">
                        <label class="col-sm-3 control-label">Project</label>

                        <div class="col-sm-9 form-control-static">
                            ${project}
                        </div>
                    </div>

                    <div class="row form-group">
                        <label class="col-sm-3 control-label">Client</label>

                        <div class="col-sm-9 form-control-static">
                            ${client}
                        </div>
                    </div>
                </fieldset>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-3">
            <h3>Material Requests</h3>
        </div>
    </div>

    <div class="row">
        <div class=" col-sm-12">
            <table class="table table-striped table-hover">
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
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <g:link class="btn btn-primary" controller="flowMaterialRequest"
                    action="createMaterialRequest">Create Request</g:link>
        </div>
    </div>

</div>
</body>
</html>
