package co.net.quiron.application.vendor;

import co.net.quiron.util.PropertiesLoader;
import co.net.quiron.vendor.com.apimedic.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.internal.util.Base64;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The type Api medic manager.
 */
@Data
public class ApiMedicManager implements PropertiesLoader {

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private final Logger logger = LogManager.getLogger(this.getClass());

    private Properties properties;
    private String format;
    private String language;
    private String accessToken;

    /**
     * Instantiates a new Geo life manager.
     *
     * @param propertiesFileName the properties file name
     */
    public ApiMedicManager (String propertiesFileName){
        logger.info("ApiMedicManager(String): Instantiating ApiMedicManager class.");
        properties = loadProperties(propertiesFileName);
        format = "json";
        language ="en-gb";

        acquireAuthToken();
    }

    /**
     * Get Access Token to use ApiMedic API.
     *
     */
    private void acquireAuthToken()
            /*throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException*/
    {

        String authenticationUrl = properties.getProperty("apimedic.auth.endpoint");
        String apiKey = properties.getProperty("apimedic.apikey.username");
        String secretKey = properties.getProperty("apimedic.apikey.password");

        byte[] secretBytes = new byte[0];
        byte[] dataBytes = new byte[0];

        try {
            secretBytes = secretKey.getBytes("UTF-8");
            dataBytes = authenticationUrl.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("acquireAuthToken: UnsupportedEncodingException. " + e);
        }

        SecretKeySpec keySpec = new SecretKeySpec(secretBytes, "HmacMD5");
        Mac mac = null;
        try {
            mac = Mac.getInstance("HmacMD5");
        } catch (NoSuchAlgorithmException e) {
            logger.error("acquireAuthToken: NoSuchAlgorithmException. " + e);
        }

        try {
            mac.init(keySpec);
        } catch (InvalidKeyException e) {
            logger.error("acquireAuthToken: NoSuchAlgorithmException. " + e);
        }

        byte[] computedHash = mac.doFinal(dataBytes);
        BASE64Encoder encoder = new BASE64Encoder();

        String computedHashString = encoder.encode(computedHash);
        String authHeader = "Bearer " + apiKey + ":" + computedHashString;

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(authenticationUrl);

        Invocation.Builder builder = target.request().header("Authorization", authHeader);
        Form form = new Form();
        Response response = builder.post(Entity.entity(form, MediaType.TEXT_HTML_TYPE));
        String jsonResponse = response.readEntity(String.class);

        JsonReader jsonReader = Json.createReader(new StringReader(jsonResponse));
        JsonObject jsonObject = jsonReader.readObject();
        jsonReader.close();
        accessToken = jsonObject.getString("Token");

        if (accessToken == null) {
            accessToken = properties.getProperty("apimedic.token");
        }

    }


    /**
     * Gets a well-formed URL to consume the GeoLife services.
     *
     * @param token security token received from https://authservice.priaid.ch/login
     * @param language de-ch, en-gb, fr-fr, es-es, tr-tr
     * @param format json, xml
     * @param symptoms Serialized array of selected symptom ids in json format. example symptoms=[234,235,236]
     * @param gender male, female
     * @param birthYear year of birth
     * @param locationId see Body location id. If locationId = 0, then you get all symptoms
     * @param selectorStatus man, woman, boy, girl
     * @return well-formed symptoms URL including the given arguments
     */
    private static String getSymptomsUrl(String token,
            String language,
            String format,
            String symptoms,
            Gender gender,
            int birthYear,
            int locationId,
            SelectorStatus selectorStatus) {

        String apiUrl = "/symptoms";

        String location = Integer.valueOf(locationId).toString();
        String yearOfBirth = Integer.valueOf(birthYear).toString();

        if (location != null && location != ""
                && selectorStatus != null && selectorStatus.toString() != "") {
            apiUrl += "/" + location + "/" + selectorStatus.toString();
        }

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

        return apiUrl;
    }

    /**
     * Gets a well-formed URL to consume the GeoLife services.
     *
     * @param serviceName the service to consume
     * @param token security token received from https://authservice.priaid.ch/login
     * @param language de-ch, en-gb, fr-fr, es-es, tr-tr
     * @param format json, xml
     * @param symptoms Serialized array of selected symptom ids in json format. example symptoms=[234,235,236]
     * @param gender male, female
     * @param birthYear year of birth
     * @param locationId see Body location id. If locationId = 0, then you get all symptoms
     * @param selectorStatus man, woman, boy, girl
     * @return well-formed symptoms URL including the given arguments
     */
    private static String getUrl(String serviceName,
                                 String token,
                                 String language,
                                 String format,
                                 String symptoms,
                                 Gender gender,
                                 int birthYear,
                                 int locationId,
                                 SelectorStatus selectorStatus) {

        String apiUrl = "/" + serviceName;

        String location = Integer.valueOf(locationId).toString();
        String yearOfBirth = Integer.valueOf(birthYear).toString();

        if (location != null && location != ""
                && selectorStatus != null && selectorStatus.toString() != "") {
            apiUrl += "/" + location + "/" + selectorStatus.toString();
        }

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

        return apiUrl;
    }


    /**
     * Gets a well-formed URL to consume the GeoLife services.
     *
     * @param serviceName the service to consume
     * @param token security token received from https://authservice.priaid.ch/login
     * @param language de-ch, en-gb, fr-fr, es-es, tr-tr
     * @param format json, xml
     * @param symptoms Serialized array of selected symptom ids in json format. example symptoms=[234,235,236]
     * @param gender male, female
     * @param birthYear year of birth
     * @return well-formed symptoms URL including the given arguments
     */
    private static String getUrl(String serviceName,
                                 String token,
                                 String language,
                                 String format,
                                 String symptoms,
                                 Gender gender,
                                 int birthYear) {

        String apiUrl = getUrl(serviceName, token, language, format, symptoms, gender, birthYear,
                        0, null);

        return apiUrl;
    }

    /**
     * Gets symptoms list.
     *
     * @param inputSymptoms  the input symptoms
     * @param gender         the gender
     * @param birthYear      the birth year
     * @param locationId     the location id
     * @param selectorStatus the selector status
     * @return the symptoms list
     */
    public List<Symptom> getSymptomsList (String inputSymptoms,
                                          Gender gender,
                                          int birthYear,
                                          int locationId,
                                          SelectorStatus selectorStatus) {



        String apiUrl = getUrl("symptoms", accessToken, language, format,
                                       inputSymptoms, gender, birthYear,
                                       locationId, selectorStatus);

        String url = properties.getProperty("apimedic.service.endpoint") + apiUrl;
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        String response = target.request(MediaType.APPLICATION_JSON)
                .get(String.class)
                .trim()
                .replaceFirst("\ufeff", "");

        ObjectMapper mapper = new ObjectMapper();

        List<Symptom> symptomslist = null;
        try {
            symptomslist = mapper.readValue(response, new TypeReference<List<Symptom>>(){});
        } catch (IOException e) {
            logger.error(e.getStackTrace());
        }

        return symptomslist;
    }

    /**
     * Gets symptoms list.
     *
     * @return the symptoms list
     */
    public List<Symptom> getSymptomsList () {
        List<Symptom> symptomList = getSymptomsList(null, null, 0,0, null);
        return symptomList;
    }

    /**
     * Gets diagnosis list.
     *
     * @param symptoms  the symptoms
     * @param gender    the gender
     * @param birthYear the birth year
     * @return the diagnosis list
     */
    public List<Diagnosis> getDiagnosisList(String symptoms, Gender gender, int birthYear) {

        String apiUrl = getUrl("diagnosis", accessToken, language, format,
                                symptoms, gender, birthYear);

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
        } catch (IOException e) {
            logger.error(e.getStackTrace());
        }

        return diagnosisList;
    }

    public List<Issue> getIssuesListBySymptom(int symptomId, String genderCode, int birthYear) {

        String symptoms =  "[" + Integer.valueOf(symptomId).toString().trim() + "]";
        Gender gender = Gender.male;
        if (!genderCode.equals("M")){
            gender = Gender.female;
        }

        List<Issue> issuesList = new ArrayList<>();
        List<Diagnosis> diagnosisList = getDiagnosisList(symptoms, gender, birthYear);

        for (Diagnosis diagnosis: diagnosisList) {
            issuesList.add(diagnosis.getIssue());
        }

        return issuesList;
    }
}
