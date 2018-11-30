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
        request.setAttribute("visitId", request.getParameter("id"));

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

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession();

        String url = "/quiron/patient/visit";
        String title = "My Visit";
        String personType = (String) session.getAttribute("personType");
        String username = request.getUserPrincipal().getName();

        request.setAttribute("title", title);
        ProfileManager profileManager;
        AccountManager accountManager =  (AccountManager) session.getAttribute("account");
        VisitManager visitManager = new VisitManager(username, personType);

        String symptom = request.getParameter("symptom");
        String diagnosis = request.getParameter("diagnosis");


        if ((request.getParameter("visitId") != null || !request.getParameter("visitId").isEmpty() )
                && (request.getParameter("startDate") != null || !request.getParameter("startDate").isEmpty())
                && (request.getParameter("startTime") != null || !request.getParameter("startTime").isEmpty())
                && (request.getParameter("endTime") != null || !request.getParameter("endTime").isEmpty())
                && (request.getParameter("symptom") != null || !request.getParameter("symptom").isEmpty())
                && (request.getParameter("diagnosis") != null || !request.getParameter("diagnosis").isEmpty())
                && (request.getParameter("weight") != null || !request.getParameter("weight").isEmpty())
                && (request.getParameter("height") != null || !request.getParameter("height").isEmpty())
                && (request.getParameter("pulse") != null || !request.getParameter("pulse").isEmpty())
                && (request.getParameter("respiration") != null || !request.getParameter("respiration").isEmpty())
                && (request.getParameter("bmi") != null || !request.getParameter("bmi").isEmpty())
                && (request.getParameter("temperature") != null || !request.getParameter("temperature").isEmpty())
                && (request.getParameter("providerComment") != null || !request.getParameter("providerComment").isEmpty())
        ) {
            profileManager =  new ProfileManager();

            int visitId = Integer.parseInt(request.getParameter("visitId"));;
            String startDate = request.getParameter("startDate");
            String startTime = request.getParameter("startTime");
            String endTime = request.getParameter("endTime");
/*
            String symptom = request.getParameter("symptom");
            String diagnosis = request.getParameter("diagnosis");
*/

            String weight =  request.getParameter("weight");
            String height =  request.getParameter("height");
            String pulse =  request.getParameter("pulse");
            String respiration =  request.getParameter("respiration");
            String bmi =  request.getParameter("bmi");
            String temperature =  request.getParameter("temperature");
            String providerComment =  request.getParameter("providerComment");


            session.setAttribute("visit", visitManager.updateVisit(visitId));
            session.setAttribute("message", visitManager.getMessage());
        }

        response.sendRedirect(url);

    }

}
