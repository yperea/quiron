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
        urlPatterns = {"/care/visits", "/visits"}
)
public class VisitList extends HttpServlet {

    @Override
    public void doGet (HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String url = "/care/visits.jsp";
        String title = "My Visits";

        AccountManager accountManager = AccountManager.getAccountManager(session, request);
        VisitManager visitManager = new VisitManager(accountManager);
        List<Visit> visits = visitManager.getVisitsList(request.getParameter("state"));

        request.setAttribute("title", title);
        request.setAttribute("visits", visits);
        request.setAttribute("state", request.getParameter("state"));
        session.setAttribute("message", visitManager.getMessage());

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
