<h2>General</h2>
<fieldset class="form">
    <div class="fieldcontain"><label>Client</label>${client}</div>
    <div class="fieldcontain"><label>Project</label>${materialRequest?.project}</div>
    <div class="fieldcontain"><label>Status</label>${materialRequest?.status?.name}</div>
    <div class="fieldcontain"><label>Req Number</label>${materialRequest?.reqNumber}</div>
    <div class="fieldcontain"><label>Description</label>${materialRequest?.description}</div>
    <div class="fieldcontain"><label>Budget</label>${materialRequest?.budget}</div>
    <div class="fieldcontain"><label>Ras Date</label>${materialRequest?.rasDate}</div>
    <div class="fieldcontain"><label>Est Lead Time</label>${materialRequest?.estLeadTime}</div>
    <div class="fieldcontain"><label>Strategy</label>${materialRequest?.strategy}</div>
</fieldset>