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
            <h1 class="h2">My Treatments</h1>
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
                            <th>Start Date</th>
                            <th>End Date</th>
                            <th>Completed</th>
                            <th>Symptom</th>
                            <th>Medication</th>
                            <th>Evaluation</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="treatment" items="${treatments}">
                        <tr>
                            <td><a class="" href="${root}/treatment?id=${treatment.id}" role="button">${treatment.id}</a></td>
                            <td>${treatment.startDate}</td>
                            <td>${treatment.endDate}</td>
                            <td>
                                <c:if test="${treatment.status != 'A'}">Yes</c:if>
                                <c:if test="${treatment.status == 'A'}">No</c:if>
                            </td>
                            <td>${treatment.visit.symptomName}</td>
                            <td>
                                <c:forEach var="prescription" items="${treatment.prescriptions}">
                                    ${prescription.medication.name}
                                </c:forEach>
                            </td>
                            <td>${treatment.evaluation}</td>
                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <br/>
    </main>

    <%@include file="../shared/cdn-jss.jsp"%>
    <%@include file="../shared/footer.jsp"%>

</body>
</html>
