package co.net.quiron.controller.account;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
        urlPatterns = {"/public/logout"}
)
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
               HttpServletResponse response) throws ServletException, IOException {

        String url = "/quiron/";

        HttpSession session = request.getSession();
        session.invalidate();

        response.sendRedirect(url);
    }
}
