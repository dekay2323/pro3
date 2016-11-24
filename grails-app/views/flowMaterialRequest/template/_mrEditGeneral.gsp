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
            <label>Ras Date</label>
            <g:datePicker default='none' precision="day" relativeYears="[0..25]" noSelection="['':'']" name="rasDate" value="${materialRequest?.rasDate}"/>
        </div>
        <f:field property="estLeadTime" />
        <div class="fieldcontain">
            <label>Closing Date</label>
            <g:datePicker default='none' precision="day" relativeYears="[0..25]" noSelection="['':'']" name="rasDate" value="${materialRequest?.closingDate}"/>
        </div>
        <f:field property="strategy" />
    </f:with>
</fieldset>
