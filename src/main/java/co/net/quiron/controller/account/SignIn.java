package co.net.quiron.controller.account;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.account.ProfileManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Sign In Controller.
 */
@WebServlet (
        name="signin",
        urlPatterns = {"/account/signin" }
)
public class SignIn extends HttpServlet {

    @Override
    protected void doGet (HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String url = "/account/signin.jsp";
        String title = "Sign In";

        request.setAttribute("title", title);

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String url = "/account/signin";
        String title = "Sign In";

        AccountManager accountManager =  new AccountManager();
        ProfileManager profileManager = new ProfileManager();

        //accountManager.loadUserAccount(username);
    }
}
