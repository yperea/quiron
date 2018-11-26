package co.net.quiron.application.vendor;

import co.net.quiron.vendor.com.apimedic.Gender;
import co.net.quiron.vendor.com.apimedic.SelectorStatus;
import co.net.quiron.vendor.com.apimedic.Symptom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ApiMedicManagerTest {

    private ApiMedicManager apiMedicManager;

    @BeforeEach
    void setUp(){
        apiMedicManager = new ApiMedicManager("/apimedic.properties");
    }

    @Test
    void getSymptomsListWithArguments() throws IOException {

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
    void getSymptomsListWithNoArguments() throws IOException {

        List<Symptom> symptoms = apiMedicManager.getSymptomsList();

        assertEquals(63, symptoms.size());
    }

}