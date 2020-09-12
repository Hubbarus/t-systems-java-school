package project.dto;

import project.entity.Orders;

import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

public class ClientDTO {
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String email;
    private String username;
    private String userPass;
    private Collection<Orders> orders;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public Collection<Orders> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientDTO)) return false;
        ClientDTO clientDTO = (ClientDTO) o;
        return Objects.equals(getFirstName(), clientDTO.getFirstName()) &&
                Objects.equals(getLastName(), clientDTO.getLastName()) &&
                Objects.equals(getBirthDate(), clientDTO.getBirthDate()) &&
                Objects.equals(getEmail(), clientDTO.getEmail()) &&
                Objects.equals(getUsername(), clientDTO.getUsername()) &&
                Objects.equals(getUserPass(), clientDTO.getUserPass()) &&
                Objects.equals(getOrders(), clientDTO.getOrders());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getBirthDate(), getEmail(), getUsername(), getUserPass(), getOrders());
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", userPass='" + userPass + '\'' +
                ", orders=" + orders +
                '}';
    }
}
