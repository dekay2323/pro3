<!doctype html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
    </head>
    <body>
        <g:if env="development">
            <g:if test="${Throwable.isInstance(exception)}">
                <g:renderException exception="${exception}" />
            </g:if>
            <g:elseif test="${request.getAttribute('javax.servlet.error.exception')}">
                <g:renderException exception="${request.getAttribute('javax.servlet.error.exception')}" />
            </g:elseif>
            <g:else>
                <ul class="errors">
                    <li>Not Authorized</li>
                </ul>
            </g:else>
        </g:if>
        <g:else>
            <ul class="errors">
                <li>Not Authorized</li>
            </ul>
        </g:else>
    </body>
</html>
