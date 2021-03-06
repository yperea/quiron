<%@include file="../shared/tag-libs.jsp"%>
<c:set var="root" value="/quiron" scope="session" />

<%--
<c:if test="${account.message != null}">

    <c:choose>
        <c:when test = "${account.message.type == 'ERROR'}">
            <c:set var="alertType" value="alert-danger" scope="request" />
        </c:when>

        <c:when test = "${account.message.type == 'WARNING'}">
            <c:set var="alertType" value="alert-warning" scope="request" />
        </c:when>

        <c:otherwise>
            <c:set var="alertType" value="alert-info" scope="request" />
        </c:otherwise>
    </c:choose>

    <c:set var="alertDescription" value="${account.message.description}" scope="request" />
    <c:set var="redirectUrl" value="${account.message.redirect}" scope="request" />
    <c:remove var="account" scope="session" />
</c:if>
--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../shared/meta.jsp"%>
    <link rel="icon" href="${root}/style/img/QuironIcon75x75.png">

    <title>Signin</title>

    <%@include file="../shared/cdn-css.jsp"%>

    <!-- Bootstrap validator CSS -->
    <link href="${root}/style/css/form-validation.css" rel="stylesheet" type="text/css" />
    <!-- Bootstrap Signin CSS -->
    <link href="${root}/style/css/signin.css" rel="stylesheet" type="text/css" />
    <!-- Project Quiron Style Sheet -->
    <link href="${root}/style/css/main.css" rel="stylesheet" type="text/css" />


</head>

<body class="text-center">


<form class="form-signin" action="j_security_check?test=1" method="POST">
    <img class="mb-4" src="${root}/style/img/QuironIcon75x75.png" alt="" width="72" height="72">
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>


<%--
    <c:if test="${alertType != null}">
    <div class="alert ${alertType}" role="alert">${alertDescription}
        <c:if test="${redirectUrl != null}">
        <a href="${redirectUrl}" class="alert-link">CLICK HERE</a> to continue.
        </c:if>
    </div>
    </c:if>
--%>

    <yp:alert type="${message.type}" url="${message.redirect}">${message.description}</yp:alert>
    <%--<c:remove var="message" scope="session" />--%>
    <c:remove var="message"/>

    <label for="username" class="sr-only">Email address</label>
    <input type="text" id="username" name="j_username" class="form-control" placeholder="Username" required autofocus>
    <label for="password" class="sr-only">Password</label>
    <input type="password" id="password" name="j_password" class="form-control" placeholder="Password" required>

    <div class="checkbox mb-3">
<%--
        <label>
            <input type="checkbox" value="remember-me"> Remember me
        </label>
--%>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="login-type1" name="login-type" value="${root}/account/router?tp=patient" class="custom-control-input" required />
            <label class="custom-control-label" for="login-type1">Patient</label>
        </div>
        <div class="custom-control custom-radio custom-control-inline">
            <input type="radio" id="login-type2" name="login-type" value="${root}/account/router?tp=provider" class="custom-control-input" required />
            <label class="custom-control-label" for="login-type2">Provider</label>
        </div>

    </div>

    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    <p class="mt-5 mb-3 text-muted">Don't have an account?
        <a href="${root}/account/signup">Sign Up</a> | <a href="${root}">Home</a> <br/>
        <a href="${root}/account/router?tp=provider">Are you a provider?</a>
    </p>
</form>

<%@include file="../shared/cdn-jss.jsp"%>

<!-- Bootstrap Core JavaScript -->
<script src="${root}/style/js/holder.min.js"></script>

<script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function() {
        'use strict';

        window.addEventListener('load', function() {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');

            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function(form) {
                form.addEventListener('submit', function(event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>
<!-- Reference to local JS library -->
<script type="text/javascript" charset="utf-8" src="${root}/style/js/signin.js" ></script>
</body>
</html>

