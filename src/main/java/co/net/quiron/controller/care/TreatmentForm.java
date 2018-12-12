package co.net.quiron.controller.care;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.care.TreatmentManager;
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
        urlPatterns = {"/treatment"}
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
        TreatmentManager treatmentManager = new TreatmentManager(accountManager);
        if ((request.getParameter("id") != null
                && !request.getParameter("id").isEmpty())){
            int treatmentId = Integer.parseInt(request.getParameter("id"));
            treatment = treatmentManager.getPatientTreatment(treatmentId);
        }

        session.setAttribute("treatment", treatment);

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();

        String url = "/quiron/tratments";
        String title = "My Treatments";
        String personType = (String) session.getAttribute("personType");
        String username = request.getUserPrincipal().getName();

        Treatment treatment = (Treatment) session.getAttribute("treatment");
        AccountManager accountManager = (AccountManager) session.getAttribute("account");
        if (accountManager == null){
            accountManager = new AccountManager(username, personType);
            session.setAttribute("account", accountManager);
        }

        TreatmentManager treatmentManager = new TreatmentManager(accountManager);

        if ((request.getParameter("treatmentId") != null && !request.getParameter("treatmentId").isEmpty() )
                &&
                (personType.equals("provider")
                        && (request.getParameter("providerComment") != null && !request.getParameter("providerComment").isEmpty())
                )
                ||
                (personType.equals("patient")
                        && (request.getParameter("providerComment") != null && !request.getParameter("providerComment").isEmpty())
                        && (request.getParameter("evaluation") != null && !request.getParameter("evaluation").isEmpty())
                )
        ) {

            url = "/quiron/treatment?id=" + request.getParameter("treatmentId");

            treatment.setStatus(request.getParameter("statusCode"));

            treatment.setProviderComments(request.getParameter("providerComment"));

            if (treatmentManager.saveTreatment(treatment)) {
                session.setAttribute("treatment", treatment);
            }

            session.setAttribute("message", treatmentManager.getMessage());
            request.setAttribute("title", title);
        }
        response.sendRedirect(url);
    }
}
