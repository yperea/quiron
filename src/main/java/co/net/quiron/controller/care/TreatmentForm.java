package co.net.quiron.controller.care;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.care.TreatmentManager;
import co.net.quiron.domain.care.Prescription;
import co.net.quiron.domain.care.Treatment;
import co.net.quiron.util.FormManager;
import co.net.quiron.util.Message;
import co.net.quiron.util.MessageType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
        Prescription prescription = null;
        AccountManager accountManager = AccountManager.getAccountManager(session, request);
        TreatmentManager treatmentManager = new TreatmentManager(accountManager);
        int id = Integer.parseInt(FormManager.getNumericValue(request.getParameter("id")));

        Treatment treatment = treatmentManager.getPatientTreatment(id);
        if (treatment != null) {
            prescription = treatment.getPrescriptions().stream().findFirst().orElse(null);
        } else {
            session.setAttribute("message", treatmentManager.getMessage());
        }

        request.setAttribute("title", title);
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
        Prescription prescription = null;
        AccountManager accountManager = AccountManager.getAccountManager(session, request);
        int treatmentId = Integer.parseInt(FormManager.getNumericValue(request.getParameter("treatmentId")));
        String statusCode = FormManager.getValue(request.getParameter("statusCode"));
        int evaluation = Integer.parseInt(FormManager.getNumericValue(request.getParameter("evaluation")));
        String providerComments = FormManager.getValue(request.getParameter("providerComment"));
        String patientComments = FormManager.getValue(request.getParameter("patientComment"));

        TreatmentManager treatmentManager = new TreatmentManager(accountManager);
        Treatment treatment = treatmentManager.getPatientTreatment(treatmentId);
        if (treatment != null) {
            prescription = treatment.getPrescriptions().stream().findFirst().orElse(null);
        } else {
            session.setAttribute("message", treatmentManager.getMessage());
        }

        treatment.setId(treatmentId);
        treatment.setStatus(statusCode);
        treatment.setEvaluation(evaluation);
        treatment.setProviderComments(providerComments);
        treatment.setPatientComments(patientComments);

        if (FormManager.validForm(request, getRequiredFields())) {
            url += "?id=" + treatmentId;
            treatmentManager.saveTreatment(treatment);
            session.setAttribute("message", treatmentManager.getMessage());
            response.sendRedirect(url);
        } else {
            url = "/care/treatment.jsp?id=" + treatmentId;
            request.setAttribute("title", title);
            request.setAttribute("treatment", treatment);
            request.setAttribute("prescription", prescription);
            Message message = new Message(MessageType.ERROR, "Missing data error. Try that again, and if it still doesn't work, let us know.");
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }
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
