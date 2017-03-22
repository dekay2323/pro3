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
        <div class="col-sm-6">
            <form class="form-horizontal">
                <fieldset>
                    <legend>Procurement Statistics</legend>

                    <div class="form-group">
                        <label class="col-sm-6 control-label">POs Issued YTD</label>

                        <div class="col-sm-6">
                            <input class="form-control" id="ytd" type="text" value="${poData.ytd}"
                                   disabled="">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-6 control-label">PO Value Issued YTD</label>

                        <div class="col-sm-6">
                            <input class="form-control" id="ytdValue" type="text" value="${poData.ytdValue}"
                                   disabled="">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-6 control-label">POs Issues All Time</label>

                        <div class="col-sm-6">
                            <input class="form-control" id="all" type="text" value="${poData.all}"
                                   disabled="">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-6 control-label">PO Value Issue All Time</label>

                        <div class="col-sm-6">
                            <input class="form-control" id="allValue" type="text" value="${poData.allValue}"
                                   disabled="">
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <h4>Critical Procurement Status Update</h4>
        </div>
    </div>

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
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>