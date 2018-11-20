package co.net.quiron.controller.care;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.factory.ManagerFactory;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.care.Treatment;
import co.net.quiron.domain.care.Visit;
import co.net.quiron.util.Message;
import co.net.quiron.util.MessageType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        request.setAttribute("title", title);
        session.setAttribute("currentPage", "My Treatments");

        String username = request.getUserPrincipal().getName();

        AccountManager accountManager =  new AccountManager();
        EntityManager<Treatment> treatmentManager = ManagerFactory.getManager(Treatment.class);

        accountManager.loadUserAccount(username, personType);
        session.setAttribute("username", username);

        List<Treatment> treatments = treatmentManager.getList()
                                    .stream()
                                    .collect(Collectors.toList());

        request.setAttribute("treatments", treatments);

        if (treatments.size() == 0) {
            Message message = new Message();
            message.setType(MessageType.WARNING);
            message.setDescription("Records not found");
            accountManager.setMessage(message);
            session.setAttribute("message", message);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
