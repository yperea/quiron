package co.net.quiron.domain.person;

import lombok.Data;

@Data
public class Profile  {

    private Person person;
    private Patient patient;
    private Address address;

}
