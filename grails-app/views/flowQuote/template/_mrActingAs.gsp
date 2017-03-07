<h2>Acting AS</h2>
<fieldset class="form">
    <f:with bean="materialRequest">
        <div class="fieldcontain"><label>Acting as</label>${quote?.vendor?.username}</div>
        <div class="fieldcontain"><label>Made Changes</label>${quote?.actingAsChange}</div>
    </f:with>
</fieldset>
