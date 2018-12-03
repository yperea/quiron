package co.net.quiron.controller.account;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.location.LocationManager;
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
        name="profile",
        urlPatterns = {"/patient/profile", "/provider/profile"}
)
public class Profile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String url = "/private/profile.jsp";
        String title = "My Profile";
        String username = request.getUserPrincipal().getName();
        String personType = (String) session.getAttribute("personType");

        AccountManager accountManager = (AccountManager) session.getAttribute("account");
        if (accountManager == null){
            accountManager = new AccountManager(username, personType);
        }

        request.setAttribute("title", title);

        if (accountManager.isSigned()) {
            session.setAttribute("username", username);
            session.setAttribute("account", accountManager);
            session.setAttribute("profile", accountManager.getProfile());
            session.setAttribute("personType", accountManager.getProfile().getPersonType());

            LocationManager locationManager = new LocationManager();
            Set<State> states = locationManager.getStates("US");
            session.setAttribute("states", states);

            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);

        } else {
            url = "/quiron/account/router?tp=" + personType ;
            session.invalidate();
            response.sendRedirect(url);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();

        String url = "/quiron/patient/profile";
        String title = "My Profile";
        String username = request.getUserPrincipal().getName();
        String personType = (String) session.getAttribute("personType");
        AccountManager accountManager = (AccountManager) session.getAttribute("account");
        if (accountManager == null){
            accountManager = new AccountManager(username, personType);
        }

        if ((request.getParameter("firstName") != null || !request.getParameter("firstName").isEmpty() )
                && (request.getParameter("lastName") != null || !request.getParameter("lastName").isEmpty())
                && (request.getParameter("address1") != null || !request.getParameter("address1").isEmpty())
                && (request.getParameter("city") != null || !request.getParameter("city").isEmpty())
                && (request.getParameter("state") != null || !request.getParameter("state").isEmpty())
                && (request.getParameter("zip") != null || !request.getParameter("zip").isEmpty())

        ) {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String address1 = request.getParameter("address1");
            String address2 = request.getParameter("address2");
            String city =  request.getParameter("city");
            int stateId = Integer.parseInt(request.getParameter("state"));
            String postalCode = request.getParameter("zip");

            if(personType == "patient"
                    && (request.getParameter("birthDate") != null || !request.getParameter("birthDate").isEmpty())
                    && (request.getParameter("gender") != null || !request.getParameter("gender").isEmpty())

            ){

                String gender = request.getParameter("gender");
                String birthDate = request.getParameter("birthDate");

                co.net.quiron.domain.account.Profile profile = accountManager.saveProfile(accountManager, firstName, lastName,
                        address1, address2, city, stateId, postalCode, birthDate, gender);

                session.setAttribute("profile", profile);


            } else if(personType == "provider"
                    && (request.getParameter("npi") != null || !request.getParameter("npi").isEmpty())
            ){
                String npi = request.getParameter("npi");
                url = "/quiron/provider/profile";
                session.setAttribute("profile", accountManager.saveProfile(accountManager, firstName, lastName,
                        address1, address2, city, stateId, postalCode, npi));

            }
            request.setAttribute("title", title);
            session.setAttribute("message", accountManager.getMessage());
        }
        response.sendRedirect(url);
    }
}
