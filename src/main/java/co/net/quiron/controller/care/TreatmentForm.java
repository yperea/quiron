package co.net.quiron.controller.care;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.care.TreatmentManager;
import co.net.quiron.domain.care.Prescription;
import co.net.quiron.domain.care.Treatment;
import co.net.quiron.util.FormManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        name = "treatement",
        urlPatterns = {"/care/treatment", "/treatment"}
)
public class TreatmentForm extends HttpServlet {
    @Override
    public void doGet (HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String url = "/care/treatment.jsp";
        String title = "My Treatments";
        String personType = (String) session.getAttribute("personType");
        String username = request.getUserPrincipal().getName();

        Prescription prescription = null;

        AccountManager accountManager = (AccountManager) session.getAttribute("account");
        if (accountManager == null){
            accountManager = new AccountManager(username, personType);
            session.setAttribute("account", accountManager);
        }

        TreatmentManager treatmentManager = new TreatmentManager(accountManager);
        int id = Integer.parseInt(FormManager.getNumericValue(request.getParameter("id")));

        Treatment treatment = treatmentManager.getPatientTreatment(id);
        if (treatment != null) {
            prescription = treatment.getPrescriptions().stream().findFirst().orElse(null);
        }
/*
        if ((request.getParameter("id") != null && !request.getParameter("id").isEmpty())){
            int treatmentId = Integer.parseInt(request.getParameter("id"));
            treatment = treatmentManager.getPatientTreatment(treatmentId);
            prescription = treatment.getPrescriptions().stream().findFirst().orElse(null);
        }
*/
        request.setAttribute("title", title);
        session.setAttribute("message", treatmentManager.getMessage());
        request.setAttribute("treatment", treatment);
        request.setAttribute("prescription", prescription);
        request.setAttribute("id", id);

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();

        String url = "/quiron/care/treatment";
        String title = "My Treatments";
        String personType = (String) session.getAttribute("personType");
        String username = request.getUserPrincipal().getName();

        AccountManager accountManager = (AccountManager) session.getAttribute("account");
        if (accountManager == null){
            accountManager = new AccountManager(username, personType);
            session.setAttribute("account", accountManager);
        }

        int treatmentId = Integer.parseInt(FormManager.getNumericValue(request.getParameter("treatmentId")));
        String statusCode = FormManager.getValue(request.getParameter("statusCode"));
        int evaluation = Integer.parseInt(FormManager.getNumericValue(request.getParameter("evaluation")));
        String providerComments = FormManager.getValue(request.getParameter("providerComment"));
        String patientComments = FormManager.getValue(request.getParameter("patientComment"));

        TreatmentManager treatmentManager = new TreatmentManager(accountManager);
        Treatment treatment = treatmentManager.getPatientTreatment(treatmentId);

        treatment.setId(treatmentId);
        treatment.setStatus(statusCode);
        treatment.setEvaluation(evaluation);
        treatment.setProviderComments(providerComments);
        treatment.setPatientComments(patientComments);

        url += "?id=" + treatmentId;

        if (FormManager.validForm(request, getRequiredFields())) {
            boolean result = treatmentManager.saveTreatment(treatment);
            session.setAttribute("message", treatmentManager.getMessage());
            response.sendRedirect(url);
        } else {
            request.setAttribute("title", title);
            request.setAttribute("treatment", treatment);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }

/*
        if ((request.getParameter("treatmentId") != null && !request.getParameter("treatmentId").isEmpty() )
                &&
                (personType.equals("provider")
                        && (request.getParameter("providerComment") != null && !request.getParameter("providerComment").isEmpty())
                )
                ||
                (personType.equals("patient")
                        && (request.getParameter("patientComment") != null && !request.getParameter("patientComment").isEmpty())
                        && (request.getParameter("evaluation") != null && !request.getParameter("evaluation").isEmpty())
                )
        ) {

            url = "/quiron/care/treatment?id=" + request.getParameter("treatmentId");

            treatment.setStatus(request.getParameter("statusCode"));
            treatment.setProviderComments(request.getParameter("providerComment"));
            treatment.setPatientComments(request.getParameter("patientComment"));

            if (treatmentManager.saveTreatment(treatment)) {
                session.setAttribute("treatment", treatment);
            }

            session.setAttribute("message", treatmentManager.getMessage());
            request.setAttribute("title", title);
        }
        response.sendRedirect(url);
*/
    }

    /**
     * Returns a list with the required form fields to validate.
     *
     * @return List of required fields.
     */
    private List<String> getRequiredFields(){

        List<String> requiredFields = new ArrayList<>();
        requiredFields.add("treatmentId");
        requiredFields.add("statusCode");

        return requiredFields;
    }
}
