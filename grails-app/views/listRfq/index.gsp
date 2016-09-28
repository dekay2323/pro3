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
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}">Home</a></li>
        <li><g:link controller="listProject" action="index">Project List</g:link></li>
    </ul>
</div>
<div id="list" class="content scaffold-list" role="main">
    <h1>Rfq List</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>

    <table>
        <thead>
        <tr>
            <g:sortableColumn property="mrNumber" title="MR Number" />
            <g:sortableColumn property="mrDescription" title="MR Description" />
            <g:sortableColumn property="rfq" title="RFQ" />
            <g:sortableColumn property="bidsReceived" title="Bids Received" />
            <g:sortableColumn property="clarifications" title="Clarifications Pending" />
            <g:sortableColumn property="bidClosing" title="Bid Closing" />
            <g:sortableColumn property="commercialEvaluation" title="Commercial Evaluation" />
            <g:sortableColumn property="technicalEvaluation" title="Technical Evaluation" />
        </tr>
        </thead>
        <tbody>
        <g:each in="${rfqList}" var="rfq" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td><f:display bean="${rfq}" property="materialRequest.reqNumber" /></td>
                <td><f:display bean="${rfq}" property="materialRequest.description" /></td>
                <td><f:display bean="${rfq}" property="name" /></td>
                <td>${rfq?.bidsReceived}/${rfq?.quotes?.size()}</td>
                <td>${rfq?.clarifications.size()}</td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
        </g:each>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${clientCount ?: 0}" />
    </div>
</div>
</body>
</html>