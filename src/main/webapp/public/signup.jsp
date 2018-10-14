<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@include file="../shared/head.jsp"%>
</head>

<body class="bg-light">
    <!-- Navigation -->
    <%@include file="../shared/topbar.jsp" %>

    <div class="container">
        <div class="py-5 text-center">
            <img class="d-block mx-auto mb-4" src="${root}/style/img/QuironIcon75x75.png" alt="" width="72" height="72">
            <h2>Sign Up</h2>
            <p class="lead">Project Quiron makes it easy for you to track your treatments.</p>
        </div>

        <div class="row justify-content-center">
            <div class="col-md-8">
                <!--<h4 class="mb-3">Your person</h4>-->
                <form class="needs-validation"
                      action="${root}/public/signup"
                      method="POST"
                      novalidate>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="firstName">First name</label>
                            <input type="text" class="form-control" id="firstName" placeholder="" value="" required>
                            <div class="invalid-feedback">
                                Valid first name is required.
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="lastName">Last name</label>
                            <input type="text" class="form-control" id="lastName" placeholder="" value="" required>
                            <div class="invalid-feedback">
                                Valid last name is required.
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="username">Username <span class="text-muted"></span></label>
                        <input type="username" class="form-control" id="username" placeholder="Username" required>
                        <div class="invalid-feedback">
                            Your username is required.
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="email">Email</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">@</span>
                            </div>
                            <input type="email" class="form-control" id="email" placeholder="you@example.com" required>
                            <div class="invalid-feedback" style="width: 100%;">
                                Please enter a valid email address.
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="password">Password</label>
                            <input type="password" class="form-control" id="password" placeholder="" value="" required>
                            <div class="invalid-feedback">
                                Valid password is required.
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="password2">Confirmation</label>
                            <input type="password" class="form-control" id="password2" placeholder="Password Confirmation" value="" required>
                            <div class="invalid-feedback">
                                Valid password is required.
                            </div>
                        </div>
                    </div>

                    <hr class="mb-4">

                    <div class="checkbox mb-3">
                        <label>
                            <input type="checkbox" value="i-agree"> I agree to these <a href="#">Terms of Use.</a>
                        </label>
                    </div>
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Sign Up</button>
                </form>
            </div>

        </div>
    </div>
    <%@include file="../shared/cdn-jss.jsp"%>
    <%@include file="../shared/footer.jsp"%>
</body>
</html>



