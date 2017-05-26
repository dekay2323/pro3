<h2>General</h2>
<fieldset class="form">
    <f:with bean="materialRequest">
        <g:hiddenField name="project" value="${materialRequest?.project?.id}" />
        <g:hiddenField name="status" value="${materialRequest?.status?.id}" />
        <div class="fieldcontain"><label>Client</label>${client}</div>
        <div class="fieldcontain"><label>Project</label>${materialRequest?.project}</div>
        <div class="fieldcontain"><label>Status</label>${materialRequest?.status?.name}</div>
        <div class="fieldcontain">
            <label for="reqNumber">Req Number</label>
            <g:textField name="reqNumber" value="${materialRequest.reqNumber}"/>
        </div>
        <f:field property="description" />
        <f:field property="budget" />
        <div class="fieldcontain">
            <label title="Required at Site Date">Ras Date <i class="fa fa-info-circle fa-1" aria-hidden="true"></i></label>
            <g:field type="date" name="rasDate" value="${materialRequest?.rasDate}"/>
        </div>
        <div class="fieldcontain">
            <label>Closing Date <span class="required-indicator">*</span></label>
            <g:field type="date" name="closingDate" value="${materialRequest?.closingDate}"/>
        </div>
        <f:field property="strategy" />
    </f:with>
</fieldset>
