package co.net.quiron.controller.account;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Logout Controller.
 */
@WebServlet(
        name = "logout",
        urlPatterns = {"/account/logout"}
)
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
               HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("/quiron/account");
    }
}
