package co.net.quiron.controller.admin;

import co.net.quiron.application.admin.AddressTypesManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller for the Address Type model.
 */
@WebServlet(
        name = "get-address-types",
        urlPatterns = {"/AddressTypes", "/AddressTypes/index"}
)
public class AddressTypesController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //super.doGet(req, resp);
        String url = "/admin/addressTypeList.jsp";
        String title = "Address Type List";

        AddressTypesManager addressTypesManager = new AddressTypesManager();

        req.setAttribute("addressTypes", addressTypesManager.getAll());
        req.setAttribute("title", title);

        //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        RequestDispatcher dispatcher = req.getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
