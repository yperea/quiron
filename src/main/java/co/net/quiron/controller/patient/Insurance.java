package co.net.quiron.controller.patient;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.institution.OrganizationManager;
import co.net.quiron.domain.institution.Organization;
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
        name="patient-insurance",
        urlPatterns = {"/patient/insurance"}
)
public class Insurance extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String url = "/patient/insurance.jsp";
        String title = "My Account";

        AccountManager accountManager = AccountManager.getAccountManager(session, request);
        OrganizationManager organizationManager = new OrganizationManager();
        List<Organization> companies = organizationManager.getInsuranceCompanies();

        session.setAttribute("account", accountManager);
        request.setAttribute("companies", companies);
        request.setAttribute("title", title);
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {


        HttpSession session = request.getSession();

        String url = "/quiron/patient/insurance";
        String title = "My Account";
        request.setAttribute("title", title);

        AccountManager accountManager = AccountManager.getAccountManager(session, request);

        int companyId = Integer.parseInt(FormManager.getNumericValue(request.getParameter("company")));
        String subscriberCode = FormManager.getValue(request.getParameter("subscriber"));

        if (FormManager.validForm(request, getRequiredFields())) {
            accountManager.savePatientInsurance(companyId, subscriberCode);
            session.setAttribute("message", accountManager.getMessage());
            response.sendRedirect(url);
        } else {
            url = "/patient/insurance.jsp";
            request.setAttribute("account", accountManager);
            request.setAttribute("companies", new OrganizationManager().getInsuranceCompanies());
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
        requiredFields.add("company");
        requiredFields.add("subscriber");

        return requiredFields;
    }
}
