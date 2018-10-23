<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="/quiron" scope="session" />
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

<form class="form-signin" action="j_security_check" method="POST">
    <img class="mb-4" src="../style/img/QuironIcon75x75.png" alt="" width="72" height="72">
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    <label for="username" class="sr-only">Email address</label>
    <input type="text" id="username" name="j_username" class="form-control" placeholder="Username" required autofocus>
    <label for="password" class="sr-only">Password</label>
    <input type="password" id="password" name="j_password" class="form-control" placeholder="Password" required>
    <!--
    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me"> Remember me
        </label>
    </div>
    -->
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    <p class="mt-5 mb-3 text-muted">Don't have an account?
        <a href="${root}/account/signup">Sign Up</a> | <a href="${root}">Home</a>
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

</body>
</html>
