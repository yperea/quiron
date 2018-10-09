package co.net.quiron.domain.admin;

import lombok.*;
import lombok.experimental.Accessors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * This class represents the Country domain for the application.
 *
 * @autor yperea
 */
@Entity(name = "Country")
@Table(name = "COUNTRIES")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Country {

    @Id
    @Column(name = "CountryID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @NonNull
    @Column(name = "CountryCode")
    private String code;

    @NonNull
    @Column(name = "Name")
    private String name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<State> states = new HashSet<>();

    /* Using Database Default value with a LocalDate type for createdDate
    @CreationTimestamp
    @Column(name = "CreatedDate", insertable = false)
    private LocalDate createdDate;
    */
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedDate")
    private Date createdDate; //@CreationTimestamp annotation only works with Date or Calendar types

    /*
    @Column(name = "ModifiedDate")
    private LocalDate modifiedDate;
    */
    @UpdateTimestamp
    @EqualsAndHashCode.Exclude
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ModifiedDate")
    private Date modifiedDate;


    /**
     * Add state to the collection within the Country.
     *
     * @param state the state
     */
    public void addState(State state) {
        states.add(state);
        state.setCountry(this);

        //logger.trace("addState(State): Adding the State to the collection.");
    }

    /**
     * Remove state from the collection.
     *
     * @param state the state
     */
    public void removeState(State state) {
        states.remove(state);
        state.setCountry(null);

        //logger.trace("removeState(State): Removing the State from the collection.");
    }
}

