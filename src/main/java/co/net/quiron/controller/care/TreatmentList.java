package co.net.quiron.controller.care;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.care.TreatmentManager;
import co.net.quiron.application.care.VisitManager;
import co.net.quiron.domain.care.Treatment;
import co.net.quiron.domain.care.Visit;

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
        String personType = (String) session.getAttribute("personType");
        String username = request.getUserPrincipal().getName();

        AccountManager accountManager = (AccountManager) session.getAttribute("account");
        if (accountManager == null){
            accountManager = new AccountManager(username, personType);
            session.setAttribute("account", accountManager);
        }

        TreatmentManager treatmentManager = new TreatmentManager(accountManager);
        List<Treatment> treatments = null;
        if (personType.equals("provider")) {
            treatments = treatmentManager.getProviderTreatmentsList();
        } else {
            treatments = treatmentManager.getPatientTreatmentsList();
        }

        request.setAttribute("title", title);
        request.setAttribute("treatments", treatments);
        request.setAttribute("message", treatmentManager.getMessage());

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
