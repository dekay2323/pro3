<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Create Line Item</title>
</head>
<body>
<g:render template="/template/dropdownNav" />
<g:render template="/template/topNavUser" />

<div id="create-lineItem" class="content scaffold-create" role="main">
    <h1>Add Bidder</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:form action="saveAddBidder">
        <fieldset class="form">
            <g:hiddenField name="materialRequestId" value="${materialRequest?.id}" />
            <g:select name="bidders" from="${userList}" value="${materialRequest?.bidders*.id}" optionKey="id" multiple="true" />
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Add')}" />
        </fieldset>
    </g:form>
</div>
</body>
</html>
