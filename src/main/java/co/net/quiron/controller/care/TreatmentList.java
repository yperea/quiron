package co.net.quiron.controller.care;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.care.TreatmentManager;
import co.net.quiron.domain.care.Treatment;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "treatments",
        urlPatterns = {"/care/treatments", "/treatments"}
)
public class TreatmentList extends HttpServlet {

    @Override
    public void doGet (HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        String url = "/care/treatments.jsp";
        String title = "My Treatments";

        AccountManager accountManager = AccountManager.getAccountManager(session, request);
        TreatmentManager treatmentManager = new TreatmentManager(accountManager);
        List<Treatment> treatments = treatmentManager.getTreatmentsList();

        request.setAttribute("title", title);
        request.setAttribute("treatments", treatments);
        request.setAttribute("message", treatmentManager.getMessage());

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
