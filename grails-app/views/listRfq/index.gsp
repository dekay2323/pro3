<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
</head>
<body>

<div id="list" class="container" role="main">
    <h1>Request for Quote List</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <table>
        <thead>
        <tr>
            <th>Project</th>
            <g:sortableColumn property="mrNumber" title="MR Number" />
            <g:sortableColumn property="mrDescription" title="MR Description" />
            <g:sortableColumn property="rfq" title="RFQ" />
            <g:sortableColumn property="bidsReceived" title="Bids Received" />
            <g:sortableColumn property="approved" title="Status" />
            <g:sortableColumn property="clarifications" title="Clarifications Pending" />
            <g:sortableColumn property="bidClosing" title="Bid Closing" />
            <g:sortableColumn property="commercialEvaluation" title="Commercial Evaluation" />
            <g:sortableColumn property="technicalEvaluation" title="Technical Evaluation" />
        </tr>
        </thead>
        <tbody>
        <g:each in="${rfqList}" var="rfq" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${rfq?.materialRequest?.project?.name}</td>
                <td>${rfq?.materialRequest?.code}</td>
                <td>${rfq?.materialRequest?.name}</td>
                <td><g:link controller="flowBid" action="index" id="${rfq?.id}">${rfq?.name}</g:link></td>
                <td>${rfq?.bidsReceived}/${rfq?.bidsOut}</td>
                <td>${rfq?.materialRequest?.status}</td>
                <td>${rfq?.clarifications?.size()}</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
        </g:each>
        </tbody>
    </table>
    <g:if test="${!rfqList}">
        <div class="alert alert-warning" role="alert">
            No Project, <g:link controller="listProject" action="index">Create Project</g:link>
        </div>
    </g:if>
</div>
</body>
</html>