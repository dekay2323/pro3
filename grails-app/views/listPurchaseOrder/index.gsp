<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Pro3</title>
</head>
<body>

<div id="stats" class="content scaffold-list" role="main">
    <h1>Purchase Order</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <table>
        <thead>
        <tr>
            <g:sortableColumn property="rfq.name" title="Rfq" />
            <g:sortableColumn property="bidNumber" title="Bid Number" />
            <g:sortableColumn property="quote.vendor.username" title="Vendor" />
        </tr>
        </thead>
        <tbody>
        <g:each in="${purchaseOrderList}" var="po" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td><g:link controller="showPurchaseOrder" action="showPurchaseOrders" id="${po?.id}">${po?.rfq?.name}</g:link></td>
                <td>${po?.quote?.bidNumber}</td>
                <td>${po?.quote?.vendor?.username}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
</body>
</html>