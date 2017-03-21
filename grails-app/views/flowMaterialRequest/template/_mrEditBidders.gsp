<h2>Bidders <span class="required-indicator">*</span></h2>
<fieldset class="form">
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
            <li><g:link class="create" action="addBidder" id="${materialRequest?.id}">Edit Bidders</g:link></li>
        </ul>
    </div>
</fieldset>

