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
            <g:field type="text" name="reqNumber" value="${materialRequest.reqNumber}" />
        </div>
        <div class="fieldcontain">
            <label for="description">Description</label>
            <g:field type="text" name="description" value="${materialRequest.description}" />
        </div>
        <div class="fieldcontain">
            <pro3:labelfield label="Budget" type="number" name="budget" value="${materialRequest.budget}" readonly="${readonly}"/>
        </div>
        <div class="fieldcontain">
            <label title="Required at Site Date">Ras Date <i class="fa fa-info-circle fa-1" aria-hidden="true"></i></label>
            <g:if test="${!readonly}">
                <g:field type="date" name="rasDate" value="${materialRequest?.rasDate}" />
            </g:if>
            <g:else>
                ${materialRequest?.rasDate}                
            </g:else>
        </div>
        <div class="fieldcontain">
            <label>Closing Date <span class="required-indicator">*</span></label>
            <g:field type="date" name="closingDate" value="${materialRequest?.closingDate}" />
        </div>
        <f:field property="strategy" />
    </f:with>
</fieldset>
