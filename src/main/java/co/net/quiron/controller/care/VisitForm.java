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
import java.util.List;

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
        String personType = (String) session.getAttribute("personType");
        String username = request.getUserPrincipal().getName();

        request.setAttribute("title", title);
        request.setAttribute("visitId", request.getParameter("id"));

        AccountManager accountManager = (AccountManager) session.getAttribute("account");
        if (accountManager == null){
            accountManager = new AccountManager(username, personType);
            session.setAttribute("account", accountManager);
        }

        Visit visit = null;
        VisitManager visitManager = new VisitManager(accountManager);
        if ((request.getParameter("id") != null
            && !request.getParameter("id").isEmpty())){
            int visitId = Integer.parseInt(request.getParameter("id"));
            visit = visitManager.getPatientVisit(visitId);
        }

        ApiMedicManager apiMedicManager = new ApiMedicManager("/apimedic.properties");
        List<Symptom> symptoms = apiMedicManager.getSymptomsList();
        List<Issue> issues = null;

        if(visit.getSymptomId() != 0
                && visit.getPatient().getGender() != null
                && visit.getPatient().getBirthDate() != null){
            issues = apiMedicManager.getIssuesListBySymptom(visit.getSymptomId(),
                    visit.getPatient().getGender(),
                    visit.getPatient().getBirthDate().getYear());
        }

        //TODO: Fix timezone when returning visit actual datetime
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String actualStartTime = LocalDateTime.now().minusMinutes(30).format(timeFormatter);
        String actualEndTime = LocalDateTime.now().plusMinutes(30).format(timeFormatter);

        if(visit.getActualStartDate() != null) {
            actualStartTime = visit.getActualStartDate().format(timeFormatter);
            actualEndTime = visit.getActualEndDate().format(timeFormatter);
        }

        List<Medication> medications = new MedicationManager().getMedications();
        Prescription prescription = null;
        Treatment treatment = visit.getTreatments().stream().findFirst().orElse(null);
        if (treatment != null) {
            prescription = treatment.getPrescriptions().stream().findFirst().orElse(null);
        }

        session.setAttribute("visit", visit);
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
                          HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();

        String url = "/quiron/visits";
        String title = "My Visit";
        String personType = (String) session.getAttribute("personType");
        String username = request.getUserPrincipal().getName();
        boolean treatmentAdded = false;

        Visit visit = (Visit) session.getAttribute("visit");
        AccountManager accountManager = (AccountManager) session.getAttribute("account");
        if (accountManager == null){
            accountManager = new AccountManager(username, personType);
            session.setAttribute("account", accountManager);
        }

        VisitManager visitManager = new VisitManager(accountManager);

        if ((request.getParameter("visitId") != null && !request.getParameter("visitId").isEmpty() )
                &&
                (personType.equals("provider")
                && (request.getParameter("visitStartDate") != null && !request.getParameter("visitStartDate").isEmpty())
                && (request.getParameter("startTime") != null && !request.getParameter("startTime").isEmpty())
                && (request.getParameter("endTime") != null && !request.getParameter("endTime").isEmpty())
                && (request.getParameter("symptom") != null && !request.getParameter("symptom").isEmpty())
                && (request.getParameter("statusCode") != null && !request.getParameter("statusCode").isEmpty())
                && (request.getParameter("weight") != null && !request.getParameter("weight").isEmpty())
                && (request.getParameter("height") != null && !request.getParameter("height").isEmpty())
                && (request.getParameter("pulse") != null && !request.getParameter("pulse").isEmpty())
                && (request.getParameter("respiration") != null && !request.getParameter("respiration").isEmpty())
                && (request.getParameter("bmi") != null && !request.getParameter("bmi").isEmpty())
                && (request.getParameter("temperature") != null && !request.getParameter("temperature").isEmpty())
                && (request.getParameter("providerComment") != null && !request.getParameter("providerComment").isEmpty())
                    )
                ||
                (personType.equals("patient")
                        && (request.getParameter("symptom") != null && !request.getParameter("symptom").isEmpty())
                )
        ) {

            int diagosticId = 0;
            String diagnosisName = "";
            int symptomId = Integer.parseInt(request.getParameter("symptom"));
            String symptomName = request.getParameter("symptomName");

            if(request.getParameter("diagnosis") != null && !request.getParameter("diagnosis").isEmpty()) {
                diagosticId = Integer.parseInt(request.getParameter("diagnosis"));
                diagnosisName = request.getParameter("diagnosticName");
            }

            url = "/quiron/visit?id=" + request.getParameter("visitId");

            String actualStartDate = request.getParameter("visitStartDate") + " "
                                   + request.getParameter("startTime");
            String actualEndDate = request.getParameter("visitStartDate") + " "
                                 + request.getParameter("endTime");

            if(request.getParameter("startTime") != null) {
                visit.setActualStartDate(LocalDateTime.parse(actualStartDate,
                        DateTimeFormatter.ofPattern("MM/d/yyyy HH:mm")));
            }
            if(request.getParameter("endTime") != null) {
                visit.setActualEndDate(LocalDateTime.parse(actualEndDate,
                        DateTimeFormatter.ofPattern("MM/d/yyyy HH:mm")));
            }

            visit.setStatus(request.getParameter("statusCode"));
            visit.setSymptomId(symptomId);
            visit.setSymptomName(symptomName);
            visit.setDiagnosticId(diagosticId);
            visit.setDiagnosticName(diagnosisName);

            visit.setPatientWeight(Double.parseDouble(request.getParameter("weight")));
            visit.setPatientHeight(Double.parseDouble(request.getParameter("height")));
            visit.setPatientPulse(Double.parseDouble(request.getParameter("pulse")));
            visit.setPatientRespiration(Double.parseDouble(request.getParameter("respiration")));
            visit.setPatientBMI(Double.parseDouble(request.getParameter("bmi")));
            visit.setPatientTemperature(Double.parseDouble(request.getParameter("temperature")));
            visit.setProviderComments(request.getParameter("providerComment"));


            boolean visitSaved = visitManager.saveVisit(visit);

            if (visitSaved) {

                session.setAttribute("visit", visit);

                if ((request.getParameter("medicationId") != null && !request.getParameter("medicationId").isEmpty())
                        && (request.getParameter("treatmentStartDate") != null && !request.getParameter("treatmentStartDate").isEmpty())
                        && (request.getParameter("treatmentEndDate") != null && !request.getParameter("treatmentEndDate").isEmpty())
                        && (request.getParameter("treatmentInstructions") != null && !request.getParameter("treatmentInstructions").isEmpty())
                ) {
                    //TreatmentManager treatmentManager = new TreatmentManager();


                    int visitId = Integer.parseInt(request.getParameter("visitId"));
                    int medicationId = Integer.parseInt(request.getParameter("medicationId"));

                    LocalDate treatmentStartDate = LocalDate.parse(request.getParameter("treatmentStartDate"),
                            DateTimeFormatter.ofPattern("MM/d/yyyy"));

                    LocalDate treatmentEndDate = LocalDate.parse(request.getParameter("treatmentEndDate"),
                            DateTimeFormatter.ofPattern("MM/d/yyyy"));

                    String treatmentInstructions = request.getParameter("treatmentInstructions");

                    treatmentAdded = visitManager.addTreatment(visitId, medicationId, treatmentStartDate, treatmentEndDate, treatmentInstructions);

                    if(treatmentAdded) {
                        session.setAttribute("treatment", visitManager.getTreatment());    
                    }
                    
/*
                    session.setAttribute("treatmentStartDate", treatmentStartDate);
                    session.setAttribute("treatmentEndDate", treatmentEndDate);
                    session.setAttribute("prescriptionInstructions", prescriptionInstructions);
*/
                }
            }

            session.setAttribute("message", visitManager.getMessage());
            request.setAttribute("title", title);
        }
        response.sendRedirect(url);
    }
}
