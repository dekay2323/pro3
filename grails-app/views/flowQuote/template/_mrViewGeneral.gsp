<h2>General</h2>
<fieldset class="form">
    <f:with bean="materialRequest">
        <g:hiddenField name="project" value="${materialRequest?.project?.id}" />
        <div class="fieldcontain"><label>Client</label>${client}</div>
        <div class="fieldcontain"><label>Project</label>${materialRequest?.project}</div>
        <div class="fieldcontain"><label>Status</label>${materialRequest?.status?.name}</div>
        <div class="fieldcontain"><label>Code</label>${materialRequest?.code}</div>
        <div class="fieldcontain"><label>Description</label>${materialRequest?.description}</div>
        <div class="fieldcontain"><label>Budget</label>${materialRequest?.budget}</div>
        <div class="fieldcontain"><label>Ras Date</label>${materialRequest?.rasDate}</div>
        <div class="fieldcontain"><label>Strategy</label>${materialRequest?.strategy}</div>
        <div class="fieldcontain"><label>Closing Date</label>${materialRequest?.closingDate} (${materialRequest?.daysLeftTillClose()})</div>
    </f:with>
</fieldset>
