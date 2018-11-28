package co.net.quiron.controller.care;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.account.ProfileManager;
import co.net.quiron.application.care.VisitManager;
import co.net.quiron.application.factory.ManagerFactory;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.application.vendor.ApiMedicManager;
import co.net.quiron.domain.care.Visit;
import co.net.quiron.util.Message;
import co.net.quiron.util.MessageType;
import co.net.quiron.vendor.com.apimedic.Symptom;

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
import java.util.stream.Collectors;

@WebServlet(
        name = "visit",
        urlPatterns = {"/patient/visit", "/provider/visit"}
)
public class VisitForm extends HttpServlet {

    @Override
    public void doGet (HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String url = "/care/visit.jsp";
        String title = "My Visit";
        String personType = (String) session.getAttribute("personType");
        String username = request.getUserPrincipal().getName();

        request.setAttribute("title", title);
        session.setAttribute("currentPage", "My Visit");

        AccountManager accountManager =  new AccountManager();
        accountManager.loadUserAccount(username, personType);
        ProfileManager profileManager = new ProfileManager();

        ApiMedicManager apiMedicManager = new ApiMedicManager("/apimedic.properties");
        List<Symptom> symptoms = apiMedicManager.getSymptomsList();

        Visit visit = null;
        VisitManager visitManager = new VisitManager(username, personType);
        if ((request.getParameter("id") != null
            && !request.getParameter("id").isEmpty())){
            int visitId = Integer.parseInt(request.getParameter("id"));
            visit = visitManager.getPatientVisit(visitId);
        }
        request.setAttribute("visit", visit);
        request.setAttribute("symptoms", symptoms);
        session.setAttribute("account", accountManager);
        session.setAttribute("message", visitManager.getMessage());

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
