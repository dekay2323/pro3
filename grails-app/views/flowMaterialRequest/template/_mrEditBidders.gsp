<h2>Bidders <span class="required-indicator">*</span></h2>
<table>
    <thead>
    <tr>
        <g:sortableColumn property="username" title="Bidder"/>
    </tr>
    </thead>
    <tbody>
    <g:each in="${materialRequest?.bidders}" var="bidder" status="i">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td>${bidder?.username}</td>
        </tr>
    </g:each>
    </tbody>
</table>
<div class="nav" role="navigation">
    <ul>
        <li>
            <g:actionSubmit value="Edit Bidders" action="clickEditBidders" class="create"/>
        </li>
    </ul>
</div>

