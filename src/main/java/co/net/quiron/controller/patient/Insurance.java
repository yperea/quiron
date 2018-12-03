package co.net.quiron.controller.patient;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.institution.OrganizationManager;
import co.net.quiron.domain.institution.Organization;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name="patient-insurance",
        urlPatterns = {"/patient/insurance"}
)
public class Insurance extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String url = "/patient/insurance.jsp";
        String title = "My Profile";
        String username = request.getUserPrincipal().getName();
        String personType = (String) session.getAttribute("personType");

        AccountManager accountManager = (AccountManager) session.getAttribute("account");
        if (accountManager == null){
            accountManager = new AccountManager(username, personType);
        }

        OrganizationManager organizationManager = new OrganizationManager();
        List<Organization> companies = organizationManager.getInsuranceCompanies();

        session.setAttribute("companies", companies);
        request.setAttribute("title", title);
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();

        String url = "/quiron/patient/insurance";
        String title = "My Profile";
        request.setAttribute("title", title);
        String username = request.getUserPrincipal().getName();
        String personType = (String) session.getAttribute("personType");

        AccountManager accountManager = (AccountManager) session.getAttribute("account");
        if (accountManager == null){
            accountManager = new AccountManager(username, personType);
        }

        if ((request.getParameter("company") != null || !request.getParameter("company").isEmpty() )
                && (request.getParameter("subscriber") != null || !request.getParameter("subscriber").isEmpty())) {

            int companyId = Integer.parseInt(request.getParameter("company"));
            String subscriberCode = request.getParameter("subscriber");

            session.setAttribute("profile", accountManager
                    .savePatientInsurance(accountManager, companyId, subscriberCode));
            session.setAttribute("message", accountManager.getMessage());
        }
        response.sendRedirect(url);
    }
}
