package co.net.quiron.controller.account;

import co.net.quiron.application.account.AccountManager;
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
        String err = request.getParameter("err");

        request.setAttribute("title", title);
        if (err != null && !err.isEmpty() && err.equals("invalid profile")) {
            Message message = new Message(MessageType.ERROR, "No profile found for the username provided.");
            request.setAttribute("message", message);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
