package co.net.quiron.controller.care;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.account.ProfileManager;
import co.net.quiron.application.care.VisitManager;
import co.net.quiron.application.factory.ManagerFactory;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.care.Visit;
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
        ProfileManager profileManager = new ProfileManager();

        //EntityManager<Visit> visitManager = ManagerFactory.getManager(Visit.class);

        VisitManager visitManager = new VisitManager(username, personType);

        //accountManager.loadUserAccount(username,personType);

        Visit visit = new Visit();

        if ((request.getParameter("id") != null
                && !request.getParameter("id").isEmpty())){

            int visitId = Integer.parseInt(request.getParameter("id"));
            visit = visitManager.getPatientVisit(visitId);

        }
        request.setAttribute("visit", visit);
        session.setAttribute("message", visitManager.getMessage());

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
