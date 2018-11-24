package co.net.quiron.controller.care;

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
        urlPatterns = {"/patient/treatments", "/provider/treatments"}
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

        request.setAttribute("title", title);
        session.setAttribute("currentPage", "My Treatments");

        TreatmentManager treatmentManager = new TreatmentManager(username, personType);

        List<Treatment> treatments = treatmentManager.getPatientTreatmentsList();

        request.setAttribute("treatments", treatments);
        session.setAttribute("message", treatmentManager.getMessage());

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
