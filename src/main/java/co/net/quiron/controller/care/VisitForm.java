package co.net.quiron.controller.care;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.care.MedicationManager;
import co.net.quiron.application.care.TreatmentManager;
import co.net.quiron.application.care.VisitManager;
import co.net.quiron.application.vendor.ApiMedicManager;
import co.net.quiron.domain.care.Medication;
import co.net.quiron.domain.care.Prescription;
import co.net.quiron.domain.care.Treatment;
import co.net.quiron.domain.care.Visit;
import co.net.quiron.util.FormManager;
import co.net.quiron.vendor.com.apimedic.Issue;
import co.net.quiron.vendor.com.apimedic.Symptom;

import javax.ejb.Local;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Visit form.
 */
@WebServlet(
        name = "visit",
        urlPatterns = {"/care/visit", "/visit"}
)
public class VisitForm extends HttpServlet {
    @Override
    public void doGet (HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String url = "/care/visit.jsp";
        String title = "My Visits";
        Prescription prescription = null;
        AccountManager accountManager = AccountManager.getAccountManager(session, request);
        VisitManager visitManager = new VisitManager(accountManager);
        int id = Integer.parseInt(FormManager.getNumericValue(request.getParameter("id")));
        Visit visit = visitManager.getPatientVisit(id);
        List<Symptom> symptoms = getSymptoms();
        List<Issue> issues = getIssues(visit);

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String actualStartTime = LocalDateTime.now().minusMinutes(30).format(timeFormatter);
        String actualEndTime = LocalDateTime.now().plusMinutes(30).format(timeFormatter);

        //TODO: Fix timezone when returning visit actual datetime. This is a temporary solution.
        if(visit.getActualStartDate() != null) {
            actualStartTime = visit.getActualStartDate().plusHours(6).format(timeFormatter);
            actualEndTime = visit.getActualEndDate().plusHours(6).format(timeFormatter);
        }

        List<Medication> medications = new MedicationManager().getMedications();
        Treatment treatment = visit.getTreatments().stream().findFirst().orElse(null);
        if (treatment != null) {
            prescription = treatment.getPrescriptions().stream().findFirst().orElse(null);
        }

        request.setAttribute("title", title);
        request.setAttribute("id", id);
        request.setAttribute("visit", visit);
        request.setAttribute("treatment", treatment);
        request.setAttribute("prescription", prescription);
        request.setAttribute("startTime", actualStartTime);
        request.setAttribute("endTime", actualEndTime);
        request.setAttribute("symptoms", symptoms);
        request.setAttribute("issues", issues);
        request.setAttribute("medications", medications);

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        String url = "/quiron/care/visit";
        String title = "My Visit";
        String personType = (String) session.getAttribute("personType");

        AccountManager accountManager = AccountManager.getAccountManager(session, request);
        VisitManager visitManager = new VisitManager(accountManager);

        boolean visitSaved = false;
        boolean treatmentAdded = true;
        Prescription prescription = null;
        int visitId = Integer.parseInt(FormManager.getNumericValue(request.getParameter("visitId")));
        Visit visit = visitManager.getPatientVisit(visitId);

        String visitStartDate = FormManager.getValue(request.getParameter("visitStartDate"));
        String visitStartTime = FormManager.getValue(request.getParameter("startTime"));
        String visitEndTime = FormManager.getValue(request.getParameter("endTime"));
        int symptomId = Integer.parseInt(FormManager.getNumericValue(request.getParameter("symptom")));
        String symptomName = FormManager.getValue(request.getParameter("symptomName"));
        int diagnosisId = Integer.parseInt(FormManager.getNumericValue(request.getParameter("diagnosis")));
        String diagnosisName = FormManager.getValue(request.getParameter("diagnosticName"));
        String statusCode = FormManager.getValue(request.getParameter("statusCode"));
        double weight = Double.parseDouble(FormManager.getNumericValue(request.getParameter("weight")));
        double height = Double.parseDouble(FormManager.getNumericValue(request.getParameter("height")));
        double pulse = Double.parseDouble(FormManager.getNumericValue(request.getParameter("pulse")));
        double respiration = Double.parseDouble(FormManager.getNumericValue(request.getParameter("respiration")));
        double bmi = Double.parseDouble(FormManager.getNumericValue(request.getParameter("bmi")));
        double temperature = Double.parseDouble(FormManager.getNumericValue(request.getParameter("temperature")));
        String providerComments = FormManager.getValue(request.getParameter("providerComment"));
        String actualStartDate = visitStartDate + " " + visitStartTime;
        String actualEndDate = visitStartDate + " " + visitEndTime;

        visit.setStatus(statusCode);
        visit.setSymptomId(symptomId);
        visit.setSymptomName(symptomName);
        visit.setDiagnosticId(diagnosisId);
        visit.setDiagnosticName(diagnosisName);
        visit.setPatientWeight(weight);
        visit.setPatientHeight(height);
        visit.setPatientPulse(pulse);
        visit.setPatientRespiration(respiration);
        visit.setPatientBMI(bmi);
        visit.setPatientTemperature(temperature);
        visit.setProviderComments(providerComments);

        if(!visitStartTime.equals("")) {
            visit.setActualStartDate(LocalDateTime.parse(actualStartDate,
                    DateTimeFormatter.ofPattern("MM/d/yyyy HH:mm")));
        }
        if(!visitEndTime.equals("")) {
            visit.setActualEndDate(LocalDateTime.parse(actualEndDate,
                    DateTimeFormatter.ofPattern("MM/d/yyyy HH:mm")));
        }

        url += "?id=" + visitId;

        if (FormManager.validForm(request, getVisitRequiredFields(personType))) {
            visitSaved = visitManager.saveVisit(visit);
            session.setAttribute("message", visitManager.getMessage());
            if (visitSaved && personType == "provider") {

                int medicationId = Integer.parseInt(FormManager.getNumericValue(request.getParameter("medicationId")));
                String prescriptionStartDate = FormManager.getValue(request.getParameter("treatmentStartDate"));
                String prescriptionEndDate = FormManager.getValue(request.getParameter("treatmentEndDate"));
                String treatmentInstructions = FormManager.getValue(request.getParameter("treatmentInstructions"));

                if (FormManager.validForm(request, getPrescriptionRequiredFields(personType))) {
                    LocalDate treatmentStartDate = null;
                    LocalDate treatmentEndDate = null;
                    if(!prescriptionStartDate.equals("")) {
                        treatmentStartDate = LocalDate.parse(prescriptionStartDate,
                                DateTimeFormatter.ofPattern("MM/d/yyyy"));
                    }
                    if(!prescriptionEndDate.equals("")) {
                        treatmentEndDate = LocalDate.parse(prescriptionEndDate,
                                DateTimeFormatter.ofPattern("MM/d/yyyy"));
                    }
                    treatmentAdded = visitManager.addTreatment(visitId, medicationId, treatmentStartDate, treatmentEndDate, treatmentInstructions);
                    session.setAttribute("message", visitManager.getMessage());
                }
            }
        }

        if (visitSaved & treatmentAdded) {
            response.sendRedirect(url);
        } else {
            url = "/care/visit.jsp?id=" + visitId;

            List<Symptom> symptoms = getSymptoms();
            List<Issue> issues = getIssues(visit);
            List<Medication> medications = new MedicationManager().getMedications();
            Treatment treatment = visit.getTreatments().stream().findFirst().orElse(null);
            if (treatment != null) {
                prescription = treatment.getPrescriptions().stream().findFirst().orElse(null);
            }

            request.setAttribute("title", title);
            request.setAttribute("visit", visit);
            request.setAttribute("prescription", prescription);
            request.setAttribute("startTime", visitStartTime);
            request.setAttribute("endTime", visitEndTime);
            request.setAttribute("symptoms", symptoms);
            request.setAttribute("issues", issues);
            request.setAttribute("medications", medications);

            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }

    }

    /**
     * Returns a list with the required Visit form fields to validate.
     * @param personType the type of person.
     *
     * @return List of required fields.
     */
    private List<String> getVisitRequiredFields(String personType){

        List<String> requiredFields = new ArrayList<>();
        if (personType == "provider") {
            requiredFields.add("visitStartDate");
            requiredFields.add("startTime");
            requiredFields.add("symptom");
            requiredFields.add("statusCode");
            requiredFields.add("weight");
            requiredFields.add("height");
            requiredFields.add("pulse");
            requiredFields.add("respiration");
            requiredFields.add("bmi");
            requiredFields.add("temperature");
            requiredFields.add("providerComment");
        } else {
            requiredFields.add("symptom");
        }

        return requiredFields;
    }

    /**
     * Returns a list with the required Prescription form fields to validate.
     * @param personType the type of person.
     *
     * @return List of required fields.
     */
    private List<String> getPrescriptionRequiredFields(String personType){

        List<String> requiredFields = new ArrayList<>();
        if (personType == "provider") {
            requiredFields.add("medicationId");
            requiredFields.add("treatmentStartDate");
            requiredFields.add("treatmentEndDate");
            requiredFields.add("treatmentInstructions");
        }

        return requiredFields;
    }

    /**
     * Returns a list of possible issues for the patient and symtoms.
     * @param visit the visit.
     *
     * @return List of issues.
     */
    private List<Issue> getIssues(Visit visit) {
        ApiMedicManager apiMedicManager = new ApiMedicManager("/apimedic.properties");
        List<Issue> issues = null;
        if(visit.getSymptomId() != 0 && visit.getPatient().getGender() != null
                && visit.getPatient().getBirthDate() != null){
            issues = apiMedicManager.getIssuesListBySymptom(visit.getSymptomId(),
                    visit.getPatient().getGender(),
                    visit.getPatient().getBirthDate().getYear());
        }
        return issues;
    }

    /**
     * Returns a list of the possible symptoms for the patient.
     *
     * @return List of symptoms.
     */
    private List<Symptom> getSymptoms() {
        ApiMedicManager apiMedicManager = new ApiMedicManager("/apimedic.properties");
        return apiMedicManager.getSymptomsList();
    }
}
