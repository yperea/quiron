<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: yperea
  Date: 10/7/2018
  Time: 12:29 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- meta tags -->
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Project Quiron">
    <meta name="author" content="Yesid Perea">
    <link rel="icon" href="style/img/QuironIcon75x75.png">

    <title>Signin</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
          crossorigin="anonymous">

    <!-- Bootstrap validator CSS -->
    <link href="style/css/form-validation.css" rel="stylesheet" />

    <!-- Bootstrap Signin CSS -->
    <link href="style/css/signin.css" rel="stylesheet" />
    <!-- Project Quiron Style Sheet -->
    <link rel="stylesheet" href="style/css/main.css" />


</head>

<body class="text-center">
<!-- Navigation -->
<%@include file="shared/topbar.jsp" %>

<!--
    <form action="j_security_check" method="POST">
        <TABLE>
            <TR><TD>User name: <input type="TEXT" name="j_username" />
            <TR><TD>Password: <input type="PASSWORD" name="j_password" />
            <TR><TH><input TYPE="SUBMIT" VALUE="Log In" />
        </TABLE>
    </form>
-->
<form class="form-signin" action="j_security_check" method="POST">
    <img class="mb-4" src="style/img/QuironIcon75x75.png" alt="" width="72" height="72">
    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
    <label for="username" class="sr-only">Email address</label>
    <input type="text" id="username" name="j_username" class="form-control" placeholder="Username" required autofocus>
    <label for="password" class="sr-only">Password</label>
    <input type="password" id="password" name="j_password" class="form-control" placeholder="Password" required>
    <div class="checkbox mb-3">
        <label>
            <input type="checkbox" value="remember-me"> Remember me
        </label>
    </div>
    <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    <p class="mt-5 mb-3 text-muted">Don't have an account? <a href="signup.jsp">Sign Up</a></p>
</form>


<!-- Bootstrap JavaScript
================================================== -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>

<!-- Bootstrap Core JavaScript -->
<script src="style/js/holder.min.js"></script>
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



