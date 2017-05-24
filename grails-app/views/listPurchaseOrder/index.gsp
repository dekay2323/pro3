<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Procurement Statistics</title>
</head>
<body>
<g:render template="/template/dropdownNav" />
<g:render template="/template/topNavUser" />

<div id="stats" class="content scaffold-list" role="main">
    <h1>Purchase Orders</h1>

    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <table>
        <thead>
        <tr>
            <th property="client" title="Client" />
        </tr>
        </thead>
        <tbody>
        <g:each in="${this.purchaseOrderList}" var="purchaseOrder" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${purchaseOrder}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
</body>
</html>