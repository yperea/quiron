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
                    <a class="btn btn-sm btn-outline-success" href="${root}/care/treatments" role="button">Back to My Treatments</a>
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

                <ul class="list-group mb-3">
                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <div class="text-success">
                            <h6 class="my-0">Service</h6>
                            <h5 class="text-muted">${treatment.visit.service.name}</h5>
                            <h6 class="my-0">Date</h6>
                            <h5 class="text-muted"><tags:localDateTime date="${treatment.visit.actualStartDate}" pattern="MM/d/yyyy"/></h5>
                        </div>
                    </li>

                    <c:if test="${account.profile.personType == 'patient'}">
                        <li class="list-group-item d-flex justify-content-between lh-condensed">
                            <div class="text-success">
                                <h6 class="my-0">Provider</h6>
                                <h5 class="text-muted">${treatment.visit.providerSchedule.provider.title} ${treatment.visit.providerSchedule.provider.firstName} ${treatment.visit.providerSchedule.provider.lastName}</h5>
                                <h6 class="my-0">Organization</h6>
                                <h5 class="text-muted">${treatment.visit.providerSchedule.organization.name}</h5>
                            </div>
                        </li>
                    </c:if>
                    <c:if test="${account.profile.personType == 'provider'}">
                        <li class="list-group-item d-flex justify-content-between lh-condensed">
                            <div class="text-success">
                                <h6 class="my-0">Patient</h6>
                                <h5 class="text-muted">${treatment.visit.patient.title} ${treatment.visit.patient.firstName} ${treatment.visit.patient.lastName}</h5>
                                <h6 class="my-0">Health Insurance</h6>
                                <h5 class="text-muted">${treatment.visit.patient.organization.name}</h5>
                            </div>
                        </li>
                    </c:if>

                    <li class="list-group-item d-flex justify-content-between lh-condensed">
                        <div class="text-success">
                            <h6 class="my-0">Symptom</h6>
                            <h5 class="text-muted">${treatment.visit.symptomName}</h5>
                        </div>
                    </li>
                    <li class="list-group-item d-flex justify-content-between bg-light">
                        <div class="text-success">
                            <h6 class="my-0">Diagnosis</h6>
                            <h5 class="text-muted">${treatment.visit.diagnosticName}</h5>
                        </div>
                    </li>
                </ul>
            </div>

            <div class="col-md-8">
                <h4 class="mb-3">Treatment No. ${treatment.id}</h4>

                <yp:alert type="${message.type}" url="${message.redirect}">${message.description}</yp:alert>
                <c:remove var="message" scope="session" />
                <c:set var="now" value="<%= new java.util.Date()%>" />

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
                                   class="form-control"
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
                                   class="form-control"
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
                            <c:if test="${(account.profile.personType != 'patient')}">
                                <input type="hidden" id="statusCode" name="statusCode"
                                       value="${treatment.status}"/>
                                <input type="text"
                                       class="form-control"
                                       <c:if test="${treatment.status == 'A'}">value="No"</c:if>
                                       <c:if test="${treatment.status != 'A'}">value="Yes"</c:if>
                                       readonly
                                       required />
                            </c:if>
                            <c:if test="${account.profile.personType == 'patient'}">
                                <select class="custom-select d-block w-100"
                                        id="statusCode"
                                        name="statusCode"
                                        required />
                                <option value="">Choose...</option>
                                <option value="C" <c:if test="${treatment.status != 'A'}">selected</c:if>>Yes</option>
                                <option value="A" <c:if test="${treatment.status == 'A'}">selected</c:if>>No</option>
                                </select>
                            </c:if>

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
                                    <c:if test="${personType == 'provider' }">
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
                                <c:if test="${personType == 'provider' }">
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
