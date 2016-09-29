<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'materialRequest.label', default: 'MaterialRequest')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<g:render template="/template/dropdownNav" />

<a href="#list-materialRequest" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<g:render template="/template/topNav" />

<div id="list" class="content scaffold-list" role="main">
    <h1>Quote List</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <table>
        <thead>
        <tr>
            <g:sortableColumn property="name" title="Name" />
            <g:sortableColumn property="vendor" title="Vendor" />
            <g:sortableColumn property="description" title="Description" />
        </tr>
        </thead>
        <tbody>
        <g:each in="${quoteList}" var="quote" status="i">
            <g:each in="${quote?.quoteLineItems}" var="quoteLineItem">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td><f:display bean="${quote}" property="rfq.name" /></td>
                    <td>${quote.vendor}</td>
                    <td>${quoteLineItem?.lineItem?.description}</td>
                </tr>
            </g:each>
            <g:unless test="${quote?.quoteLineItems}">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td><f:display bean="${quote}" property="rfq.name" /></td>
                    <td>${quote.vendor}</td>
                    <td>No Line Items</td>
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