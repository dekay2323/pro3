<h2>Bidders</h2>
<ul>
    <g:each in="${materialRequest?.bidders}" var="bidder">
        <li>${bidder?.username}</li>
    </g:each>
</ul>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="create" action="addBidder" id="${materialRequest?.id}">Add Bidder</g:link></li>
    </ul>
</div>