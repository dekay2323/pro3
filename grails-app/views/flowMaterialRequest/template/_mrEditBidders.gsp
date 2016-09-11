<h2>Bidders</h2>
<fieldset class="form">
    <div class="fieldcontain">
        <label>Recommended Bidders</label>
        <g:select name="bidders" from="${com.pro3.Vendor.list()}" value="${materialRequest?.bidders*.id}" optionKey="id" multiple="true" />
    </div>
</fieldset>