<%@include file="../shared/tag-libs.jsp"%>
<!DOCTYPE html>
<html lang="en">
<%@include file="../shared/head.jsp"%>
<body>

<div id="wrapper">

    <!-- Navigation -->
    <%@include file="../shared/topbar.jsp"%>
    <%@include file="../shared/sidebar.jsp"%>

    <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
            <h1 class="h2">My Account</h1>
        </div>
        <br/>
        <div class="row justify-content-center">
            <div class="col-md-11">
                <!--<h4 class="mb-3">Your person</h4>-->
                <yp:alert type="${message.type}" url="${message.redirect}">${message.description}</yp:alert>
                <c:remove var="message" scope="session" />

                <form class="needs-validation"
                      action="${root}/account/credentials"
                      method="POST"
                      novalidate>

                    <div class="mb-3">
                        <label for="userName">Username <span class="text-muted"></span></label>
                        <input type="text"
                               class="form-control"
                               id="userName"
                               name="userName"
                               placeholder="Username"
                               value="${account.username}"
                               required
                               readonly />
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
                                   value="${account.email}"
                                   required
                                   readonly />
                            <div class="invalid-feedback" style="width: 100%;">
                                Please enter a valid email address.
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="password">New Password</label>
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
                            <label for="confirmation">Password Confirmation</label>
                            <input type="password"
                                   class="form-control"
                                   id="confirmation"
                                   name="confirmation"
                                   placeholder=""
                                   value=""
                                   required />
                            <div class="invalid-feedback">
                                Valid password is required.
                            </div>
                        </div>
                    </div>

                    <hr class="mb-4">

                    <button class="btn btn-primary btn-lg btn-block" type="submit">Save</button>
                </form>
            </div>
        </div>
        <br/>
    </main>

    <%@include file="../shared/cdn-jss.jsp"%>
    <%@include file="../shared/footer.jsp"%>

</body>
</html>
