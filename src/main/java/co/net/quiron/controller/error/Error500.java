package co.net.quiron.controller.error;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Error 500 Controller.
 */
@WebServlet(
        name="Error500",
        urlPatterns = {"/error500" }
)
public class Error500 extends HttpServlet {

    @Override
    protected void doGet (HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String url = "/public/500.jsp";
        String title = "Error 500";
        request.setAttribute("title", title);
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
