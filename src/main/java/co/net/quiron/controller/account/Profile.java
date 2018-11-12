package co.net.quiron.controller.account;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.account.ProfileManager;
import co.net.quiron.application.factory.ManagerFactory;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.location.Country;
import co.net.quiron.domain.location.State;
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

        request.setAttribute("title", title);
        AccountManager accountManager =  new AccountManager();
        ProfileManager profileManager = new ProfileManager();
        accountManager.loadUserAccount(username, personType);

        if (accountManager.isSigned()) {
            session.setAttribute("username", username);
            session.setAttribute("account", accountManager);
            //session.setAttribute("profile", profileManager.getPatientProfile(accountManager));
            session.setAttribute("profile", profileManager.getProfile(accountManager, personType));
            session.setAttribute("currentPage", "My Profile");

            EntityManager<Country> countryManager = ManagerFactory.getManager(Country.class);
            Set<State> states = countryManager.get(1).getStates();
            session.setAttribute("states", states);

            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);

        } else {
            url = "/quiron/account/router?tp=" + personType ;
            session.invalidate();
/*
            Message message =  new Message();
            message.setType(MessageType.ERROR);
            message.setDescription("Account doesn't exist as " + personType);
            accountManager.setMessage(message);
            session.setAttribute("account", accountManager);
            session.setAttribute("message", message);
*/

            response.sendRedirect(url);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();

        String url = "/quiron/patient/profile";
        String title = "My Profile";
        String personType = (String) session.getAttribute("personType");

        request.setAttribute("title", title);
        ProfileManager profileManager;
        AccountManager accountManager =  (AccountManager) session.getAttribute("account");

        if ((request.getParameter("firstName") != null || !request.getParameter("firstName").isEmpty() )
                && (request.getParameter("lastName") != null || !request.getParameter("lastName").isEmpty())
                && (request.getParameter("address1") != null || !request.getParameter("address1").isEmpty())
                && (request.getParameter("city") != null || !request.getParameter("city").isEmpty())
                && (request.getParameter("state") != null || !request.getParameter("state").isEmpty())
                && (request.getParameter("zip") != null || !request.getParameter("zip").isEmpty())

        ) {
            profileManager =  new ProfileManager();

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

                session.setAttribute("profile", profileManager.saveProfile(accountManager, firstName, lastName,
                        address1, address2, city, stateId, postalCode, birthDate, gender));


            } else if(personType == "provider"
                    && (request.getParameter("npi") != null || !request.getParameter("npi").isEmpty())
            ){
                String npi = request.getParameter("npi");
                url = "/quiron/provider/profile";
                session.setAttribute("profile", profileManager.saveProfile(accountManager, firstName, lastName,
                        address1, address2, city, stateId, postalCode, npi));

            }

/*
            session.setAttribute("profile", profileManager.savePatientProfile(accountManager, firstName, lastName,
                                 address1, address2, city, stateId, postalCode, birthDate, gender));
*/
            session.setAttribute("message", profileManager.getMessage());
        }

        response.sendRedirect(url);

    }
}
