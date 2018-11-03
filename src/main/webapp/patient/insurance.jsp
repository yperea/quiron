<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="yp" uri="http://yperea.co/ctags" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

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
                    <a class="btn btn-sm btn-danger" href="#" role="button">Health Insurance</a>
                    &nbsp;
                    <a class="btn btn-sm btn-outline-warning" href="#" role="button">Pharmacies</a>
                    &nbsp;
                    <a class="btn btn-sm btn-outline-success" href="#" role="button">Payment Info</a>
                    &nbsp;
                </div>
            </div>

        </div>
        <br/>
        <div class="row justify-content-center">
            <div class="col-md-9">
                <!--<h4 class="mb-3">Your person</h4>-->

                <yp:alert type="${message.type}" url="${message.redirect}">${message.description}</yp:alert>
                <c:remove var="message" scope="session" />

                <form class="needs-validation"
                      action="${root}/patient/insurance"
                      method="POST"
                      novalidate>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="company">Health Insurance</label>
                            <select class="custom-select d-block w-100"
                                    id="company"
                                    name="company"
                                    required />
                            <option value="">Choose...</option>
                            <c:forEach var="company" items="${companies}">
                                <option value="${company.id}" <c:if test="${profile.patient.organization.id == company.id}">selected</c:if> >${company.name}</option>
                            </c:forEach>
                            </select>
                            <div class="invalid-feedback">
                                Please provide a valid state.
                            </div>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="subscriber">Subscriber Id</label>
                            <input type="text"
                                   class="form-control"
                                   id="subscriber"
                                   name="subscriber"
                                   placeholder=""
                                   value="${profile.patient.subscriberCode}"
                                   required />
                            <div class="invalid-feedback">
                                Please enter a valid date of birth.
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

</body>
</html>
