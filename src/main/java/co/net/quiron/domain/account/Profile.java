package co.net.quiron.domain.account;

import co.net.quiron.domain.location.Address;
import co.net.quiron.domain.person.Patient;
import co.net.quiron.domain.person.Person;
import co.net.quiron.domain.person.Provider;
import lombok.Data;

@Data
public class Profile  {

    private Patient patient;
    private Provider provider;
    private Person person;
    private Address address;
}
