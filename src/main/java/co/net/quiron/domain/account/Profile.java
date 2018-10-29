package co.net.quiron.domain.account;

import co.net.quiron.domain.location.Address;
import co.net.quiron.domain.person.Patient;
import co.net.quiron.domain.person.Person;
import lombok.Data;

@Data
public class Profile  {

    private Patient patient;
    private Address address;

}
