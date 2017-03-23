<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Procurement Statistics</title>
</head>

<body>
<g:render template="/template/topNavUser"/>

<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <h2>Procurement Statistics</h2>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-3">
            POs Issued YTD
        </div>
        <div class="col-sm-1">
            ${poData.ytd}
        </div>
    </div>
    <div class="row">
        <div class="col-sm-3">
            PO Value Issued YTD
        </div>
        <div class="col-sm-1">
            ${poData.ytdValue}
        </div>
    </div>
    <div class="row">
        <div class="col-sm-3">
            POs Issues All Time
        </div>
        <div class="col-sm-1">
            ${poData.all}
        </div>
    </div>
    <div class="row">
        <div class="col-sm-3">
            PO Value Issue All Time
        </div>
        <div class="col-sm-1">
            ${poData.allValue}
        </div>
    </div>
    
    <hr />
    
    <div class="row">
        <div class="col-sm-12">
            <table class="table table-striped table-hover ">
                <thead>
                <tr>
                    <g:sortableColumn property="client" title="Client"/>
                    <g:sortableColumn property="project" title="Project"/>
                    <g:sortableColumn property="po" title="PO#"/>
                    <g:sortableColumn property="shortDescription" title="Description"/>
                    <g:sortableColumn property="rasDate" title="RAS Date"/>
                    <g:sortableColumn property="shipDate" title="Ship Date"/>
                    <g:sortableColumn property="deltaWeeks" title="Delta Weeks"/>
                </tr>
                </thead>
                <tbody>
                <g:each in="${projectList}" var="project" status="i">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <td>${project?.client}</td>
                        <td><g:link controller="listMaterialRequest" action="index"
                                    id="${project?.id}">${project?.name}</g:link></td>
                        <td></td>
                        <td><f:display bean="${project}" property="shortDescription"/></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </g:each>
                <g:if test="${projectList.isEmpty()}">
                    <tr>
                        <td colspan="7">
                            <div class="alert alert-info">
                                No projects to display
                            </div>
                        </td>
                    </tr>

                </g:if>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>