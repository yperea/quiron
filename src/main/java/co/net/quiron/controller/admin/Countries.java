package co.net.quiron.controller.admin;

import co.net.quiron.application.admin.CountryManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller for the Country model.
 */
@WebServlet(
        name = "get-countries",
        urlPatterns = {"/Countries", "/Countries/Index"}
)
public class Countries extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //super.doGet(req, resp);
        String url = "/admin/countryList.jsp";
        String title = "Country List";

        CountryManager countryManager = new CountryManager();

        req.setAttribute("countries", countryManager.getList());
        req.setAttribute("title", title);

        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
