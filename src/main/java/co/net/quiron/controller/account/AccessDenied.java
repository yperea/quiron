package co.net.quiron.controller.account;

import co.net.quiron.util.Message;
import co.net.quiron.util.MessageType;

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
@WebServlet(
        name="access-denied",
        urlPatterns = {"/account/access-denied" }
)
public class AccessDenied extends HttpServlet {

    @Override
    protected void doGet (HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String url = "/quiron/account/signin";
        String title = "Sign In";

        request.setAttribute("title", title);
        response.sendRedirect(url);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String url = "/quiron/account/signin";
        String title = "Sign In";

        Message message =  new Message();
        message.setType(MessageType.ERROR);
        message.setDescription("Incorrect username or password.");
        session.setAttribute("message", message);
        request.setAttribute("title", title);
        response.sendRedirect(url);
    }
}
