package co.net.quiron.controller.account;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.location.LocationManager;
import co.net.quiron.domain.account.Profile;
import co.net.quiron.domain.location.Address;
import co.net.quiron.domain.location.AddressType;
import co.net.quiron.domain.location.State;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@WebServlet(
        name="account",
        urlPatterns = {"/patient/account", "/provider/account", "/account"}
)
public class Account extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String url = "/private/profile.jsp";
        String title = "My Account";

        AccountManager accountManager = AccountManager.getAccountManager(session, request);
        request.setAttribute("title", title);

        if (accountManager.isSigned()) {
            LocationManager locationManager = new LocationManager();
            Set<State> states = locationManager.getStates("US");
            request.setAttribute("states", states);
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);
        } else {
            url = "/quiron/account/router?tp=" + session.getAttribute("personType") + "&err=invalid profile";
            session.invalidate();
            response.sendRedirect(url);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();

        String url = "/quiron/account";
        String title = "My Account";
        String personType = (String) session.getAttribute("personType");
        AccountManager accountManager = AccountManager.getAccountManager(session, request);

        if ((request.getParameter("firstName") != null && !request.getParameter("firstName").isEmpty() )
                && (request.getParameter("lastName") != null && !request.getParameter("lastName").isEmpty())
                && (request.getParameter("address1") != null && !request.getParameter("address1").isEmpty())
                && (request.getParameter("city") != null && !request.getParameter("city").isEmpty())
                && (request.getParameter("state") != null && !request.getParameter("state").isEmpty())
                && (request.getParameter("zip") != null && !request.getParameter("zip").isEmpty())

        ) {

            Address address = new Address();
            Profile profile = new Profile();

            State state = new LocationManager().getState(Integer.parseInt(request.getParameter("state")));
            AddressType addressType =  new LocationManager().getAddressType(3);

            address.setAddressLine1(request.getParameter("address1"));
            address.setAddressLine2(request.getParameter("address2"));
            address.setCity(request.getParameter("city"));
            address.setPostalCode(request.getParameter("zip"));
            address.setState(state);
            address.setAddressType(addressType);

            profile.setFirstName(request.getParameter("firstName"));
            profile.setLastName(request.getParameter("lastName"));
            profile.setAddress(address);

            if(personType.equals("patient")
                    && (request.getParameter("birthDate") != null || !request.getParameter("birthDate").isEmpty())
                    && (request.getParameter("gender") != null || !request.getParameter("gender").isEmpty())
            ){
                profile.setGender(request.getParameter("gender"));
                profile.setBirthDate(LocalDate.parse(request.getParameter("birthDate"),
                        DateTimeFormatter.ofPattern("MM/d/yyyy")));

            } else if(personType.equals("provider")
                    && (request.getParameter("npi") != null || !request.getParameter("npi").isEmpty())
            ){
                profile.setNpi(request.getParameter("npi"));
            }

            if (accountManager.saveProfile(profile)) {
                session.setAttribute("account", accountManager);
            }

            request.setAttribute("title", title);
            session.setAttribute("message", accountManager.getMessage());
        }
        response.sendRedirect(url);
    }
}
