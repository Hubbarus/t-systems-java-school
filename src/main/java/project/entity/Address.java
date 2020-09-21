package project.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Address implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "postcode", nullable = false)
    private String postcode;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "building", nullable = false)
    private int building;
    @Column(name = "apart", nullable = false)
    private int apart;
    @ManyToMany(mappedBy = "addressList")
    private List<Client> clientList;
}
