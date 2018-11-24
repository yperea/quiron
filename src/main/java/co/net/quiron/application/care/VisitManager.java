package co.net.quiron.application.care;

import co.net.quiron.application.account.AccountManager;
import co.net.quiron.application.factory.ManagerFactory;
import co.net.quiron.application.shared.EntityManager;
import co.net.quiron.domain.care.Visit;
import co.net.quiron.domain.person.Patient;
import co.net.quiron.domain.person.Provider;
import co.net.quiron.util.Message;
import co.net.quiron.util.MessageType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Data
//@NoArgsConstructor
public class VisitManager {

    AccountManager accountManager;
    Message message;
    EntityManager<Visit> visitAgent;

    public VisitManager() {
        accountManager =  new AccountManager();
        message = new Message();
        visitAgent = ManagerFactory.getManager(Visit.class);
    }

    public VisitManager(String username, String personType) {
        this();
        accountManager.loadUserAccount(username, personType);
    }


    public List<Visit> getPatientVisitsList(String state) {

        EntityManager<Patient> patientAgent = ManagerFactory.getManager(Patient.class);
        Map<String, Object> params = new TreeMap<>();
        int patientId = accountManager.getId();
        Patient patient = patientAgent.get(patientId);
        params.put("patient", patient);

        List<Visit> visits = visitAgent.getListEquals(params)
                .stream()
                .filter(v -> state.equals(v.getStatus()))
                .collect(Collectors.toList());

        if (visits.size() == 0) {
            message.setType(MessageType.WARNING);
            message.setDescription("Records not found");
        }

        return visits;
    }

    public List<Visit> getProviderVisitsList(String state) {

        EntityManager<Provider> providerAgent = ManagerFactory.getManager(Provider.class);
        Map<String, Object> params = new TreeMap<>();
        int providerId = accountManager.getId();
        Provider provider = providerAgent.get(providerId);
        params.put("provider", provider);

        List<Visit> visits = visitAgent.getList()
                .stream()
                .filter(v -> state.equals(v.getStatus()))
                .filter(ps -> ps.getProviderSchedule()
                        .getProvider()
                        .equals(provider))
                .collect(Collectors.toList());

        if (visits.size() == 0) {
            message.setType(MessageType.WARNING);
            message.setDescription("Records not found");
        }

        return visits;
    }

}
