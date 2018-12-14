<%@include file="../shared/tag-libs.jsp"%>
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
                      action="${root}/account/signup"
                      method="POST"
                      novalidate>

                    <input type="hidden"
                           id="personType"
                           name="personType"
                           value="patient"/>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="firstName">First name</label>
                            <input type="text"
                                   class="form-control"
                                   id="firstName"
                                   name="firstName"
                                   placeholder=""
                                   value="${firstName}"
                                   required />
                            <div class="invalid-feedback">
                                Valid first name is required.
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="lastName">Last name</label>
                            <input type="text"
                                   class="form-control"
                                   id="lastName"
                                   name="lastName"
                                   placeholder=""
                                   value="${lastName}"
                                   required />
                            <div class="invalid-feedback">
                                Valid last name is required.
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="birthDate">Date of Birth</label>
                            <input type="text"
                                   class="form-control"
                                   id="birthDate"
                                   name="birthDate"
                                   placeholder="mm/dd/yyyy"
                                   value="${birthDate}"
                                   required />
                            <div class="invalid-feedback">
                                Please enter a valid date of birth.
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="gender">Gender</label>
                            <select class="custom-select d-block w-100"
                                    id="gender"
                                    name="gender"
                                    required />
                            <option value="">Choose...</option>
                            <option value="M" <c:if test="${gender == 'M'}">selected</c:if> >Male</option>
                            <option value="F" <c:if test="${gender == 'F'}">selected</c:if> >Female</option>
                            </select>
                            <div class="invalid-feedback">
                                Please provide a gender.
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="userName">Username <span class="text-muted"></span></label>
                        <input type="text"
                               class="form-control"
                               id="userName"
                               name="userName"
                               placeholder="Username"
                               value="${userName}"
                               required />
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
                            <input type="email"
                                   class="form-control"
                                   id="email"
                                   name="email"
                                   placeholder="you@example.com"
                                   value="${email}"
                                   required />
                            <div class="invalid-feedback" style="width: 100%;">
                                Please enter a valid email address.
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="password">Password</label>
                            <input type="password"
                                   class="form-control"
                                   id="password"
                                   name="password"
                                   placeholder=""
                                   value=""
                                   required />
                            <div class="invalid-feedback">
                                Valid password is required.
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="confirmation">Confirmation</label>
                            <input type="password"
                                   class="form-control"
                                   id="confirmation"
                                   name="confirmation"
                                   placeholder="Password Confirmation"
                                   value=""
                                   required />
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
    <!-- Reference to local JS library -->
    <script type="text/javascript" charset="utf-8" src="${root}/style/js/signup.js" ></script>

</body>
</html>



