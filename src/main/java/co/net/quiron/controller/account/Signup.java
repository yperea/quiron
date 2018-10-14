package co.net.quiron.controller.account;

import co.net.quiron.application.admin.UserManager;
import co.net.quiron.application.util.FormManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        name="signup",
        urlPatterns = {"/public/signup"}
)
public class Signup extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String url = "/public/signup.jsp";
        String title = "Sign Up";

        request.setAttribute("title", title);

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

        //response.sendRedirect("employee-add");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String url = "/account/myprofile.jsp";
        String title = "My Profile";
        FormManager formManager = new FormManager();
        HttpSession session = request.getSession();
        List<String> requiredFields = getRequiredFields();
        request.setAttribute("title", title);



        String firstName = formManager.getValue(request.getParameter("firstName"));
        String lastName = formManager.getValue(request.getParameter("lastName"));
        String ssn = formManager.getValue(request.getParameter("ssn"));
        String department = formManager.getValue(request.getParameter("department"));
        String room = formManager.getValue(request.getParameter("room"));
        String phone = formManager.getValue(request.getParameter("phone"));

        if (formManager.validForm(request, requiredFields)) {

            UserManager userManager = new UserManager();

           // String resultMessage = directory.addEmployee(firstName, lastName, ssn, department, room, phone);
           //session.setAttribute("project4AddMessage", resultMessage);

            // Redirect to Add Employee page
            response.sendRedirect("/public/signup");

        } else {

            String urlContent = "jsp/employeeAdd.jsp";
            String pageTitle = "Add Employee";

            // Content page attributes
            request.setAttribute("contentPage", urlContent);
            request.setAttribute("pageTitle", pageTitle);

            // Form attributes
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            request.setAttribute("ssn", ssn);
            request.setAttribute("department", department);
            request.setAttribute("room", room);
            request.setAttribute("phone", phone);
            request.setAttribute("errorDataEntry", true);

            // Forwarding
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
            dispatcher.forward(request, response);
        }

    }

    /**
     * Returns a list with the required form fields to validate.
     *
     * @return List of required fields.
     */
    private List<String> getRequiredFields() {

        List<String> requiredFields = new ArrayList<>();
        requiredFields.add("firstName");
        requiredFields.add("lastName");
        requiredFields.add("ssn");

        return requiredFields;
    }
}
