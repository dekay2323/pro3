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
            <g:sortableColumn property="description" title="Description" />
            <g:sortableColumn property="price" title="Price" />
            <g:sortableColumn property="extPrice" title="Extended Price" />
            <g:sortableColumn property="shipDate" title="Ship Date" />
        </tr>
        </thead>
        <tbody>
        <g:each in="${quoteList}" var="quote" status="i">
            <g:each in="${quote?.quoteLineItems}" var="quoteLineItem">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td><f:display bean="${quote?.rfq?.materialRequest?.project}" property="name" /></td>
                    <td><g:link controller="flowQuote" action="editQuote" id="${quote.id}">${quote?.rfq?.name}</g:link></td>
                    <td>${quote.vendor}</td>
                    <td>${quoteLineItem?.lineItem?.description}</td>
                    <td>${quoteLineItem?.price}</td>
                    <td>${quoteLineItem?.extendedPrice}</td>
                    <td>${quoteLineItem?.shipDate}</td>
                </tr>
            </g:each>
            <g:unless test="${quote?.quoteLineItems}">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td><f:display bean="${quote?.rfq?.materialRequest?.project}" property="name" /></td>
                    <td><f:display bean="${quote?.rfq}" property="name" /></td>
                    <td>${quote.vendor}</td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </g:unless>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${quoteCount ?: 0}" />
    </div>
</div>
</body>
</html>