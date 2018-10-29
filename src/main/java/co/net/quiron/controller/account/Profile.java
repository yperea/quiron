package co.net.quiron.controller.account;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.account.ProfileManager;
import co.net.quiron.application.admin.CountryManager;
import co.net.quiron.domain.location.State;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

@WebServlet(
        name="patient-profile",
        urlPatterns = {"/patient/profile"}
)
public class Profile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        /*
        if (session.getAttribute("account") == null) {
            // Session is expired
            session.setAttribute("expireSessionMessage","Your session has expired. Try again.");
            response.sendRedirect("/quiron/account/logout");
            return;
        }
        */

        String url = "/patient/profile.jsp";
        String title = "My Profile";
        String username = request.getUserPrincipal().getName();

        request.setAttribute("title", title);

        AccountManager accountManager =  new AccountManager();
        ProfileManager profileManager = new ProfileManager();

        accountManager.loadUserAccount(username);
        session.setAttribute("username", username);
        session.setAttribute("account", accountManager);
        session.setAttribute("profile", profileManager.getPatientProfile(accountManager));

        CountryManager countryManager = new CountryManager();
        Set<State> states = countryManager.get(1).getStates();
        session.setAttribute("states", states);

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();
        /*
        if (session.getAttribute("account") == null) {
            // Session is expired
            session.setAttribute("expireSessionMessage","Your session has expired. Try again.");
            response.sendRedirect("/account/logout");
            return;
        }
        */

        String url = "/quiron/patient/profile";
        String title = "My Profile";
        request.setAttribute("title", title);

        ProfileManager profileManager;
        AccountManager accountManager =  (AccountManager) session.getAttribute("account");

        if ((request.getParameter("firstName") != null || !request.getParameter("firstName").isEmpty() )
                && (request.getParameter("lastName") != null || !request.getParameter("lastName").isEmpty())
                && (request.getParameter("birthDate") != null || !request.getParameter("birthDate").isEmpty())
                && (request.getParameter("gender") != null || !request.getParameter("gender").isEmpty())
                && (request.getParameter("address1") != null || !request.getParameter("address1").isEmpty())
                && (request.getParameter("city") != null || !request.getParameter("city").isEmpty())
                && (request.getParameter("state") != null || !request.getParameter("state").isEmpty())
                && (request.getParameter("zip") != null || !request.getParameter("zip").isEmpty())) {

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String birthDate = request.getParameter("birthDate");
            String gender = request.getParameter("gender");
            String address1 = request.getParameter("address1");
            String address2 = request.getParameter("address2");
            String city =  request.getParameter("city");
            int stateId = Integer.parseInt(request.getParameter("state"));
            String postalCode = request.getParameter("zip");

            profileManager =  new ProfileManager();

            session.setAttribute("profile", profileManager.savePatientProfile(accountManager, firstName, lastName,
                                 address1, address2, city, stateId, postalCode, birthDate, gender));
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
