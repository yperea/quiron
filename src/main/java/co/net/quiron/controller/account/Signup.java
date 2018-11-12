package co.net.quiron.controller.account;

import co.net.quiron.application.account.AccountManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name="signup",
        urlPatterns = {"/account/signup"}
)
public class Signup extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {

        String url = "/account/signup.jsp";
        String title = "Sign Up";

        request.setAttribute("title", title);

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        String url = "/account/signup";
        String title = "Sign Up";

        boolean successfullSignup = false;
        int userTypeId = 3; //Patient
        int roleId = 2; //User

        AccountManager accountManager;

        HttpSession session = request.getSession();
        request.setAttribute("title", title);

        if ((request.getParameter("firstName") != null || !request.getParameter("firstName").isEmpty())
            && (request.getParameter("lastName") != null || !request.getParameter("lastName").isEmpty())
            && (request.getParameter("userName") != null || !request.getParameter("userName").isEmpty())
            && (request.getParameter("birthDate") != null || !request.getParameter("birthDate").isEmpty())
            && (request.getParameter("gender") != null || !request.getParameter("gender").isEmpty())
            && (request.getParameter("email") != null || !request.getParameter("email").isEmpty())
            && (request.getParameter("password") != null || !request.getParameter("password").isEmpty())
            && (request.getParameter("confirmation") != null || !request.getParameter("confirmation").isEmpty())) {


            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String userName = request.getParameter("userName");
            String birthDate = request.getParameter("birthDate");
            String gender = request.getParameter("gender");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String confirmation = request.getParameter("confirmation");

            accountManager =  new AccountManager();
            successfullSignup = accountManager.signup(userTypeId, roleId, firstName, lastName, userName, email, birthDate, gender, password, confirmation);
            session.setAttribute("account", accountManager);
            String personType = "patient";
            session.setAttribute("personType", personType);
            //String username = request.getUserPrincipal().getName();
            //accountManager.loadUserAccount(username, personType);

        }

        if (successfullSignup) {
            response.sendRedirect("/quiron/patient/profile");
        } else {
            request.setAttribute("firstName", request.getParameter("firstName"));
            request.setAttribute("lastName", request.getParameter("lastName"));
            request.setAttribute("userName", request.getParameter("userName"));
            request.setAttribute("email", request.getParameter("email"));

/*
            RequestDispatcher dispatcher = request.getRequestDispatcher("/public/signup.jsp");
            dispatcher.forward(request, response);
*/

            response.sendRedirect("/account/signup");

        }
    }
}
