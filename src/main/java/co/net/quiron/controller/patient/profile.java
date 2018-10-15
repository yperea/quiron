package co.net.quiron.controller.patient;

import co.net.quiron.application.account.AccountManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name="patient-profile",
        urlPatterns = {"/patient/profile"}
)
public class profile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String url = "/patient/profile.jsp";
        String title = "My Profile";
        String userName = request.getUserPrincipal().getName();

        request.setAttribute("title", title);

        AccountManager accountManager = new AccountManager();
        accountManager.loadUserAccount(userName);
        accountManager.setSigned(true);
        session.setAttribute("account", accountManager);

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
