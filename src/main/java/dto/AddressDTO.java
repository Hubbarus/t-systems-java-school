package dto;

import java.util.Objects;

public class AddressDTO {
    private long id;
    private String country;
    private String city;
    private int postcode;
    private String street;
    private int building;
    private int flat;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuilding() {
        return building;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    public int getFlat() {
        return flat;
    }

    public void setFlat(int flat) {
        this.flat = flat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressDTO)) return false;
        AddressDTO that = (AddressDTO) o;
        return getId() == that.getId() &&
                getPostcode() == that.getPostcode() &&
                getBuilding() == that.getBuilding() &&
                getFlat() == that.getFlat() &&
                Objects.equals(getCountry(), that.getCountry()) &&
                Objects.equals(getCity(), that.getCity()) &&
                Objects.equals(getStreet(), that.getStreet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCountry(), getCity(), getPostcode(), getStreet(), getBuilding(), getFlat());
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", postcode=" + postcode +
                ", street='" + street + '\'' +
                ", building=" + building +
                ", flat=" + flat +
                '}';
    }
}
