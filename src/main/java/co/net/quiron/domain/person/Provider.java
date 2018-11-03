package co.net.quiron.domain.person;


import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "Provider")
@Table(name = "PROVIDERS")
@PrimaryKeyJoinColumn(name = "ProviderID")
public class Provider extends Person {

    @NonNull
    @Column(name = "NPI")
    private String npi;

    public Provider(PersonType personType, String firstName, String lastName, String npi) {
        super(personType, firstName, lastName);
        this.npi= npi;
    }

}
