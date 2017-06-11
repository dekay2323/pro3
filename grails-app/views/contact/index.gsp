<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main" />
    <title>Procurable</title>
</head>
<body>


<div id="contactForm" class="content scaffold-create" role="main">
    <h1>Contact</h1>

    <form action="https://formspree.io/contact@procurableapp.com" method="POST">
        <input type="hidden" name="_next" value="/contact/complete" />

        <fieldset class="form">
            <div class="fieldcontain">
                <label>Email</label>
                <input type="email" name="_replyto" value=""/>
            </div>
            <div class="fieldcontain">
                <label>Reason</label>
                <g:select name="reason" from="${['Question', 'Report a Problem', 'Request a Feature']}" value="Question" />
            </div>
            <div class="fieldcontain">
                <label>Message</label>
                <textarea rows="10" cols="250" name="message" value=""></textarea>
            </div>
        </fieldset>

        <fieldset class="buttons">
            <input type="submit" value="Send">
        </fieldset>
    </form>
</div>
</body>
</html>