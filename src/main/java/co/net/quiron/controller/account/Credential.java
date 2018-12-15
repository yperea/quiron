package co.net.quiron.controller.account;


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
        name = "credentials",
        urlPatterns = {"/account/credentials"}
)
public class Credential extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String url = "/account/credentials.jsp";
        String title = "Account Credentials";
        AccountManager accountManager = AccountManager.getAccountManager(session, request);

        request.setAttribute("title", title);
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String url = "/quiron/account/credentials";
        String title = "Account Credentials";

        AccountManager accountManager = AccountManager.getAccountManager(session, request);

        if ((request.getParameter("password") != null || !request.getParameter("password").isEmpty())
                && (request.getParameter("confirmation") != null || !request.getParameter("confirmation").isEmpty())) {

            String password = request.getParameter("password");
            String confirmation = request.getParameter("confirmation");

            if (accountManager.saveCredentials(password, confirmation)) {
                session.setAttribute("account", accountManager);
            }
        }

        session.setAttribute("message", accountManager.getMessage());
        request.setAttribute("title", title);
        response.sendRedirect(url);
    }
}
