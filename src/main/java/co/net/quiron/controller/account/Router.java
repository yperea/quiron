package co.net.quiron.controller.account;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name="account-router",
        urlPatterns = {"/account/router"}
)
public class Router extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String url = "/quiron/patient/profile";
        String personType = "patient";

        if ((request.getParameter("tp") != null && !request.getParameter("tp").isEmpty())){
            switch (request.getParameter("tp")) {
                case "provider":
                    personType = "provider";
                    url = "/quiron/provider/profile";
                    break;
            }
        }

        session.setAttribute("personType", personType);
        response.sendRedirect(url);
    }
}
