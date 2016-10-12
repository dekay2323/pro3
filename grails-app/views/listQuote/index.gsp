<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Quote List</title>
</head>
<body>
<g:render template="/template/dropdownNav" />
<g:render template="/template/topNav" />

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
        </tr>
        </thead>
        <tbody>
        <g:each in="${quoteList}" var="quote" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${quote?.rfq?.materialRequest?.project?.name}</td>
                <td><g:link controller="flowQuote" action="editQuote" id="${quote.id}">${quote?.rfq?.name}</g:link></td>
                <td>${quote.vendor}</td>
            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${quoteCount ?: 0}" />
    </div>
</div>
</body>
</html>