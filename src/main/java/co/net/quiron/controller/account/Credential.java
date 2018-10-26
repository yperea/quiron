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
        String username = request.getUserPrincipal().getName();

        request.setAttribute("title", title);

        AccountManager accountManager = new AccountManager();
        accountManager.loadUserAccount(username);
        accountManager.setSigned(true);
        session.setAttribute("account",accountManager);

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}
