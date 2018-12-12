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

                <button class="btn btn-sm btn-outline-secondary dropdown-toggle"
                        id="create-prescription"
                        data-toggle="modal"
                        data-target="#dialog-form">
                    <span data-feather="calendar"></span>
                    Add Prescription
                </button>

            </div>

        </div>
        <br/>

        <div class="row justify-content-center">

            <div class="col-md-9">
                <!--<h4 class="mb-3">Your person</h4>-->

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

                    <input type="hidden" id="gender" name="gender"
                           value="${visit.patient.gender}"/>

                    <input type="hidden" id="birthYear" name="birthYear"
                           value="<tags:localDate date="${visit.patient.birthDate}" pattern="yyyy"/>" />

                    <input type="hidden" id="treatmentStartDate" name="treatmentStartDate"
                           value=""/>

                    <input type="hidden" id="treatmentEndDate" name="treatmentEndDate"
                           value=""/>

                    <input type="hidden" id="prescriptionInstructions" name="prescriptionInstructions"
                           value=""/>

                    <input type="hidden" id="medicationId" name="medicationId"
                           value=""/>


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
                                           value="<fmt:formatDate value="${now}" pattern="MM/d/yyyy" />"
<%--
                                            <c:if test="${visit.actualStartDate == null}">
                                                value="<fmt:formatDate value="${now}" pattern="MM/d/yyyy" />"
                                            </c:if>
                                            <c:if test="${visit.actualStartDate != null}">
                                                value="<tags:localDateTime date="${visit.actualStartDate}" pattern="MM/d/yyyy"/>"
                                            </c:if>

                                            <c:if test="${personType == null || personType == 'patient' }">
                                                disabled
                                            </c:if>
--%>

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
                                           value="<fmt:formatDate value="${now}" pattern="MM/d/yyyy" />"
                                    <%--
                                                                                <c:if test="${visit.actualStartDate == null}">
                                                                                    value="<fmt:formatDate value="${now}" pattern="MM/d/yyyy" />"
                                                                                </c:if>
                                                                                <c:if test="${visit.actualStartDate != null}">
                                                                                    value="<tags:localDateTime date="${visit.actualStartDate}" pattern="MM/d/yyyy"/>"
                                                                                </c:if>

                                                                                <c:if test="${personType == null || personType == 'patient' }">
                                                                                    disabled
                                                                                </c:if>
                                    --%>

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
                                    <c:forEach var="symptom" items="${symptoms}">
                                        <option value="${symptom.ID}" <c:if test="${visit.symptomId == symptom.ID}">selected</c:if> >${symptom.name}</option>
                                    </c:forEach>

                                    </select>
                                    <div class="invalid-feedback">
                                        Please provide a valid medication.
                                    </div>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="instructions">Instructions</label>
                                <textarea class="form-control"
                                          id="instructions"
                                          name="instructions"
                                          size="512"
                                          required >${visit.providerComments}</textarea>
                                <div class="invalid-feedback">
                                    Please enter prescription instructions.
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

        <%--<div class="modal fade" id="addPrescription" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog modal-md" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="myModalLabel">Add Contact</h4>
                    </div>
                    <form action="addcontact.php" method="POST">
                        <div class="modal-body">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-md-6 col-sm-6 col-xs-6">
                                        <div class="form-group">
                                            <label for="contactfname">First Name</label>
                                            <input type="text" class="form-control" id="contactfname" name="txt_fn" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="contactmname">Middle Name</label>
                                            <input type="text" class="form-control" id="contactmname" name="txt_mn" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="contactlname">Last Name</label>
                                            <input type="text" class="form-control" id="contactlname" name="txt_ln" required>
                                        </div>


                                    </div>
                                    <div class="col-md-6 col-sm-6 col-xs-6">
                                        <div class="form-group">
                                            <label for="contactea">Email Address</label>
                                            <input type="email" class="form-control" id="contactea" name="txt_ea" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="contactnum">Mobile Number</label>
                                            <input type="text" class="form-control" id="contactnum" name="txt_num" required>
                                        </div>
                                        <div class="form-group">
                                            <label for="contactgroup">Select Group</label>
                                            <select name="txt_group" class="form-control" id="contactgroup" required>
                                                <option value="">Choose group...</option>
                                                <option value="1">One</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                        <input type="submit" name="addgroupbtn" class="btn btn-primary" value="Save Contact">
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>--%>

        <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
            <h4 class="h4">Prescription</h4>
        </div>

        <div class="row justify-content-center">

            <div class="table-responsive col-md-9">
                <table class="table table-striped table-sm"
                       id="prescriptions">
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
    </main>

    <%@include file="../shared/cdn-jss.jsp"%>
    <%@include file="../shared/footer.jsp"%>

    <!-- Reference to local JS library -->
    <script type="text/javascript" charset="utf-8" src="../style/js/visit.js" ></script>

</body>
</html>
