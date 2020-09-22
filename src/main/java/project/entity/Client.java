package project.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import project.entity.enums.RoleEnum;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.sql.Date;
import java.util.Set;

@Entity
@Data
public class Client  implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    @Column(name = "user_pass", nullable = false)
    private String userPass;
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;
    @Column(name = "email", nullable = false)
    private String email;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "client_address",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Set<Address> addressList;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleEnum role;
}
