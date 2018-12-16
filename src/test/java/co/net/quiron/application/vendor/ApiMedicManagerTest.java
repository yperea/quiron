package co.net.quiron.application.vendor;

import co.net.quiron.util.PropertiesLoader;
import co.net.quiron.vendor.com.apimedic.Diagnosis;
import co.net.quiron.vendor.com.apimedic.Gender;
import co.net.quiron.vendor.com.apimedic.SelectorStatus;
import co.net.quiron.vendor.com.apimedic.Symptom;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

class ApiMedicManagerTest implements PropertiesLoader {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private ApiMedicManager apiMedicManager;
    private Properties properties;
    private String propertiesFileName;
    @BeforeEach
    void setUp(){
        propertiesFileName = "/apimedic.properties";
        apiMedicManager = new ApiMedicManager(propertiesFileName);
        properties = loadProperties(propertiesFileName);
    }

    @Test
    void testGetSymptomsListWithArguments() {

        List<Integer> symptomList = new ArrayList<>();

        symptomList.add(10);
        symptomList.add(238);
        symptomList.add(104);

        String inputSymptoms = symptomList.toString().replace(" ","");//"[10,238,104]";
        Gender gender = Gender.male;
        int birthYear = 1977;
        int locationId = 0;
        SelectorStatus selectorStatus = SelectorStatus.man;

        List<Symptom> symptoms = apiMedicManager.getSymptomsList(
                                    inputSymptoms, gender,
                                    birthYear, locationId,
                                    selectorStatus);

        assertEquals(61, symptoms.size());
    }

    @Test
    void testGetSymptomsListWithNoArguments() {

        List<Symptom> symptoms = apiMedicManager.getSymptomsList();

        assertEquals(63, symptoms.size());
    }

    @Test
    void testGetDiagnosisList() {

        List<Integer> symptomList = new ArrayList<>();
        symptomList.add(10);
        String symptoms = symptomList.toString().replace(" ","");
        int birthYear = 1977;

        List<Diagnosis> diagnosisList = apiMedicManager.getDiagnosisList(symptoms,Gender.male, birthYear);
        assertEquals(7, diagnosisList.size());
    }


    @Test
    void testGetRawDiagnosisListWithNoArguments() {

        String token = apiMedicManager.getAccessToken();

        String apiUrl = "/diagnosis";
        int birthYear = 1977;
        String gender = "male";
        String language ="en-gb";
        String format = "json";
        String yearOfBirth = Integer.valueOf(birthYear).toString();
        List<Integer> symptomList = new ArrayList<>();
        symptomList.add(10);
        String symptoms = symptomList.toString().replace(" ","");

        apiUrl += "?token=" + token + "&language=" + language;

        if (format != null && format != "") {
            apiUrl += "&format=" + format;
        }

        if (symptoms != null && symptoms != "") {
            apiUrl += "&symptoms=" + symptoms;
        }

        if (gender != null && gender.toString() != "") {
            apiUrl += "&gender=" + gender.toString();
        }
        if (yearOfBirth != null && yearOfBirth != "" && birthYear != 0) {
            apiUrl += "&year_of_birth=" + birthYear;
        }

        String url = properties.getProperty("apimedic.service.endpoint") + apiUrl;
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        String response = target.request(MediaType.APPLICATION_JSON)
                .get(String.class)
                .trim()
                .replaceFirst("\ufeff", "");

        ObjectMapper mapper = new ObjectMapper();
        List<Diagnosis> diagnosisList = null;
        try {
            diagnosisList = mapper.readValue(response, new TypeReference<List<Diagnosis>>(){});
            //Symptoms symptomsList = mapper.readValue(response, Symptoms.class);
        } catch (IOException e) {
            logger.error("testGetRawDiagnosisListWithNoArguments(): " + e.getStackTrace());
        }
        assertEquals(7, diagnosisList.size());
    }
}