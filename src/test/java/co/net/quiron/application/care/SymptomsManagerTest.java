package co.net.quiron.application.care;

import co.net.quiron.vendor.com.apimedic.Symptom;
import co.net.quiron.vendor.com.apimedic.Symptoms;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SymptomsManagerTest {

    private static final String ACCESS_TOKEN = "access_token";
    private static final String BEARER = "Bearer ";
    private static final String BASIC = "Basic ";
    private static final String CLIENT_CREDENTIALS = "client_credentials";
    private static final String GRANT_TYPE = "grant_type";
    private static final String AUTH_HEADER = "Authorization";
    private static final String COLON = ":";
    private static final String PRIAID_URL = "https://sandbox-healthservice.priaid.ch/";
    //private static final String PRIAID_URL = "https://healthservice.priaid.ch/";

    private String accessToken;

    /**
     * Sets up.
     */
    @BeforeEach
    void setUp() throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        //acquireAuthToken();
        accessToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6Inllc2lkLnBlcmVhQG91dGxvb2suY29tIiwicm9sZSI6IlVzZXIiLCJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9zaWQiOiI0MTU5IiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy92ZXJzaW9uIjoiMjAwIiwiaHR0cDovL2V4YW1wbGUub3JnL2NsYWltcy9saW1pdCI6Ijk5OTk5OTk5OSIsImh0dHA6Ly9leGFtcGxlLm9yZy9jbGFpbXMvbWVtYmVyc2hpcCI6IlByZW1pdW0iLCJodHRwOi8vZXhhbXBsZS5vcmcvY2xhaW1zL2xhbmd1YWdlIjoiZW4tZ2IiLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL2V4cGlyYXRpb24iOiIyMDk5LTEyLTMxIiwiaHR0cDovL2V4YW1wbGUub3JnL2NsYWltcy9tZW1iZXJzaGlwc3RhcnQiOiIyMDE4LTEwLTMxIiwiaXNzIjoiaHR0cHM6Ly9zYW5kYm94LWF1dGhzZXJ2aWNlLnByaWFpZC5jaCIsImF1ZCI6Imh0dHBzOi8vaGVhbHRoc2VydmljZS5wcmlhaWQuY2giLCJleHAiOjE1NDIxNTQyMTAsIm5iZiI6MTU0MjE0NzAxMH0.SlcRWHC5IRN6bIzt10KxeyQ1CrKE9Wler8nj1j16CEA";
        //DatabaseManager dbm = DatabaseManager.getInstance();
        //dbm.runSQL("cleandb.sql");
    }


    @Test
    public void testGetSymptoms() throws IOException, NoSuchAlgorithmException, InvalidKeyException {

        String uri = "https://sandbox-healthservice.priaid.ch/symptoms";
        String token = accessToken;
        String format = "json";
        String language ="en-gb";
        String inputSymptoms = "[10,238,104]";
        String gender = "male";
        int birthYear = 1977;
        int locationId = 0;
        String selectorStatus = "man";

        String apiUrl = getSymptomsUrl(token, language, format, inputSymptoms, gender, birthYear, locationId, selectorStatus);

        String url = PRIAID_URL + apiUrl;
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(url);
        String response = target.request(MediaType.APPLICATION_JSON)
                .get(String.class)
                .trim()
                .replaceFirst("\ufeff", "");

        ObjectMapper mapper = new ObjectMapper();

        List<Symptom> symptoms = mapper.readValue(response, new TypeReference<List<Symptom>>(){});
        //Symptoms symptomsList = mapper.readValue(response, Symptoms.class);
        //Symptom symptom = symptoms.stream().filter(s -> s.getID() == 9 ).findFirst().get();

        assertEquals(61, symptoms.size());
    }

    private void acquireAuthToken() throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {

        String uri = "https://authservice.priaid.ch/login";

        String apiKey = "yesid.perea@outlook.com";
        String secretKey = "Ls23AyHp8b7R5Mjq6";
        byte[] secretBytes = secretKey.getBytes("UTF-8");
        byte[] dataBytes = uri.getBytes("UTF-8");

        SecretKeySpec keySpec = new SecretKeySpec(secretBytes, "HmacMD5");

        //MessageDigest md5 = MessageDigest.getInstance("MD5");
        //md5.reset();
        //md5.update(secretBytes);
        //byte[] computedHash = md5.digest(dataBytes);

        Mac mac = Mac.getInstance("HmacMD5");
        mac.init(keySpec);

        byte[] computedHash = mac.doFinal(dataBytes);
        BASE64Encoder encoder = new BASE64Encoder();

        //String computedHashString = Base64.encodeAsString(computedHash);
        String computedHashString = encoder.encode(computedHash);

/*
        String authHeader = BASIC
                + Base64.encodeAsString("yesid.perea@outlook.com"
                + COLON + "Ls23AyHp8b7R5Mjq6");
*/

        String authHeader = "Bearer " + apiKey + ":" + computedHashString;

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(uri);

        //Invocation.Builder builder = target.request().header(AUTH_HEADER, authHeader);
        Invocation.Builder builder = target.request().header("Authorization", authHeader);
        Form form = new Form();
        //form.param(GRANT_TYPE, CLIENT_CREDENTIALS);
        //form.param("grant_type", "client_credentials");
        Response response = builder.post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED));
        response = builder.post(Entity.entity(form, MediaType.TEXT_HTML_TYPE));
        response = builder.post(Entity.entity(form, MediaType.TEXT_HTML));
        response = builder.post(Entity.entity(form, MediaType.TEXT_PLAIN));
        response = builder.post(Entity.entity(form, MediaType.TEXT_HTML_TYPE));

        String jsonResponse = response.readEntity(String.class);

        //JsonReader jsonReader = Json.createReader(new StringReader(jsonResponse));
        //JsonObject jsonObject = jsonReader.readObject();
        //jsonReader.close();
        //accessToken = jsonObject.getString(ACCESS_TOKEN);
        accessToken = jsonResponse;
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
                                         String gender,
                                         int birthYear,
                                         int locationId,
                                         String selectorStatus) {

        String apiUrl = "symptoms";

        String location = Integer.valueOf(locationId).toString();
        String yearOfBirth = Integer.valueOf(birthYear).toString();

        if (location != null && location != ""
            && selectorStatus != null && selectorStatus != "") {
            apiUrl += "/" + location + "/" + selectorStatus;
        }

        apiUrl += "?token=" + token + "&language=" + language;

        if (format != null && format != "") {
            apiUrl += "&format=" + format;
        }
        if (symptoms != null && symptoms != "") {
            apiUrl += "&symptoms=" + symptoms;
        }
        if (gender != null && gender != "") {
            apiUrl += "&gender=" + gender;
        }
        if (yearOfBirth != null && yearOfBirth != "" && birthYear != 0) {
            apiUrl += "&year_of_birth=" + birthYear;
        }

        return apiUrl;
    }
}
