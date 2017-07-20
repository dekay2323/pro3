<!DOCTYPE html>
<html>
<head>
    <sec:ifAnyGranted roles='ROLE_VENDOR'>
        <meta name="layout" content="vendor" />
    </sec:ifAnyGranted>
    <sec:ifAnyGranted roles='ROLE_ADMIN,ROLE_USER'>
        <meta name="layout" content="main" />
    </sec:ifAnyGranted>
    <sec:ifNotLoggedIn>
        <meta name="layout" content="main" />
    </sec:ifNotLoggedIn>
</head>
<body>

<div id="contactForm" class="container" role="main">
    <h1>Thanks</h1>

    <div class="jumbotron">
        <div class="row">
            <div class="col-sm-1"></div>
            <div class="col-sm-10">Thank you for your interest in Procurable!</div>
            <div class="col-sm-1"></div>
        </div>
        <br />
        <div class="row">
            <div class="col-sm-1"></div>
            <div class="col-sm-10">
                We are excited to bring a simple and efficient solution to the worlds infrastructure project procurement needs.
            </div>
            <div class="col-sm-1"></div>
        </div>
        <div class="row">
            <div class="col-sm-1"></div>
            <div class="col-sm-10">
                Stay tuned for updates on the release of the Procurable Procurement Module.
                We would be very interested in speaking with you briefly regarding the needs of your business, a representative from Procurable will be in touch in the near future.
            </div>
            <div class="col-sm-1"></div>
        </div>
    </div>
    
</div>
</body>
</html>