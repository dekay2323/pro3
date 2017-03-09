<h2>Bidders <span class="required-indicator">*</span></h2>
<ul>
    <g:each in="${materialRequest?.bidders}" var="bidder">
        <li>${bidder?.username}</li>
    </g:each>
</ul>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="create" action="addBidder" id="${materialRequest?.id}">Edit Bidders</g:link></li>
    </ul>
</div>