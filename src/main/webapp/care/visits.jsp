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
            <h1 class="h2">My Visits</h1>
            <div class="btn-toolbar mb-2 mb-md-0">
                <div class="btn-group mr-2">
                    <a class="btn btn-sm btn<c:if test="${state != null && state != 'A'}">-outline</c:if>-warning" href="<c:if test="${state == null || state == 'A'}">#</c:if><c:if test="${state != null && state != 'A'}">${root}/visits</c:if>" role="button">Upcoming</a>
                    &nbsp;
                    <a class="btn btn-sm btn<c:if test="${state != 'C'}">-outline</c:if>-success" href="<c:if test="${state == 'C'}">#</c:if><c:if test="${state != 'C'}">${root}/visits?state=C</c:if>" role="button">Completed</a>
                    &nbsp;
                    <a class="btn btn-sm btn<c:if test="${state != 'D'}">-outline</c:if>-danger" href="<c:if test="${state == 'D'}">#</c:if><c:if test="${state != 'D'}">${root}/visits?state=D</c:if>" role="button">Canceled</a>
                    &nbsp;
                </div>
            </div>
        </div>
        <br/>
        <div class="row justify-content-center">
            <div class="col-md-11">
                <!--<h4 class="mb-3">Your person</h4>-->
                <yp:alert type="${message.type}" url="${message.redirect}">${message.description}</yp:alert>
                <c:remove var="message" scope="session" />

                <div class="table-responsive col-md-12">
                    <table class="table table-striped table-sm">
                        <thead>
                        <tr>
                            <th>Id</th>
                            <th>Date</th>
                            <th>Status</th>
                            <th>
                            <c:if test="${account.profile.personType == 'patient'}">
                                Provider
                            </c:if>
                            <c:if test="${account.profile.personType == 'provider'}">
                                Patient
                            </c:if>
                            </th>
                            <th>Service</th>
                            <th>Location</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="visit" items="${visits}">
                        <tr>
                            <td><a class="" href="${root}/care/visit?id=${visit.id}" role="button">${visit.id}</a></td>
                            <c:if test="${visit.actualStartDate == null}">
                                <td><tags:localDateTime date="${visit.scheduledStartDate}" pattern="MM/d/yyyy"/></td>
                            </c:if>
                            <c:if test="${visit.actualStartDate != null}">
                                <td><tags:localDateTime date="${visit.actualStartDate}" pattern="MM/d/yyyy"/></td>
                            </c:if>
                            <td>
                                <c:if test="${treatment.status == 'A'}">Upcoming</c:if>
                                <c:if test="${treatment.status == 'C'}">Completed</c:if>
                                <c:if test="${treatment.status == 'D'}">Canceled</c:if>
                            </td>
                            <td>
                                <c:if test="${account.profile.personType == 'patient'}">
                                    ${visit.providerSchedule.provider.firstName} ${visit.providerSchedule.provider.lastName}
                                </c:if>
                                <c:if test="${account.profile.personType == 'provider'}">
                                    ${visit.patient.firstName} ${visit.patient.lastName}
                                </c:if>
                            </td>
                            <td>${visit.service.name}</td>
                            <td>${visit.providerSchedule.organization.name} ${visit.providerSchedule.address.name}</td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </main>

    <%@include file="../shared/cdn-jss.jsp"%>
    <%@include file="../shared/footer.jsp"%>

</body>
</html>
