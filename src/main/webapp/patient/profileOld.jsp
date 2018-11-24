<%@include file="../shared/tag-libs.jsp"%>
<%--
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="yp" uri="http://quiron.net.co/tag" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
--%>

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
            <h1 class="h2">My Profile</h1>

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

        </div>
        <br/>
        <div class="row justify-content-center">
            <div class="col-md-9">
                <!--<h4 class="mb-3">Your person</h4>-->

                <yp:alert type="${message.type}" url="${message.redirect}">${message.description}</yp:alert>
                <c:remove var="message" scope="session" />

                <form class="needs-validation"
                      action="${root}/patient/profile"
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
                                   value="${profile.patient.firstName}"
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
                                   value="${profile.patient.lastName}"
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
                                   value="<tags:localDate date="${profile.patient.birthDate}" pattern="MM/d/yyyy"/>"
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
                                <option value="M" <c:if test="${profile.patient.gender == 'M'}">selected</c:if> >Male</option>
                                <option value="F" <c:if test="${profile.patient.gender == 'F'}">selected</c:if> >Female</option>
                            </select>
                            <div class="invalid-feedback">
                                Please provide a gender.
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="address1">Address</label>
                        <input type="text"
                               class="form-control"
                               id="address1"
                               name="address1"
                               placeholder=""
                               value="${profile.address.addressLine1}"
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
                               value="${profile.address.addressLine2}"
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
                                   value="${profile.address.city}"
                                   required />
                            <div class="invalid-feedback">
                                City required.
                            </div>
                        </div>
                        <div class="col-md-4 mb-3">
                            <label for="state">State ${profile.address.state.id}</label>
                            <select class="custom-select d-block w-100"
                                    id="state"
                                    name="state"
                                    required />
                                <option value="">Choose...</option>
                                <c:forEach var="state" items="${states}">
                                <option value="${state.id}" <c:if test="${profile.address.state.id == state.id}">selected</c:if> >${state.code} - ${state.name}</option>
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
                                   value="${profile.address.postalCode}"
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

        <!--
        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
            <h4 class="h4">Details</h4>
        </div>

        <div class="row justify-content-center">

            <div class="table-responsive col-md-9">
                <table class="table table-striped table-sm">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Header</th>
                        <th>Header</th>
                        <th>Header</th>
                        <th>Header</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>1,001</td>
                        <td>Lorem</td>
                        <td>ipsum</td>
                        <td>dolor</td>
                        <td>sit</td>
                    </tr>
                    <tr>
                        <td>1,002</td>
                        <td>amet</td>
                        <td>consectetur</td>
                        <td>adipiscing</td>
                        <td>elit</td>
                    </tr>
                    <tr>
                        <td>1,003</td>
                        <td>Integer</td>
                        <td>nec</td>
                        <td>odio</td>
                        <td>Praesent</td>
                    </tr>
                    <tr>
                        <td>1,003</td>
                        <td>libero</td>
                        <td>Sed</td>
                        <td>cursus</td>
                        <td>ante</td>
                    </tr>
                    <tr>
                        <td>1,004</td>
                        <td>dapibus</td>
                        <td>diam</td>
                        <td>Sed</td>
                        <td>nisi</td>
                    </tr>
                    <tr>
                        <td>1,005</td>
                        <td>Nulla</td>
                        <td>quis</td>
                        <td>sem</td>
                        <td>at</td>
                    </tr>
                    <tr>
                        <td>1,006</td>
                        <td>nibh</td>
                        <td>elementum</td>
                        <td>imperdiet</td>
                        <td>Duis</td>
                    </tr>
                    <tr>
                        <td>1,007</td>
                        <td>sagittis</td>
                        <td>ipsum</td>
                        <td>Praesent</td>
                        <td>mauris</td>
                    </tr>
                    <tr>
                        <td>1,008</td>
                        <td>Fusce</td>
                        <td>nec</td>
                        <td>tellus</td>
                        <td>sed</td>
                    </tr>
                    <tr>
                        <td>1,009</td>
                        <td>augue</td>
                        <td>semper</td>
                        <td>porta</td>
                        <td>Mauris</td>
                    </tr>
                    <tr>
                        <td>1,010</td>
                        <td>massa</td>
                        <td>Vestibulum</td>
                        <td>lacinia</td>
                        <td>arcu</td>
                    </tr>
                    <tr>
                        <td>1,011</td>
                        <td>eget</td>
                        <td>nulla</td>
                        <td>Class</td>
                        <td>aptent</td>
                    </tr>
                    <tr>
                        <td>1,012</td>
                        <td>taciti</td>
                        <td>sociosqu</td>
                        <td>ad</td>
                        <td>litora</td>
                    </tr>
                    <tr>
                        <td>1,013</td>
                        <td>torquent</td>
                        <td>per</td>
                        <td>conubia</td>
                        <td>nostra</td>
                    </tr>
                    <tr>
                        <td>1,014</td>
                        <td>per</td>
                        <td>inceptos</td>
                        <td>himenaeos</td>
                        <td>Curabitur</td>
                    </tr>
                    <tr>
                        <td>1,015</td>
                        <td>sodales</td>
                        <td>ligula</td>
                        <td>in</td>
                        <td>libero</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        -->
    </main>

    <%@include file="../shared/cdn-jss.jsp"%>
    <%@include file="../shared/footer.jsp"%>

</body>
</html>
