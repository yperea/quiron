package co.net.quiron.controller.care;

import co.net.quiron.application.care.VisitManager;
import co.net.quiron.domain.care.Visit;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "visits",
        urlPatterns = {"/patient/visits", "/provider/visits"}
)
public class VisitList extends HttpServlet {

    @Override
    public void doGet (HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String url = "/care/visits.jsp";
        String title = "My Visits";
        String personType = (String) session.getAttribute("personType");
        String username = request.getUserPrincipal().getName();

        request.setAttribute("title", title);
        session.setAttribute("currentPage", "My Visits");

        String state = "A";

        if ((request.getParameter("state") != null && !request.getParameter("state").isEmpty())){
            switch (request.getParameter("state")) {
                case "completed":
                    state = "C";
                    break;
                case "canceled":
                    state = "D";
                    break;
            }
        }

        VisitManager visitManager = new VisitManager(username, personType);
        List<Visit> visits = visitManager.getPatientVisitsList(state);

        request.setAttribute("visits", visits);
        request.setAttribute("state", state);
        session.setAttribute("message", visitManager.getMessage());

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }


/*
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
        //ProfileManager profileManager = new ProfileManager();
        EntityManager<Visit> visitManager = ManagerFactory.getManager(Visit.class);

        //accountManager.loadUserAccount(username, personType);
        //session.setAttribute("username", username);

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
*/
}
