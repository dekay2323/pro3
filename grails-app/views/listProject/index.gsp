<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Project List</title>
</head>

<body>
<g:render template="/template/topNavUser"/>

<div class="container">
    <div class="row">
        <div class="col-sm-12">
            <h2>Project List</h2>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <table class="table table-striped table-hover ">
                <g:each in="${this.clientList}" var="client">
                    <h3>${client?.name}</h3>
                    <table>
                        <thead>
                        <tr>
                            <g:sortableColumn property="projectNumber" title="Project #"/>
                            <g:sortableColumn property="name" title="Name"/>
                            <g:sortableColumn property="budget" title="Budget"/>
                            <g:sortableColumn property="committed" title="Committed"/>
                            <g:sortableColumn property="accrued" title="Accrued"/>
                            <g:sortableColumn property="incurred" title="Incurred"/>
                            <th>Interested Users</th>
                        </tr>
                        </thead>
                        <tbody>
                        <g:each in="${client?.projects}" var="project">
                            <tr>
                                <td>${project?.id}</td>
                                <td><g:link controller="listMaterialRequest" action="index"
                                            id="${project?.id}">${project?.name}</g:link></td>
                                <td><f:display bean="${project}" property="budget"/></td>
                                <td><f:display bean="${project}" property="committed"/></td>
                                <td><f:display bean="${project}" property="accrued"/></td>
                                <td><f:display bean="${project}" property="incurred"/></td>
                                <td><g:link controller="flowProject" action="editProject"
                                            id="${project?.id}">Edit</g:link></td>
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

                    <div class="row">
                        <div class="col-sm-12">
                            <g:link class="btn btn-success" controller="flowProject" action="createProject"
                                    params="[clientId: client?.id]">Create Project</g:link>
                        </div>
                    </div>
                </g:each>
            </table>
            <g:if test="${!clientList}">
                <tr>
                    <td colspan="7">
                        <div class="alert alert-info">
                            No clients to display
                        </div>
                    </td>
                </tr>
            </g:if>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <g:link class="btn btn-success" controller="flowClient" action="createClient">Create Client</g:link>
        </div>
    </div>
</div>
</body>
</html>