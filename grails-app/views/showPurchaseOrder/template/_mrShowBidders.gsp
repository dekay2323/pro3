<h2>Bidders</h2>
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
</fieldset>