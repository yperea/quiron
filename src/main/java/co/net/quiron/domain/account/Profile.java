package co.net.quiron.domain.account;

import co.net.quiron.domain.institution.Organization;
import co.net.quiron.domain.location.Address;
import co.net.quiron.domain.person.Patient;
import co.net.quiron.domain.person.Person;
import co.net.quiron.domain.person.Provider;
import lombok.Data;

import java.time.LocalDate;

/**
 * This class represents the User profile for the application.
 *
 * @autor yperea
 */
@Data
public class Profile  {

    private Patient patient;
    private Provider provider;
    private Person person;
    private Address address;

    private String firstName;
    private String lastName;
    private String personType;
    private LocalDate birthDate;
    private String gender;
    private String npi;
    private String subscriberCode;
    private Organization organization;
}
