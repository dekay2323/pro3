<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Create Client</title>
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
            <g:hasErrors>
                <div class="alert alert-danger">
                    <ul>
                        <g:eachError bean="${client}" var="error">
                            <li><g:message error="${error}"/></li>
                        </g:eachError>
                    </ul>
                </div>
            </g:hasErrors>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-6">
            <div class="well bs-component">
                <g:form action="saveClient" class="form-horizontal">
                    <fieldset>
                        <div class="row">
                            <legend class="col-sm-12">Create Client</legend>
                        </div>

                        <div class="row form-group ${hasErrors(bean:client,field:'name','has-error')}">
                            <label for="name" class="col-sm-3 control-label">Name</label>

                            <div class="col-sm-9">
                                <g:textField class="form-control" name="name" value="${client?.name}"/>
                            </div>
                        </div>

                        <div class="row form-group ${hasErrors(bean:client,field:'contactName','has-error')}">
                            <label for="contactName" class="col-sm-3 control-label">Contact Name</label>

                            <div class="col-sm-9">
                                <g:textField class="form-control" name="contactName" value="${client?.contactName}"/>
                            </div>
                        </div>

                        <div class="row form-group ${hasErrors(bean:client,field:'address','has-error')}">
                            <label for="address" class="col-sm-3 control-label">Address</label>

                            <div class="col-sm-9">
                                <g:textField class="form-control" name="address" value="${client?.address}"/>
                            </div>
                        </div>

                        <div class="row form-group ${hasErrors(bean:client,field:'phoneNumber','has-error')}">
                            <label for="phoneNumber" class="col-sm-3 control-label">Phone Number</label>

                            <div class="col-sm-9">
                                <g:textField class="form-control" name="phoneNumber" value="${client?.phoneNumber}"/>
                            </div>
                        </div>

                        <div class="row form-group">
                            <div class="col-sm-3">
                            </div>

                            <div class="col-sm-9">
                                <g:submitButton class="btn btn-primary" name="create" value="Submit"/>
                            </div>
                        </div>
                    </fieldset>
                </g:form>
            </div>
        </div>
    </div>

</div>