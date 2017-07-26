<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
</head>
<body>

<div id="create-lineItem" class="container" role="main">
    <h1>Add Bidder</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:form action="saveAddBidder">
        <fieldset class="form">
            <g:hiddenField name="materialRequestId" value="${materialRequest?.id}" />
            <g:select name="bidders" from="${userList}" value="${materialRequest?.bidders*.id}" optionKey="id" multiple="true" />
        </fieldset>

        <div class="nav" role="navigation">
            <ul>
                <li><g:link class="create" controller="register" action="createNewVendor" 
                            params="[projectId: materialRequest.project.id, materialRequestId: materialRequest.id]">Create New User</g:link></li>
            </ul>
        </div>

        <br />
        <fieldset class="buttons">
            <g:submitButton name="create" class="save" value="Update" />
        </fieldset>
    </g:form>
</div>
</body>
</html>
