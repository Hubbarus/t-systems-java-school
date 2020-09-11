package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity
public class Addresses {
    private long id;
    private String country;
    private String city;
    private int postcode;
    private String street;
    private int building;
    private int flat;
    private Collection<Orders> ordersById;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "country", nullable = false, length = -1)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "city", nullable = false, length = -1)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "postcode", nullable = false)
    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    @Basic
    @Column(name = "street", nullable = false, length = -1)
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Basic
    @Column(name = "building", nullable = false)
    public int getBuilding() {
        return building;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    @Basic
    @Column(name = "flat", nullable = false)
    public int getFlat() {
        return flat;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Addresses addresses = (Addresses) o;

        if (id != addresses.id) return false;
        if (postcode != addresses.postcode) return false;
        if (building != addresses.building) return false;
        if (flat != addresses.flat) return false;
        if (country != null ? !country.equals(addresses.country) : addresses.country != null) return false;
        if (city != null ? !city.equals(addresses.city) : addresses.city != null) return false;
        if (street != null ? !street.equals(addresses.street) : addresses.street != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + postcode;
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + building;
        result = 31 * result + flat;
        return result;
    }

    @Override
    public String toString() {
        return "Address {" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", postcode=" + postcode +
                ", street='" + street + '\'' +
                ", building=" + building +
                ", flat=" + flat +
                '}';
    }

    @OneToMany(mappedBy = "address")
    public Collection<Orders> getOrdersById() {
        return ordersById;
    }

    public void setOrdersById(Collection<Orders> ordersById) {
        this.ordersById = ordersById;
    }
}
