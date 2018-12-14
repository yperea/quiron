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
            <h1 class="h2">${title}</h1>
            <c:if test="${account.profile.personType == 'patient'}">
                <div class="btn-toolbar mb-2 mb-md-0">
                    <div class="btn-group mr-2">
                        <a class="btn btn-sm btn-outline-danger" href="${root}/patient/insurance" role="button">Health Insurance</a>
                        &nbsp;
                        <a class="btn btn-sm btn-outline-warning" href="#" role="button">Pharmacies</a>
                        &nbsp;
                        <a class="btn btn-sm btn-outline-success" href="#" role="button">Payment Info</a>
                        &nbsp;
                    </div>
                    <!--
                    <button class="btn btn-sm btn-outline-secondary dropdown-toggle">
                        <span data-feather="calendar"></span>
                        This week
                    </button>
                    -->
                </div>
            </c:if>
        </div>
        <br/>
        <div class="row justify-content-center">
            <div class="col-md-9">
                <!--<h4 class="mb-3">Your person</h4>-->

                <yp:alert type="${message.type}" url="${message.redirect}">${message.description}</yp:alert>
                <c:remove var="message" scope="session" />

                <form class="needs-validation"
                      action="${root}/account"
                      method="POST"
                      novalidate>
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="firstName">First name</label>
                            <input type="text"
                                   class="form-control"
                                   id="firstName"
                                   name="firstName"
                                   placeholder=""
                                   value="${account.profile.firstName}"
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
                                   value="${account.profile.lastName}"
                                   required />
                            <div class="invalid-feedback">
                                Valid last name is required.
                            </div>
                        </div>
                    </div>

                    <c:if test="${account.profile.personType == 'patient'}">
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="birthDate">Date of Birth</label>
                            <input type="text"
                                   class="form-control"
                                   id="birthDate"
                                   name="birthDate"
                                   placeholder="mm/dd/yyyy"
                                   value="<tags:localDate date="${account.profile.birthDate}" pattern="MM/d/yyyy"/>"
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
                                <option value="M" <c:if test="${account.profile.gender == 'M'}">selected</c:if> >Male</option>
                                <option value="F" <c:if test="${account.profile.gender == 'F'}">selected</c:if> >Female</option>
                            </select>
                            <div class="invalid-feedback">
                                Please provide a gender.
                            </div>
                        </div>
                    </div>
                    </c:if>
                    <c:if test="${account.profile.personType == 'provider'}">
                    <div class="mb-3">
                        <label for="npi">NPI</label>
                        <input type="text"
                               class="form-control"
                               id="npi"
                               name="npi"
                               placeholder=""
                               value="${account.profile.npi}"
                               required />
                        <div class="invalid-feedback">
                            Please enter your NPI.
                        </div>
                    </div>
                    </c:if>

                    <div class="mb-3">
                        <label for="address1">Address</label>
                        <input type="text"
                               class="form-control"
                               id="address1"
                               name="address1"
                               placeholder=""
                               value="${account.profile.address.addressLine1}"
                               required />
                        <div class="invalid-feedback">
                            Please enter your address.
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="address2">Address 2 <span class="text-muted">(Optional)</span></label>
                        <input type="text"
                               class="form-control"
                               id="address2"
                               name="address2"
                               value="${account.profile.address.addressLine2}"
                               placeholder="" />
                    </div>

                    <div class="row">
                        <div class="col-md-5 mb-3">
                            <label for="city">City</label>
                            <input type="text"
                                   class="form-control"
                                   id="city"
                                   name="city"
                                   placeholder=""
                                   value="${account.profile.address.city}"
                                   required />
                            <div class="invalid-feedback">
                                City required.
                            </div>
                        </div>
                        <div class="col-md-4 mb-3">
                            <label for="state">State</label>
                            <select class="custom-select d-block w-100"
                                    id="state"
                                    name="state"
                                    required />
                                <option value="">Choose...</option>
                                <c:forEach var="state" items="${states}">
                                <option value="${state.id}" <c:if test="${account.profile.address.state.id == state.id}">selected</c:if> >${state.code} - ${state.name}</option>
                                </c:forEach>
                            </select>
                            <div class="invalid-feedback">
                                Please provide a valid state.
                            </div>
                        </div>
                        <div class="col-md-3 mb-3">
                            <label for="zip">Zip</label>
                            <input type="text"
                                   class="form-control"
                                   id="zip"
                                   name="zip"
                                   value="${account.profile.address.postalCode}"
                                   placeholder=""
                                   required />
                            <div class="invalid-feedback">
                                Zip code required.
                            </div>
                        </div>
                    </div>

                    <hr class="mb-4">

                    <button class="btn btn-success btn-lg btn-block" type="submit">Save</button>
                </form>
            </div>
        </div>
        <br/>
    </main>

    <%@include file="../shared/cdn-jss.jsp"%>
    <%@include file="../shared/footer.jsp"%>
    <!-- Reference to local JS library -->
    <script type="text/javascript" charset="utf-8" src="${root}/style/js/signup.js" ></script>

</body>
</html>
