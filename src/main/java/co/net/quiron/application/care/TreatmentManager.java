package co.net.quiron.application.care;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.factory.RepositoryFactory;
import co.net.quiron.domain.care.Treatment;
import co.net.quiron.domain.care.Visit;
import co.net.quiron.domain.person.Patient;
import co.net.quiron.persistence.interfaces.IAppRepository;
import co.net.quiron.util.Message;
import co.net.quiron.util.MessageType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Data
public class TreatmentManager {
    AccountManager accountManager;
    Message message;
    IAppRepository<Visit> visitRepository;
    IAppRepository<Treatment> treatmentRepository;

    public TreatmentManager() {
        accountManager =  new AccountManager();
        message = new Message();
        visitRepository = RepositoryFactory.getDBContext(Visit.class);
        treatmentRepository = RepositoryFactory.getDBContext(Treatment.class);
    }

    public TreatmentManager(String username, String personType) {
        this();
        //ProfileManager profileManager = new ProfileManager();
        //profileManager.loadUserAccount(username, personType);
    }


    public List<Treatment> getPatientTreatmentsList() {

        IAppRepository<Patient> patientRepository = RepositoryFactory.getDBContext(Patient.class);
        Map<String, Object> params = new TreeMap<>();
        int patientId = accountManager.getId();
        Patient patient = patientRepository.get(patientId);
        params.put("patient", patient);

        List<Visit> visits = visitRepository.getListEquals(params);
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

        EntityManager<Provider> providerRepository = RepositoryFactory.getDBContext(Provider.class);
        Map<String, Object> params = new TreeMap<>();
        int providerId = accountManager.getId();
        Provider provider = providerRepository.get(providerId);
        params.put("provider", provider);

        List<Treatment> treatments = treatmentRepository.getList()
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
