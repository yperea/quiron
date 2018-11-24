package co.net.quiron.application.care;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.factory.ManagerFactory;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.care.Treatment;
import co.net.quiron.domain.care.Visit;
import co.net.quiron.domain.person.Patient;
import co.net.quiron.domain.person.Provider;
import co.net.quiron.util.Message;
import co.net.quiron.util.MessageType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Data
public class TreatmentManager {
    AccountManager accountManager;
    Message message;
    EntityManager<Visit> visitAgent;
    EntityManager<Treatment> treatmentAgent;

    public TreatmentManager() {
        accountManager =  new AccountManager();
        message = new Message();
        visitAgent = ManagerFactory.getManager(Visit.class);
        treatmentAgent = ManagerFactory.getManager(Treatment.class);
    }

    public TreatmentManager(String username, String personType) {
        this();
        accountManager.loadUserAccount(username, personType);
    }


    public List<Treatment> getPatientTreatmentsList() {

        EntityManager<Patient> patientAgent = ManagerFactory.getManager(Patient.class);
        Map<String, Object> params = new TreeMap<>();
        int patientId = accountManager.getId();
        Patient patient = patientAgent.get(patientId);
        params.put("patient", patient);

        List<Visit> visits = visitAgent.getListEquals(params);
        List<Treatment> treatments = new ArrayList<>();

        for (Visit visit: visits) {
            if (visit.getTreatments().size() > 0) {
                Treatment treatment = visit.getTreatments().stream().findFirst().get();
                treatments.add(treatment);
            }
        }

        if (treatments.size() == 0) {
            message.setType(MessageType.WARNING);
            message.setDescription("Records not found");
        }

        return treatments;
    }

/*
    public List<Treatment> getProviderTreatmentsList() {

        EntityManager<Provider> providerAgent = ManagerFactory.getManager(Provider.class);
        Map<String, Object> params = new TreeMap<>();
        int providerId = accountManager.getId();
        Provider provider = providerAgent.get(providerId);
        params.put("provider", provider);

        List<Treatment> treatments = treatmentAgent.getList()
                .stream()
                .filter(v -> state.equals(v.getStatus()))
                .filter(ps -> ps.getProviderSchedule()
                        .getProvider()
                        .equals(provider))
                .collect(Collectors.toList());

        if (treatments.size() == 0) {
            message.setType(MessageType.WARNING);
            message.setDescription("Records not found");
        }

        return treatments;
    }
*/

}
