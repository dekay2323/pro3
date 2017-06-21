<h2>Quote</h2>
<fieldset class="form">
    <div class="fieldcontain">
        <label for="notBiddingReason">Bidding?</label>
        <pro3:checkBox name="bidding" value="${quote?.bidding}" readonly="${readonly}"/>
    </div>
    <div class="fieldcontain">
        <label for="notBiddingReason">Not bidding reason</label>
        <pro3:field type="text" name="notBiddingReason" value="${quote?.notBiddingReason}" readonly="${readonly}"/>
    </div>
    <div class="fieldcontain">
        <label for="code">Code</label>
        <pro3:field type="text" name="code" value="${quote?.code}" readonly="${readonly}" />
    </div>
    <div class="fieldcontain">
        <label for="contactName">Contact Name</label>
        <pro3:field type="text" name="contactName" value="${quote?.contactName}" readonly="${readonly}" />
    </div>
    <div class="fieldcontain">
        <label for="contactPhoneNumber">Contact Phone Number</label>
        <pro3:field type="text" name="contactPhoneNumber" value="${quote?.contactPhoneNumber}" readonly="${readonly}" />
    </div>
    <div class="fieldcontain">
        <label for="contactEmail">Contact Email</label>
        <pro3:field type="text" name="contactEmail" value="${quote?.contactEmail}" readonly="${readonly}" />
    </div>
</fieldset>
