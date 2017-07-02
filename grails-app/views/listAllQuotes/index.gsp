<!DOCTYPE html>
<html>
<head>
    <sec:ifAnyGranted roles='ROLE_VENDOR'>
        <meta name="layout" content="vendor" />
    </sec:ifAnyGranted>
    <sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_USER'>
        <meta name="layout" content="main" />
    </sec:ifAnyGranted>
</head>
<body>

<div id="list" class="content scaffold-list" role="main">
    <h1>Quote List</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <table>
        <thead>
        <tr>
            <g:sortableColumn property="project" title="Project" />
            <g:sortableColumn property="name" title="Name" />
            <g:sortableColumn property="vendor" title="Vendor" />
            <g:sortableColumn property="approved" title="Status" />
            <th></th>
        </tr>
        </thead>
        <tbody>
        <g:each in="${quoteList}" var="quote" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${quote?.rfq?.materialRequest?.project?.name}</td>
                <td><g:link controller="flowQuote" action="editQuote" id="${quote.id}">${quote?.rfq?.name}</g:link></td>
                <td>${quote.vendor}</td>
                <td>${quote.status}</td>
                <td>
                    <g:if test="${quote.canCreateBid()}">
                        <g:link controller="flowQuote" action="createBid" id="${quote?.id}">Bid Complete</g:link>
                    </g:if>
                </td>
            </tr>
        </g:each>
        </tbody>
    </table>

</div>
</body>
</html>