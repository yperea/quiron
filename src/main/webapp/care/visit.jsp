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
            <h1 class="h2">Visit No. ${visitId}</h1>

            <div class="btn-toolbar mb-2 mb-md-0">
                <div class="btn-group mr-2">
                    <a class="btn btn-sm btn-outline-success" href="${root}/patient/visits" role="button">My Visits</a>
                    &nbsp;
                </div>
                <c:if test="${personType == 'provider' }">
                <button class="btn btn-sm btn-outline-secondary dropdown-toggle"
                        id="create-prescription"
                        data-toggle="modal"
                        data-target="#dialog-form">
                    <span data-feather="activity"></span>
                    Add Prescription
                </button>
                </c:if>
            </div>
        </div>

        <br/>

        <div class="row justify-content-center">
            <div class="col-md-3 order-md-2 mb-4">
                <c:if test="${prescription != null}">
                    <div id="importantCard" class="card border-info mb-3">
                        <div class="card-header"><h6>Important</h6></div>
                        <div id="importantCardBody" class="card-body text-info">
                            <h5 class="card-title">Prescription instructions</h5>
                            <p id="prescriptionDetails" class="card-text">
                                ${prescription.instructions} of ${prescription.medication.name} from ${treatment.startDate} to ${treatment.endDate}.
                            </p>
                        </div>
                    </div>
                </c:if>
<%--
                <h4 class="d-flex justify-content-between align-items-center mb-3">
                    <span class="text-muted">Measurements</span>
                    <span class="badge badge-secondary badge-pill">3</span>
                </h4>
                <ul class="list-group mb-3">
                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <div>
                            <h6 class="my-0"><label for="bloodPressure">Blood Pressure</label></h6>
                            <small class="text-muted">Brief description</small>
                        </div>
                        <span class="text-muted">
                            <input type="text"
                                   class="form-control"
                                   id="bloodPressure"
                                   name="bloodPressure"
                                   placeholder=""
                                   size="4"
                                   value="${visit.patientBloodPressure}"
                                   required />
                            <div class="invalid-feedback">
                                Blood pressure required.
                            </div>
                        </span>
                    </li>
                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <div>
                            <h6 class="my-0"><label for="weight">Weight</label></h6>
                            <small class="text-muted">Brief description</small>
                        </div>
                        <span class="text-muted">
                            <input type="text"
                                   class="form-control"
                                   id="weight"
                                   name="weight"
                                   placeholder=""
                                   size="4"
                                   value="${visit.patientWeight}"
                                   required />
                            <div class="invalid-feedback">
                                Enter patient weight.
                            </div>
                        </span>
                    </li>

                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <div>
                            <h6 class="my-0"><label for="height">Height</label></h6>
                            <small class="text-muted">Brief description</small>
                        </div>
                        <span class="text-muted">
                            <input type="text"
                                   class="form-control"
                                   id="height"
                                   name="height"
                                   placeholder=""
                                   size="4"
                                   value="${visit.patientHeight}"
                                   required />
                            <div class="invalid-feedback">
                                Enter patient height.
                            </div>
                        </span>
                    </li>

                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <div>
                            <h6 class="my-0"><label for="pulse">Pulse</label></h6>
                            <small class="text-muted">Brief description</small>
                        </div>
                        <span class="text-muted">
                            <input type="text"
                                   class="form-control"
                                   id="pulse"
                                   name="pulse"
                                   placeholder=""
                                   size="4"
                                   value="${visit.patientPulse}"
                                   required />
                            <div class="invalid-feedback">
                                Enter patient pulse.
                            </div>
                        </span>
                    </li>

                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <div>
                            <h6 class="my-0"><label for="respiration">Respiration</label></h6>
                            <small class="text-muted">Brief description</small>
                        </div>
                        <span class="text-muted">
                            <input type="text"
                                   class="form-control"
                                   id="respiration"
                                   name="respiration"
                                   placeholder=""
                                   size="4"
                                   value="${visit.patientRespiration}"
                                   required />
                            <div class="invalid-feedback">
                                Enter patient respiration.
                            </div>
                        </span>
                    </li>

                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <div>
                            <h6 class="my-0"><label for="bmi">BMI</label></h6>
                            <small class="text-muted">Brief description</small>
                        </div>
                        <span class="text-muted">
                            <input type="text"
                                   class="form-control"
                                   id="bmi"
                                   name="bmi"
                                   placeholder=""
                                   size="4"
                                   value="${visit.patientBMI}"
                                   required />
                            <div class="invalid-feedback">
                                Enter patient BMI.
                            </div>
                        </span>
                    </li>

                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <div>
                            <h6 class="my-0"><label for="temperature">Temperature</label></h6>
                            <small class="text-muted">Brief description</small>
                        </div>
                        <span class="text-muted">
                            <input type="text"
                                   class="form-control"
                                   id="temperature"
                                   name="temperature"
                                   placeholder=""
                                   size="4"
                                   value="${visit.patientTemperature}"
                                   required />
                            <div class="invalid-feedback">
                                Enter patient height.
                            </div>
                        </span>
                    </li>
                </ul>
--%>
            </div>


            <div class="col-md-8">
                <%--<h4 class="mb-3">Visit Information</h4>--%>

                <yp:alert type="${message.type}" url="${message.redirect}">${message.description}</yp:alert>
                <c:remove var="message" scope="session" />
                <c:set var="now" value="<%= new java.util.Date()%>" />
                <%--<c:set var="personType" value="provider" />--%>

                <form class="needs-validation"
                      id="frmVisit"
                      action="${root}/patient/visit"
                      method="POST"
                      novalidate>

                    <input type="hidden" id="visitId" name="visitId"
                           value="${visitId}"/>

                    <input type="hidden" id="diagnosticId" name="diagnosticId"
                           value="${visit.diagnosticId}"/>

                    <input type="hidden" id="diagnosticName" name="diagnosticName"
                           value="${visit.diagnosticName}"/>

                    <input type="hidden" id="symptomId" name="symptomId"
                           value="${visit.symptomId}"/>

                    <input type="hidden" id="symptomName" name="symptomName"
                           value="${visit.symptomName}"/>

                    <input type="hidden" id="gender" name="gender"
                           value="${visit.patient.gender}"/>

                    <input type="hidden" id="birthYear" name="birthYear"
                           value="<tags:localDate date="${visit.patient.birthDate}" pattern="yyyy"/>" />

                    <input type="hidden" id="treatmentStartDate" name="treatmentStartDate"
                           value="<tags:localDate date="${treatment.startDate}" pattern="MM/d/yyyy"/>"/>

                    <input type="hidden" id="treatmentEndDate" name="treatmentEndDate"
                           value="<tags:localDate date="${treatment.endDate}" pattern="MM/d/yyyy"/>"/>

                    <input type="hidden" id="treatmentInstructions" name="treatmentInstructions"
                           value="${prescription.instructions}"/>

                    <input type="hidden" id="medicationId" name="medicationId"
                           value="${prescription.medication.id}"/>


                    <c:if test="${account.profile.personType == 'patient'}">
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
                    </c:if>
                    <c:if test="${account.profile.personType == 'provider'}">
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="insuranceCompany">Insurance Company</label>
                                <input type="text"
                                       class="form-control"
                                       id="insuranceCompany"
                                       name="insuranceCompany"
                                       placeholder=""
                                       value="${visit.patient.organization.name}"
                                       disabled
                                       required />
                                <div class="invalid-feedback">
                                    Insurance Company required.
                                </div>
                            </div>
                            <div class="col-md-3 mb-3">
                                <label for="subscriber">Subscriber Id</label>
                                <input type="text"
                                       class="form-control"
                                       id="subscriber"
                                       name="subscriber"
                                       placeholder=""
                                       value="${visit.patient.subscriberCode}"
                                       disabled
                                       required />
                                <div class="invalid-feedback">
                                    Subscriber Id required.
                                </div>
                            </div>
                            <div class="col-md-3 mb-3">
                                <label for="patient">Patient</label>
                                <input type="text"
                                       class="form-control"
                                       id="patient"
                                       name="patient"
                                       placeholder=""
                                       value="${visit.patient.lastName} , ${visit.patient.firstName}"
                                       disabled
                                       required />
                                <div class="invalid-feedback">
                                    Patient required.
                                </div>
                            </div>
                        </div>
                    </c:if>



                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="scheduledStartDate">Scheduled Date</label>
                            <input type="text"
                                   class="form-control date-picker"
                                   id="scheduledStartDate"
                                   name="scheduledStartDate"
                                   placeholder="mm/dd/yyyy"
                                   value="<tags:localDateTime date="${visit.scheduledStartDate}" pattern="MM/d/yyyy"/>"
                                   disabled
                                   required />
                            <div class="invalid-feedback">
                                Please enter a valid date.
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="status">Status</label>
                            <c:if test="${account.profile.personType == 'patient'}">
                                <input type="hidden" id="statusCode" name="statusCode"
                                       value="${visit.status}"/>
                            <input type="text"
                                   class="form-control"
                                   id="status"
                                   name="status"
                                   placeholder=""
                                   <c:if test="${visit.status == 'A'}">value="Active"</c:if>
                                   <c:if test="${visit.status == 'C'}">value="Completed"</c:if>
                                   <c:if test="${visit.status == 'D'}">value="Canceled"</c:if>
                                   readonly
                                   required />
                            </c:if>
                            <c:if test="${account.profile.personType == 'provider'}">
                            <select class="custom-select d-block w-100"
                                    id="statusCode"
                                    name="statusCode"
                                    required />
                                <option value="">Choose...</option>
                                <option value="A" <c:if test="${visit.status == 'A'}">selected</c:if>>Active</option>
                                <option value="C" <c:if test="${visit.status == 'C'}">selected</c:if>>Completed</option>
                                <option value="D" <c:if test="${visit.status == 'D'}">selected</c:if>>Canceled</option>
                            </select>
                            </c:if>
                            <div class="invalid-feedback">
                                Please provide a status.
                            </div>
                        </div>
                    </div>

                    <c:if test="${(account.profile.personType == 'patient' && visit.status != 'A') || account.profile.personType == 'provider'}">
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="visitStartDate">Actual Date</label>
                            <input type="text"
                                   class="form-control date-picker"
                                   id="visitStartDate"
                                   name="visitStartDate"
                                   placeholder="mm/dd/yyyy"
                                   <c:if test="${visit.actualStartDate == null}">
                                       value="<fmt:formatDate value="${now}" pattern="MM/d/yyyy" />"
                                   </c:if>
                                    <c:if test="${visit.actualStartDate != null}">
                                        value="<tags:localDateTime date="${visit.actualStartDate}" pattern="MM/d/yyyy"/>"
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
                                   value="${startTime}"
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
                                   value="${endTime}"
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
                    </c:if>

                    <div class="row">
                        <div <c:if test="${account.profile.personType == 'provider'}">class="col-md-6 mb-3"</c:if><c:if test="${account.profile.personType == 'patient'}">class="col-md-12 mb-3"</c:if>>
                            <label for="symptom">Symptom</label>
                            <select class="custom-select d-block w-100"
                                    id="symptom"
                                    name="symptom"
                                    <c:if test="${account.profile.personType == 'patient' && visit.status != 'A'}">
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

                        <c:if test="${(visit.status == 'A' && personType == 'provider') || (visit.status == 'C')}">
                        <div class="col-md-6 mb-3">
                            <label for="diagnosis">Diagnosis</label>

                            <select class="custom-select d-block w-100"
                                    id="diagnosis"
                                    name="diagnosis"
                                    <c:if test="${personType == 'patient' }">
                                    disabled
                                    </c:if> />
                                <option value="0">Choose...</option>
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

                    <c:if test="${(account.profile.personType == 'patient' && visit.status != 'A') || account.profile.personType == 'provider'}">
                    <div class="row">
                        <div class="col-md-2 mb-3">
                            <label for="weight">Weight</label>
                            <input type="number"
                                   step="0.01"
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
                            <input type="number"
                                   step="0.01"
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
                            <input type="number"
                                   step="0.01"
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
                            <input type="number"
                                   step="0.01"
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
                            <input type="number"
                                   step="0.01"
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
                            <input type="number"
                                   step="0.01"
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

                    <div class="mb-3">
                        <label for="providerComment">Provider Comments</label>
                        <textarea class="form-control"
                                  id="providerComment"
                                  name="providerComment"
                                  size="512"
                                <c:if test="${personType == null || personType == 'patient' }">
                                    disabled
                                </c:if>
                                  required >${visit.providerComments}</textarea>
                        <div class="invalid-feedback">
                            Please enter your comments.
                        </div>
                    </div>
                    </c:if>
                    <hr class="mb-4">
                    <button class="btn btn-success btn-lg btn-block" type="submit">Save</button>
                </form>
            </div>
        </div>
        <br/>

        <!-- modal form -->
        <!-- The Modal -->
        <div class="modal fade" id="dialog-form">
            <div class="modal-dialog">
                <div class="modal-content">

                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title">Prescription</h4>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                    </div>

                    <!-- Modal body -->
                    <div class="modal-body">

                        <form class="needs-validation"
                              id="frmPrescription"
                              action=""
                              method="POST"
                              novalidate>

                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="startDate">Start Date</label>
                                    <input type="text"
                                           class="form-control date-picker"
                                           id="startDate"
                                           name="startDate"
                                           placeholder="mm/dd/yyyy"
                                            <c:if test="${treatment.startDate == null}">
                                                value="<fmt:formatDate value="${now}" pattern="MM/d/yyyy" />"
                                            </c:if>
                                            <c:if test="${treatment.startDate  != null}">
                                                value="<tags:localDate date="${treatment.startDate}" pattern="MM/d/yyyy"/>"
                                            </c:if>
                                           required />
                                    <div class="invalid-feedback">
                                        Please enter a valid date.
                                    </div>
                                </div>

                                <div class="col-md-6 mb-3">
                                    <label for="endDate">EndDate</label>
                                    <input type="text"
                                           class="form-control date-picker"
                                           id="endDate"
                                           name="endDate"
                                           placeholder="mm/dd/yyyy"
                                            <c:if test="${treatment.endDate == null}">
                                                value="<fmt:formatDate value="${now}" pattern="MM/d/yyyy" />"
                                            </c:if>
                                            <c:if test="${treatment.endDate  != null}">
                                                value="<tags:localDate date="${treatment.endDate}" pattern="MM/d/yyyy"/>"
                                            </c:if>
                                           required />
                                    <div class="invalid-feedback">
                                        Please enter a valid date.
                                    </div>
                                </div>
                            </div>


                            <div class="row">
                                <div class="col-md-12 mb-3">
                                    <label for="medication">Medication</label>
                                    <select class="custom-select d-block w-100"
                                            id="medication"
                                            name="medication"
                                            required />
                                    <option value="">Choose...</option>
                                    <c:forEach var="medication" items="${medications}">
                                        <option value="${medication.id}" <c:if test="${prescription.medication.id == medication.id}">selected</c:if> >${medication.name}</option>
                                    </c:forEach>

                                    </select>
                                    <div class="invalid-feedback">
                                        Please provide a valid medication.
                                    </div>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="dosis">Dosis</label>
                                <textarea class="form-control"
                                          id="dosis"
                                          name="dosis"
                                          size="512"
                                          required >${prescription.instructions}</textarea>
                                <div class="invalid-feedback">
                                    Please enter prescription dosis.
                                </div>
                            </div>

                        </form>
                    </div>

                    <!-- Modal footer -->
                    <div class="modal-footer">
                        <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
                        <button type="submit" form="frmPrescription" class="btn btn-primary" id="btnAddPrescription" name="btnAddPrescription">Save Prescription</button>
                    </div>

                </div>
            </div>
        </div>

    </main>

    <%@include file="../shared/cdn-jss.jsp"%>
    <%@include file="../shared/footer.jsp"%>

    <!-- Reference to local JS library -->
    <script type="text/javascript" charset="utf-8" src="${root}/style/js/visit.js" ></script>

</body>
</html>
