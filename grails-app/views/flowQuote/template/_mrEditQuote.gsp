<h2>Quote</h2>
<fieldset class="form">
    <div class="fieldcontain">
        <label for="notBiddingReason">Bidding?</label>
        <g:checkBox name="bidding" value="${true}" />
    </div>
    <div class="fieldcontain">
        <label for="notBiddingReason">Not bidding reason</label>
        <g:textArea name="notBiddingReason" value="${quote?.notBiddingReason}"/>
    </div>
    <div class="fieldcontain">
        <label for="code">Code</label>
        <g:textField name="code" value="${quote?.code}"/>
    </div>
    <div class="fieldcontain">
        <label for="contactName">Contact Name</label>
        <g:textField name="contactName" value="${quote?.contactName}"/>
    </div>
    <div class="fieldcontain">
        <label for="contactPhoneNumber">Contact Phone Number</label>
        <g:textField name="contactPhoneNumber" value="${quote?.contactPhoneNumber}"/>
    </div>
    <div class="fieldcontain">
        <label for="contactEmail">Contact Email</label>
        <g:textField name="contactEmail" value="${quote?.contactEmail}"/>
    </div>
</fieldset>
