package co.net.quiron.controller.error;

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
@WebServlet(
        name="Error404",
        urlPatterns = {"/error404" }
)
public class Error404 extends HttpServlet {

    @Override
    protected void doGet (HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String url = "/public/404.jsp";
        String title = "Error 404";

        request.setAttribute("title", title);

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
