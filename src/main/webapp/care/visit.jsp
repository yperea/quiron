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
            <h1 class="h2">My Visit</h1>

            <div class="btn-toolbar mb-2 mb-md-0">
                <div class="btn-group mr-2">
<%--
                    <a class="btn btn-sm btn-outline-warning" href="#" role="button">Pharmacies</a>
                    &nbsp;
--%>
                    <a class="btn btn-sm btn-outline-success" href="${root}/patient/visits" role="button">My Visits</a>
                    &nbsp;
                    <c:if test="${personType == 'provider' }">
                        <a class="btn btn-sm btn-outline-warning" href="${root}/patient/treatment" role="button">Add Prescription</a>
                        &nbsp;
                    </c:if>

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

<!---------------------------------------------------------------------------->
            <div class="col-md-9">
                <!--<h4 class="mb-3">Your person</h4>-->

                <yp:alert type="${message.type}" url="${message.redirect}">${message.description}</yp:alert>
                <c:remove var="message" scope="session" />
                <c:set var="now" value="<%= new java.util.Date()%>" />
                <%--<c:set var="personType" value="provider" />--%>

                <form class="needs-validation"
                      action="${root}/patient/visit"
                      method="POST"
                      novalidate>

                    <input type="hidden" id="visitId" name="visitId"
                           value="${visitId}"/>

                    <input type="hidden" id="diagnosticId" name="diagnosticId"
                           value="${visit.diagnosticId}"/>

                    <input type="hidden" id="diagnosticName" name="diagnosticName"
                           value="${visit.diagnosticName}"/>

                    <input type="hidden" id="gender" name="gender"
                           value="${account.profile.gender}"/>

                    <input type="hidden" id="birthYear" name="birthYear"
                           value="<tags:localDate date="${account.profile.birthDate}" pattern="yyyy"/>" />

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="organization">Organization</label>
                            <input type="text"
                                   class="form-control"
                                   id="organization"
                                   name="organization"
                                   placeholder=""
                                   value="${visit.providerSchedule.organization.name}"
                                   disabled
                                   required />
                            <div class="invalid-feedback">
                                Organization required.
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="provider">Provider</label>
                            <input type="text"
                                   class="form-control"
                                   id="provider"
                                   name="provider"
                                   placeholder=""
                                   value="${visit.providerSchedule.provider.lastName} , ${visit.providerSchedule.provider.firstName}"
                                   disabled
                                   required />
                            <div class="invalid-feedback">
                                Provider required.
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="scheduledStartDate">Scheduled Date</label>
                            <input type="text"
                                   class="form-control"
                                   id="scheduledStartDate"
                                   name="scheduledStartDate"
                                   placeholder="mm/dd/yyyy"
                                   value="<tags:localDate date="${visit.scheduledStartDate}" pattern="MM/d/yyyy"/>"
                                   disabled
                                   required />
                            <div class="invalid-feedback">
                                Please enter a valid date.
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="status">Status</label>
                            <select class="custom-select d-block w-100"
                                    id="status"
                                    name="status"
                                    disabled
                                    required />
                                <option value="">Choose...</option>
                                <option value="A" <c:if test="${visit.status == 'A'}">selected</c:if>>Active</option>
                                <option value="C" <c:if test="${visit.status == 'C'}">selected</c:if> >Completed</option>
                                <option value="D" <c:if test="${visit.status == 'D'}">selected</c:if> >Canceled</option>
                            </select>
                            <div class="invalid-feedback">
                                Please provide a status.
                            </div>
                        </div>
                    </div>

                    <c:if test="${visit.status == 'C'}">
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="startDate">Actual Date</label>
                            <input type="text"
                                   class="form-control"
                                   id="startDate"
                                   name="startDate"
                                   placeholder="mm/dd/yyyy"
                                   <c:if test="${visit.actualStartDate == null}">
                                       value="<fmt:formatDate value="${now}" pattern="MM/d/yyyy" />"
                                   </c:if>
                                    <c:if test="${visit.actualStartDate != null}">
                                        value="<tags:localDate date="${LocalDateTime.now}" pattern="MM/d/yyyy"/>"
                                    </c:if>

                                    <c:if test="${personType == null || personType == 'patient' }">
                                        disabled
                                    </c:if>

                                   required />
                            <div class="invalid-feedback">
                                Please enter a valid date.
                            </div>
                        </div>

                        <div class="col-md-3 mb-3">
                            <label for="startTime">Start Time</label>
                            <input type="time"
                                   class="form-control"
                                   id="startTime"
                                   name="startTime"
                                   placeholder=""
                                   value="13:30"
                                   <c:if test="${personType == null || personType == 'patient' }">
                                   disabled
                                   </c:if>
                            <%--size="4"--%>
                                   required />
                            <div class="invalid-feedback">
                                Please enter the starting time of visit.
                            </div>
                        </div>

                        <div class="col-md-3 mb-3">
                            <label for="endTime">End Time</label>
                            <input type="time"
                                   class="form-control"
                                   id="endTime"
                                   name="endTime"
                                   placeholder=""
                                   value="13:30"
                                   <c:if test="${personType == null || personType == 'patient' }">
                                   disabled
                                   </c:if>

                            <%--size="4"--%>
                                   required />
                            <div class="invalid-feedback">
                                Please enter the ending time of visit.
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div <c:if test="${visit.status == 'C'}">class="col-md-6 mb-3"</c:if><c:if test="${visit.status != 'C'}">class="col-md-12 mb-3"</c:if>>
                            <label for="symptom">Symptom</label>
                            <select class="custom-select d-block w-100"
                                    id="symptom"
                                    name="symptom"
                                    <c:if test="${personType == null || personType == 'patient' }">
                                    disabled
                                    </c:if>
                                    required />
                            <option value="">Choose...</option>

                            <c:forEach var="symptom" items="${symptoms}">
                                <option value="${symptom.ID}" <c:if test="${visit.symptomId == symptom.ID}">selected</c:if> >${symptom.name}</option>
                            </c:forEach>

                            </select>
                            <div class="invalid-feedback">
                                Please provide a valid symptom.
                            </div>
                        </div>

                        <c:if test="${visit.status == 'C'}">
                        <div class="col-md-6 mb-3">
                            <label for="diagnosis">Diagnosis</label>

                            <select class="custom-select d-block w-100"
                                    id="diagnosis"
                                    name="diagnosis"
                                    <c:if test="${personType == null || personType == 'patient' }">
                                    disabled
                                    </c:if>
                                    required />
                                <option value="">Choose...</option>

                            <c:forEach var="issue" items="${issues}">
                                <option value="${issue.ID}"
                                        <c:if test="${visit.diagnosticId == issue.ID}">selected</c:if> >
                                        ${issue.name}</option>
                            </c:forEach>

                            </select>
                            <div class="invalid-feedback">
                                Please provide a valid diagnostic.
                            </div>
                        </div>
                        </c:if>
                    </div>

                    <div class="row">
                        <div class="col-md-2 mb-3">
                            <label for="weight">Weight</label>
                            <input type="text"
                                   class="form-control"
                                   id="weight"
                                   name="weight"
                                   placeholder=""
                                   value="${visit.patientWeight}"
                                   <c:if test="${personType == null || personType == 'patient' }">
                                   disabled
                                   </c:if>
                                   required />
                            <div class="invalid-feedback">
                                Enter patient weight.
                            </div>
                        </div>
                        <div class="col-md-2 mb-3">
                            <label for="height">Height</label>
                            <input type="text"
                                   class="form-control"
                                   id="height"
                                   name="height"
                                   placeholder=""
                                   value="${visit.patientHeight}"
                                    <c:if test="${personType == null || personType == 'patient' }">
                                        disabled
                                    </c:if>
                                   required />
                            <div class="invalid-feedback">
                                Enter patient height.
                            </div>
                        </div>
                        <div class="col-md-2 mb-3">
                            <label for="pulse">Pulse</label>
                            <input type="text"
                                   class="form-control"
                                   id="pulse"
                                   name="pulse"
                                   placeholder=""
                                   value="${visit.patientPulse}"
                                    <c:if test="${personType == null || personType == 'patient' }">
                                        disabled
                                    </c:if>
                                   required />
                            <div class="invalid-feedback">
                                Enter patient pulse.
                            </div>
                        </div>

                        <div class="col-md-2 mb-3">
                            <label for="respiration">Respiration</label>
                            <input type="text"
                                   class="form-control"
                                   id="respiration"
                                   name="respiration"
                                   placeholder=""
                                   value="${visit.patientRespiration}"
                                    <c:if test="${personType == null || personType == 'patient' }">
                                        disabled
                                    </c:if>
                                   required />
                            <div class="invalid-feedback">
                                Enter patient respiration.
                            </div>
                        </div>
                        <div class="col-md-2 mb-3">
                            <label for="bmi">BMI</label>
                            <input type="text"
                                   class="form-control"
                                   id="bmi"
                                   name="bmi"
                                   placeholder=""
                                   value="${visit.patientBMI}"
                                    <c:if test="${personType == null || personType == 'patient' }">
                                        disabled
                                    </c:if>
                                   required />
                            <div class="invalid-feedback">
                                Enter patient BMI.
                            </div>
                        </div>
                        <div class="col-md-2 mb-3">
                            <label for="temperature">Temperature</label>
                            <input type="text"
                                   class="form-control"
                                   id="temperature"
                                   name="temperature"
                                   placeholder=""
                                   value="${visit.patientTemperature}"
                                    <c:if test="${personType == null || personType == 'patient' }">
                                        disabled
                                    </c:if>
                                   required />
                            <div class="invalid-feedback">
                                Enter patient height.
                            </div>
                        </div>
                    </div>
                    </c:if>

                    <div class="mb-3">
                        <label for="providerComment">Provider Comments</label>
                        <textarea class="form-control"
                                  id="providerComment"
                                  name="providerComment"
                                <c:if test="${personType == null || personType == 'patient' }">
                                    disabled
                                </c:if>
                                  required >${visit.providerComments}</textarea>
                        <div class="invalid-feedback">
                            Please enter your comments.
                        </div>
                    </div>
                    <hr class="mb-4">
                    <c:if test="${personType == 'provider' }">
                        <button class="btn btn-success btn-lg btn-block" type="submit">Save</button>
                    </c:if>
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

    <!-- Reference to local JS library -->
    <script type="text/javascript" charset="utf-8" src="../style/js/visit.js" ></script>

</body>
</html>
