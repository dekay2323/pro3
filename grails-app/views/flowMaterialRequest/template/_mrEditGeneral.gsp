<h2>General</h2>
<fieldset class="form">
    <f:with bean="materialRequest">
        <g:hiddenField name="project" value="${materialRequest?.project?.id}" />
        <g:hiddenField name="status" value="${materialRequest?.status?.id}" />
        <div class="fieldcontain">
            <pro3:labelfield label="Client" type="text" name="client" value="${client}" readonly="true"/>
        </div>
        <div class="fieldcontain">
            <pro3:labelfield label="Project" type="text" name="project" value="${materialRequest?.project}" readonly="true"/>
        </div>
        <div class="fieldcontain">
            <pro3:labelfield label="Status" type="text" name="status" value="${materialRequest?.status?.name}" readonly="true"/>
        </div>
        <div class="fieldcontain">
            <pro3:labelfield label="Code" type="text" name="code" value="${materialRequest.code}" readonly="${readonly}"/>
        </div>
        <div class="fieldcontain">
            <pro3:labelfield label="Name" type="text" name="name" value="${materialRequest.name}" readonly="${readonly}"/>
        </div>
        <div class="fieldcontain">
            <pro3:labelfield label="Budget" type="number" name="budget" value="${materialRequest.budget}" readonly="${readonly}"/>
        </div>
        <div class="fieldcontain">
            <pro3:labelfield tooltip="Required at Site Date" label="Ras Date" type="date" name="rasDate" value="${materialRequest.rasDate}" readonly="${readonly}"/>
        </div>
        <f:field property="strategy" />
    </f:with>
</fieldset>
