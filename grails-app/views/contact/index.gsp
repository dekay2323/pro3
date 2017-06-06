<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Procurable</title>
</head>
<body>

<div id="stats" class="content scaffold-list" role="main">
    <h1>Contact</h1>

    <ol class="property-list">
        <div class="fieldcontain">
            <label>Email</label>
            <g:textField name="email" value="${email}"/>
        </div>
        <div class="fieldcontain">
            <label>Reason</label>
            <g:select name="reason" from="${['Question', 'Report a Problem', 'Request a Feature']}" value="Question" />
        </div>
        <div class="fieldcontain">
            <label>Message</label>
            <g:textArea name="message" />
        </div>
    </ol>
</div>
</body>
</html>