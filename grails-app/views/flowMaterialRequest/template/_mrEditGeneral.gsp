<h2>General</h2>
<fieldset class="form">
    <f:with bean="materialRequest">
        <g:hiddenField name="project" value="${materialRequest?.project?.id}" />
        <g:hiddenField name="status" value="${materialRequest?.status?.id}" />
        <div class="fieldcontain"><label>Client</label>${client}</div>
        <div class="fieldcontain"><label>Project</label>${materialRequest?.project}</div>
        <div class="fieldcontain"><label>Status</label>${materialRequest?.status?.name}</div>
        <f:field property="reqNumber" />
        <f:field property="description" />
        <f:field property="budget" />
        <div class="fieldcontain">
            <label>Ras Date</label>
            <g:datePicker default='none' precision="day" relativeYears="[0..25]" noSelection="['':'']" name="rasDate" value="${materialRequest?.rasDate}"/>
        </div>
        <f:field property="estLeadTime" />
        <f:field property="strategy" />
    </f:with>
</fieldset>