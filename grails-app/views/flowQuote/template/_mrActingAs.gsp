<h2>Acting AS</h2>
<fieldset class="form">
    <f:with bean="materialRequest">
        <div class="fieldcontain"><label>Acting As</label>${quote?.vendor?.username}</div>
        <div class="fieldcontain"><label>Last Changed By</label>${quote?.changedBy}</div>
    </f:with>
</fieldset>
