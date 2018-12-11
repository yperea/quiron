package co.net.quiron.service;

import co.net.quiron.application.vendor.ApiMedicManager;
import co.net.quiron.vendor.com.apimedic.Diagnosis;
import co.net.quiron.vendor.com.apimedic.Issue;
import co.net.quiron.vendor.com.apimedic.Symptom;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("apimedic")
public class ApiMedic {

    @GET
    @Produces("application/json")
    @Path("/json/diagnosis")
    public Response getDiagnosis(@Context HttpServletRequest request) throws JsonProcessingException {


        ApiMedicManager apiMedicManager = new ApiMedicManager("/apimedic.properties");
        String requestSymptoms = request.getParameter("symptom");
        //String requestSymptoms = request.getParameter("relatives[symptom]");
        String requestBirthYear = request.getParameter("birthYear");
        String requestGender = request.getParameter("gender");
        int responseStatusCode = 400;

        List<Issue> issuesList = new ArrayList<>();

        if ((requestSymptoms != null && !requestSymptoms.isEmpty() )
            && (requestBirthYear != null && !requestBirthYear.isEmpty())
            && (requestGender != null && !requestGender.isEmpty())) {

            int symptom =  Integer.parseInt(requestSymptoms);
            String genderCode = requestGender.toString();
            int birthYear = Integer.parseInt(requestBirthYear);

            issuesList = apiMedicManager.getIssuesListBySymptom(symptom, genderCode, birthYear);

            responseStatusCode = 200;
        }

        ObjectMapper objectMapper = new ObjectMapper();

        String json = objectMapper.writeValueAsString(issuesList);

        return Response.status(responseStatusCode)
                .entity(json)
                .build();
    }

    @GET
    @Produces("application/json")
    @Path("/json/symptoms")
    public Response getSymptoms(@Context HttpServletRequest request) throws JsonProcessingException {

        List<Symptom> symptomList = null;
        ApiMedicManager apiMedicManager = new ApiMedicManager("/apimedic.properties");
        String requestSymptoms = request.getParameter("symptoms");
        String requestBirthYear = request.getParameter("birthYear");
        String requestGender = request.getParameter("gender");
        int responseStatusCode = 400;

        symptomList = apiMedicManager.getSymptomsList();

        if (symptomList.size()>0) {
            responseStatusCode = 200;
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(symptomList);

        return Response.status(responseStatusCode)
                .entity(json)
                .build();
    }

}
