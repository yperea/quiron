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
            <h1 class="h2">My Treatments</h1>

            <div class="btn-toolbar mb-2 mb-md-0">
                <div class="btn-group mr-2">
                    <a class="btn btn-sm btn-outline-success" href="${root}/care/treatments" role="button">My Treatments</a>
                    &nbsp;
                </div>
            </div>
        </div>

        <br/>

        <div class="row justify-content-center">
            <div class="col-md-3 order-md-2 mb-4">

                <h4 class="d-flex justify-content-between align-items-center mb-3">
                    <span class="text-muted">Visit Information</span>
                </h4>


                <div class="alert alert-success" role="alert">
<%--
                    <h4 class="alert-heading">Visit Information</h4>
--%>
                    <h6 class="card-title">The Service</h6>
                    <p class="card-text">
                        <b>Symptoms</b>: ${treatment.visit.symptomName}
                        <br/>
                        <b>Symptoms</b>: ${treatment.visit.symptomName}
                    </p>
                    <p class="card-text">
                        <b>Diagnosis</b>: ${treatment.visit.diagnosticName}
                    </p>
                    <p class="card-text">
                        <b>Provider</b>: ${treatment.visit.providerSchedule.provider.lastName} , ${treatment.visit.providerSchedule.provider.firstName}
                    </p>
                    <p class="card-text">
                        <b>Organization</b>: ${treatment.visit.providerSchedule.organization.name}
                    </p>
                    <hr>
                    <p class="mb-0">Whenever you need to, be sure to use margin utilities to keep things nice and tidy.</p>
                </div>

<%--
                <div id="serviceCard" class="card border-info mb-3">
                    <div class="card-header"><h6>Service Information</h6></div>
                    <div id="serviceCardBody" class="card-body text-info">
                        <h6 class="card-title">The Service</h6>
                        <p class="card-text">
                            <b>Symptoms</b>: ${treatment.visit.symptomName}
                        </p>
                        <p class="card-text">
                            <b>Diagnosis</b>: ${treatment.visit.diagnosticName}
                        </p>
                        <p class="card-text">
                            <b>Provider</b>: ${treatment.visit.providerSchedule.provider.lastName} , ${treatment.visit.providerSchedule.provider.firstName}
                        </p>
                        <p class="card-text">
                            <b>Organization</b>: ${treatment.visit.providerSchedule.organization.name}
                        </p>
                    </div>
                </div>
--%>
<%--
                <h4 class="d-flex justify-content-between align-items-center mb-3">
                    <span class="text-muted">Measurements</span>
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
                                   value="${treatment.visit.patientBloodPressure}"
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
                                   value="${treatment.visit.patientWeight}"
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
                                   value="${treatment.visit.patientHeight}"
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
                                   value="${treatment.visit.patientPulse}"
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
                                   value="${treatment.visit.patientRespiration}"
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
                                   value="${treatment.visit.patientBMI}"
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
                                   value="${treatment.visit.patientTemperature}"
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
                <h4 class="mb-3">Treatment No. ${treatment.id}</h4>

                <yp:alert type="${message.type}" url="${message.redirect}">${message.description}</yp:alert>
                <c:remove var="message" scope="session" />
                <c:set var="now" value="<%= new java.util.Date()%>" />
                <%--<c:set var="personType" value="provider" />--%>

                <form class="needs-validation"
                      id="frmTreatment"
                      action="${root}/care/treatment"
                      method="POST"
                      novalidate>

                    <input type="hidden" id="treatmentId" name="treatmentId"
                           value="${treatment.id}"/>

                    <input type="hidden" id="visitId" name="visitId"
                           value="${treatment.visit.id}"/>

                    <input type="hidden" id="diagnosticId" name="diagnosticId"
                           value="${treatment.visit.diagnosticId}"/>

                    <input type="hidden" id="diagnosticName" name="diagnosticName"
                           value="${treatment.visit.diagnosticName}"/>

                    <input type="hidden" id="symptomId" name="symptomId"
                           value="${treatment.visit.symptomId}"/>

                    <input type="hidden" id="symptomName" name="symptomName"
                           value="${treatment.visit.symptomName}"/>

                    <input type="hidden" id="gender" name="gender"
                           value="${treatment.visit.patient.gender}"/>

                    <input type="hidden" id="birthYear" name="birthYear"
                           value="<tags:localDate date="${treatment.visit.patient.birthDate}" pattern="yyyy"/>" />

                    <input type="hidden" id="treatmentStartDate" name="treatmentStartDate"
                           value="<tags:localDate date="${treatment.startDate}" pattern="MM/d/yyyy"/>"/>

                    <input type="hidden" id="treatmentEndDate" name="treatmentEndDate"
                           value="<tags:localDate date="${treatment.endDate}" pattern="MM/d/yyyy"/>"/>

                    <input type="hidden" id="treatmentInstructions" name="treatmentInstructions"
                           value="${prescription.instructions}"/>

                    <input type="hidden" id="medicationId" name="medicationId"
                           value="${prescription.medication.id}"/>


                    <div class="row">
                        <div class="col-md-12 mb-3">
                            <label for="medication">Medication</label>
                            <input type="text"
                                   class="form-control"
                                   id="medication"
                                   name="medication"
                                   placeholder=""
                                   value="${prescription.medication.name}"
                                   readonly
                                   required />
                            <div class="invalid-feedback">
                                Medication required.
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-12 mb-3">
                            <label for="instructions">Instructions</label>
                            <input type="text"
                                   class="form-control"
                                   id="instructions"
                                   name="instructions"
                                   placeholder=""
                                   value="${prescription.instructions}"
                                   readonly
                                   required />
                            <div class="invalid-feedback">
                                Medication required.
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="startDate">Start Date</label>
                            <input type="text"
                                   class="form-control date-picker"
                                   id="startDate"
                                   name="startDate"
                                   placeholder="mm/dd/yyyy"
                                   value="<tags:localDate date="${treatment.startDate}" pattern="MM/d/yyyy"/>"
                                   readonly
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
                                   value="<tags:localDate date="${treatment.endDate}" pattern="MM/d/yyyy"/>"
                                   readonly
                                   required />
                            <div class="invalid-feedback">
                                Please enter a valid date.
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="statusCode">Completed</label>
                                <select class="custom-select d-block w-100"
                                        id="statusCode"
                                        name="statusCode"
                                        required />
                                <option value="">Choose...</option>
                                <option value="C" <c:if test="${visit.status != 'A'}">selected</c:if>>Yes</option>
                                <option value="A" <c:if test="${visit.status == 'A'}">selected</c:if>>No</option>
                                </select>
                            <div class="invalid-feedback">
                                Please select if the treatment was completed or not.
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="evaluation">Evaluation</label>
                            <input type="number"
                                   step="1"
                                   min="1"
                                   max="5"
                                   class="form-control"
                                   id="evaluation"
                                   name="evaluation"
                                   placeholder=""
                                   value="${treatment.evaluation}"
                                    <c:if test="${personType == null || personType == 'provider' }">
                                        readonly
                                    </c:if>
                                   required />
                            <div class="invalid-feedback">
                                Enter patient weight.
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
                                    readonly
                                </c:if>
                                  required >${treatment.providerComments}</textarea>
                        <div class="invalid-feedback">
                            Please enter your comments.
                        </div>
                    </div>

                    <div class="mb-3">
                        <label for="patientComment">Patient Comments</label>
                        <textarea class="form-control"
                                  id="patientComment"
                                  name="patientComment"
                                  size="512"
                                <c:if test="${personType == null || personType == 'provider' }">
                                    readonly
                                </c:if>
                                  required >${treatment.patientComments}</textarea>
                        <div class="invalid-feedback">
                            Please enter your comments.
                        </div>
                    </div>

                    <hr class="mb-4">
                    <button class="btn btn-success btn-lg btn-block" type="submit">Save</button>
                </form>
            </div>
        </div>
    </main>

    <%@include file="../shared/cdn-jss.jsp"%>
    <%@include file="../shared/footer.jsp"%>

    <!-- Reference to local JS library -->
    <script type="text/javascript" charset="utf-8" src="${root}/style/js/visit.js" ></script>

</body>
</html>
