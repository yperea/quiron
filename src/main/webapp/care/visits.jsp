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
                    <a class="btn btn-sm btn<c:if test="${state != null && state != 'A'}">-outline</c:if>-danger" href="<c:if test="${state == null || state == 'A'}">#</c:if><c:if test="${state != null && state != 'A'}">${root}/patient/visits</c:if>" role="button">Upcoming</a>
                    &nbsp;
                    <a class="btn btn-sm btn<c:if test="${state != 'C'}">-outline</c:if>-warning" href="<c:if test="${state == 'C'}">#</c:if><c:if test="${state != 'C'}">${root}/patient/visits?state=completed</c:if>" role="button">Completed</a>
                    &nbsp;
                    <a class="btn btn-sm btn<c:if test="${state != 'D'}">-outline</c:if>-success" href="<c:if test="${state == 'D'}">#</c:if><c:if test="${state != 'D'}">${root}/patient/visits?state=canceled</c:if>" role="button">Canceled</a>
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

                <div class="table-responsive col-md-12">
                    <table class="table table-striped table-sm">
                        <thead>
                        <tr>
                            <th>#</th>
                            <th>Date</th>
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
                            <td><a class="" href="${root}/patient/visit?id=${visit.id}" role="button">${visit.id}</a></td>
                            <c:if test="${visit.actualStartDate == null}">
                                <td>${visit.scheduledStartDate}</td>
                            </c:if>
                            <c:if test="${visit.actualStartDate != null}">
                                <td>${visit.actualStartDate}</td>
                            </c:if>
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
