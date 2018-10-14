package co.net.quiron.domain.account;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "UserRole")
@Table(name = "USERS_ROLES")
@Data
//@NoArgsConstructor
//@RequiredArgsConstructor
public class UserRole  {

    @EmbeddedId
    private UserRoleId id;

    /*
    @Id
    @ManyToOne
    @NonNull
    @JoinColumn(name = "UserName")
    private User user;

    @Id
    @ManyToOne
    @NonNull
    @JoinColumn(name = "RoleName")
    private Role role;
    */
/*
    private String roleName;

    private String userName;

    void setRoleName(String roleName) {
        this.roleName = role.getName();
    }

    void setUserName(String userName) {
        this.userName = user.getUsername();
    }
*/

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CreatedDate")
    private Date createdDate; //@CreationTimestamp annotation only works with Date or Calendar types

}
