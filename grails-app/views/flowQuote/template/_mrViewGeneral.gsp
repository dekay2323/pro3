<h2>General</h2>
<fieldset class="form">
    <f:with bean="materialRequest">
        <g:hiddenField name="project" value="${materialRequest?.project?.id}" />
        <g:hiddenField name="status" value="${materialRequest?.status?.id}" />
        <div class="fieldcontain"><label>Client</label>${client}</div>
        <div class="fieldcontain"><label>Project</label>${materialRequest?.project}</div>
        <div class="fieldcontain"><label>Status</label>${materialRequest?.status?.name}</div>
        <div class="fieldcontain"><label>ReqNumber</label>${reqNumber}</div>
        <div class="fieldcontain"><label>Description</label>${description}</div>
        <div class="fieldcontain"><label>Budget</label>${budget}</div>
        <div class="fieldcontain"><label>Ras Date</label>${rasDate}</div>
        <div class="fieldcontain"><label>Est Lead Time</label>${estLeadTime}</div>
        <div class="fieldcontain"><label>Strategy</label>${strategy}</div>
    </f:with>
</fieldset>