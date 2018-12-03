package co.net.quiron.controller.care;

import co.net.quiron.application.account.AccountManager;
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

        AccountManager accountManager = (AccountManager) session.getAttribute("account");
        if (accountManager == null){
            accountManager = new AccountManager(username, personType);
            session.setAttribute("account", accountManager);
            session.setAttribute("profile", accountManager.getProfile());
            session.setAttribute("personType", accountManager.getProfile().getPersonType());
        }

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

        VisitManager visitManager = new VisitManager(accountManager);
        List<Visit> visits = visitManager.getPatientVisitsList(state);

        request.setAttribute("title", title);
        request.setAttribute("visits", visits);
        request.setAttribute("state", state);
        session.setAttribute("message", visitManager.getMessage());

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

/*    static AccountManager getAccountManager(HttpSession session, String personType, String username, AccountManager accountManager) {
        if (accountManager == null){
            accountManager = new AccountManager(username, personType);
            session.setAttribute("account", accountManager);
            session.setAttribute("profile", accountManager.getProfile());
            session.setAttribute("personType", accountManager.getProfile().getPersonType());
        }
        return accountManager;
    }*/

}
