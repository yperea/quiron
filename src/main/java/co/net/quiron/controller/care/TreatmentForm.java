package co.net.quiron.controller.care;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.care.TreatmentManager;
import co.net.quiron.domain.care.Prescription;
import co.net.quiron.domain.care.Treatment;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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

        request.setAttribute("title", title);
        request.setAttribute("treatmentId", request.getParameter("id"));

        AccountManager accountManager = (AccountManager) session.getAttribute("account");
        if (accountManager == null){
            accountManager = new AccountManager(username, personType);
            session.setAttribute("account", accountManager);
        }

        Treatment treatment = null;
        Prescription prescription = null;

        TreatmentManager treatmentManager = new TreatmentManager(accountManager);
        if ((request.getParameter("id") != null && !request.getParameter("id").isEmpty())){
            int treatmentId = Integer.parseInt(request.getParameter("id"));
            treatment = treatmentManager.getPatientTreatment(treatmentId);
            prescription = treatment.getPrescriptions().stream().findFirst().orElse(null);
        }

        request.setAttribute("treatment", treatment);
        request.setAttribute("prescription", prescription);

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();

        String url = "/quiron/care/tratments";
        String title = "My Treatments";
        String personType = (String) session.getAttribute("personType");
        String username = request.getUserPrincipal().getName();

        AccountManager accountManager = (AccountManager) session.getAttribute("account");
        if (accountManager == null){
            accountManager = new AccountManager(username, personType);
            session.setAttribute("account", accountManager);
        }

        TreatmentManager treatmentManager = new TreatmentManager(accountManager);
        Treatment treatment = new Treatment();

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
    }
}
