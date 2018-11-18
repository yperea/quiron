package co.net.quiron.application.vendor;

import co.net.quiron.util.PropertiesLoader;
import co.net.quiron.vendor.com.apimedic.Gender;
import co.net.quiron.vendor.com.apimedic.SelectorStatus;
import co.net.quiron.vendor.com.apimedic.Symptom;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
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
import java.util.List;
import java.util.Properties;

/**
 * The type Api medic manager.
 */
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
     * @throws UnsupportedEncodingException the unsupported encoding exception
     * @throws NoSuchAlgorithmException     the no such algorithm exception
     * @throws InvalidKeyException          the invalid key exception
     */
    public ApiMedicManager (String propertiesFileName)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
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
            throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {

        String authenticationUrl = properties.getProperty("apimedic.auth.endpoint");
        String apiKey = properties.getProperty("apimedic.apikey.username");
        String secretKey = properties.getProperty("apimedic.apikey.password");

        byte[] secretBytes = secretKey.getBytes("UTF-8");
        byte[] dataBytes = authenticationUrl.getBytes("UTF-8");

        SecretKeySpec keySpec = new SecretKeySpec(secretBytes, "HmacMD5");
        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(keySpec);

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
         * @param token the address
         * @param language the country
         * @param format the value based on the population count or in percentages
         * @param symptoms Serialized array of selected symptom ids in json format. example symptoms=[234,235,236]
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
     * Gets symptoms list.
     *
     * @param inputSymptoms  the input symptoms
     * @param gender         the gender
     * @param birthYear      the birth year
     * @param locationId     the location id
     * @param selectorStatus the selector status
     * @return the symptoms list
     * @throws IOException the io exception
     */
    public List<Symptom> getSymptomsList (String inputSymptoms,
                                          Gender gender,
                                          int birthYear,
                                          int locationId,
                                          SelectorStatus selectorStatus) throws IOException {



        String apiUrl = getSymptomsUrl(accessToken, language, format,
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

        List<Symptom> symptoms = mapper.readValue(response, new TypeReference<List<Symptom>>(){});
        //Symptom symptom = symptoms.stream().filter(s -> s.getID() == 9 ).findFirst().get();
        return symptoms;
    }

    /**
     * Gets symptoms list.
     *
     * @return the symptoms list
     * @throws IOException the io exception
     */
    public List<Symptom> getSymptomsList () throws IOException {

        String inputSymptoms = null;
        Gender gender = null;
        int birthYear = 0;
        int locationId = 0;
        SelectorStatus selectorStatus = null;

        return getSymptomsList(inputSymptoms, gender, birthYear,locationId, selectorStatus);
    }
}