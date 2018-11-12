package co.net.quiron.controller.patient;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.account.ProfileManager;
import co.net.quiron.application.factory.ManagerFactory;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.care.Visit;
import co.net.quiron.domain.institution.Organization;
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
        name = "patient-visits",
        urlPatterns = "/patient/visits"
)
public class VisitList extends HttpServlet {

    @Override
    public void doGet (HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String url = "/patient/visits.jsp";
        String title = "My Visits";
        String personType = (String) session.getAttribute("personType");

        request.setAttribute("title", title);
        session.setAttribute("currentPage", "My Visits");

        String username = request.getUserPrincipal().getName();
        AccountManager accountManager =  new AccountManager();
        ProfileManager profileManager = new ProfileManager();
        EntityManager<Visit> visitManager = ManagerFactory.getManager(Visit.class);

        accountManager.loadUserAccount(username, personType);
        session.setAttribute("username", username);

        List<Visit> visits = new ArrayList<>();

        String state = "A";

        if ((request.getParameter("state") != null
                && !request.getParameter("state").isEmpty())){

            switch (request.getParameter("state")) {
                case "completed":
                    state = "C";
                    break;

                case "canceled":
                    state = "D";
                    break;
            }
            //state = request.getParameter("state");
        }

        String finalState = state;

        visits = visitManager.getList()
                .stream()
                .filter(v -> finalState.equals(v.getStatus()))
                .collect(Collectors.toList());

        request.setAttribute("visits", visits);
        request.setAttribute("state", state);

        if (visits.size() == 0) {
            Message message = new Message();
            message.setType(MessageType.WARNING);
            message.setDescription("Records not found");
            accountManager.setMessage(message);
            session.setAttribute("message", message);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
