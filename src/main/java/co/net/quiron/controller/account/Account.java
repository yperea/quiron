package co.net.quiron.controller.account;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.location.LocationManager;
import co.net.quiron.domain.account.Profile;
import co.net.quiron.domain.location.Address;
import co.net.quiron.domain.location.State;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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
            request.setAttribute("states", new LocationManager().getStates("US"));
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
                          HttpServletResponse response) throws IOException, ServletException {


        HttpSession session = request.getSession();

        String url = "/quiron/account";
        String title = "My Account";
        String personType = (String) session.getAttribute("personType");
        AccountManager accountManager = AccountManager.getAccountManager(session, request);

        Profile profile = accountManager.getProfile();
        Address address = new Address();
        if (profile.getAddress() != null) {
            address = accountManager.getProfile().getAddress();
        }

        State state = new LocationManager()
                .getState(Integer.parseInt(FormManager.getNumericValue(request.getParameter("state"))));

        //TODO: The Address Type needs to be provided as a property, Enum or an argument.
        if (personType.equals("patient")) {
            address.setAddressType(new LocationManager().getAddressType(1));
            profile.setGender(FormManager.getValue(request.getParameter("gender")));
            profile.setBirthDate(LocalDate.parse(FormManager.getValue(request.getParameter("birthDate")),
                    DateTimeFormatter.ofPattern("MM/d/yyyy")));
        } else {
            address.setAddressType(new LocationManager().getAddressType(2));
            profile.setNpi(FormManager.getValue(request.getParameter("npi")));
        }

        address.setAddressLine1(FormManager.getValue(request.getParameter("address1")));
        address.setAddressLine2(FormManager.getValue(request.getParameter("address2")));
        address.setCity(FormManager.getValue(request.getParameter("city")));
        address.setPostalCode(FormManager.getValue(request.getParameter("zip")));
        address.setState(state);

        profile.setFirstName(FormManager.getValue(request.getParameter("firstName")));
        profile.setLastName(FormManager.getValue(request.getParameter("lastName")));
        profile.setAddress(address);

        accountManager.saveProfile(profile);
        session.setAttribute("account", accountManager);

        if (FormManager.validForm(request, getRequiredFields())) {
            accountManager.saveProfile(profile);
            session.setAttribute("account", accountManager);
            session.setAttribute("message", accountManager.getMessage());
            response.sendRedirect(url);
        } else {
            url = "/private/profile.jsp";
            request.setAttribute("title", title);
            request.setAttribute("account", accountManager);
            Message message = new Message(MessageType.ERROR, "Missing data error. Try that again, and if it still doesn't work, let us know.");
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }

/*

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
*/
    }
    /**
     * Returns a list with the required form fields to validate.
     *
     * @return List of required fields.
     */
    private List<String> getRequiredFields(){

        List<String> requiredFields = new ArrayList<>();
        requiredFields.add("firstName");
        requiredFields.add("lastName");
        requiredFields.add("address1");
        requiredFields.add("city");
        requiredFields.add("state");
        requiredFields.add("zip");

        return requiredFields;
    }

}
