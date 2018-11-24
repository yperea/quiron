package co.net.quiron.controller.patient;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.account.ProfileManager;
import co.net.quiron.application.factory.ManagerFactory;
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

        request.setAttribute("title", title);

        AccountManager accountManager =  new AccountManager();
        ProfileManager profileManager = new ProfileManager();

        accountManager.loadUserAccount(username, personType);
        session.setAttribute("username", username);
        //session.setAttribute("account", accountManager);
        //session.setAttribute("profile", profileManager.getPatientProfile(accountManager));
        session.setAttribute("currentPage", "My Profile");

/*
        OrganizationManager organizationManager = new OrganizationManager();
        List<Organization> companies = organizationManager.getList();
*/
        List<Organization> companies = ManagerFactory.getManager(Organization.class).getList();
        session.setAttribute("companies", companies);

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

        ProfileManager profileManager;
        AccountManager accountManager =  (AccountManager) session.getAttribute("account");

        if ((request.getParameter("company") != null || !request.getParameter("company").isEmpty() )
                && (request.getParameter("subscriber") != null || !request.getParameter("subscriber").isEmpty())) {

            int companyId = Integer.parseInt(request.getParameter("company"));
            String subscriberCode = request.getParameter("subscriber");

            profileManager =  new ProfileManager();

            session.setAttribute("profile", profileManager.savePatientInsurance(accountManager, companyId, subscriberCode));
            session.setAttribute("message", profileManager.getMessage());
        }

        response.sendRedirect(url);

        /*
        if (successfullUpdate) {
            response.sendRedirect("/quiron/patient/Profile");
        } else {
            request.setAttribute("firstName", request.getParameter("firstName"));
            request.setAttribute("lastName", request.getParameter("lastName"));
            request.setAttribute("address1", request.getParameter("address1"));
            request.setAttribute("address2", request.getParameter("address2"));
            request.setAttribute("countryId", request.getParameter("countryId"));
            request.setAttribute("stateId", request.getParameter("stateId"));
            request.setAttribute("postalCode", request.getParameter("postalCode"));

            response.sendRedirect(url);
        }
        */
    }
}
