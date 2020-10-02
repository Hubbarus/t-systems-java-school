package project.service.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.dto.CartDTO;
import project.dto.ItemDTO;
import project.dto.OrderDTO;
import project.entity.Address;
import project.entity.Client;
import project.entity.enums.RoleEnum;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public class EntityFactory {

    private static PasswordEncoder encoder = new BCryptPasswordEncoder();

    private static Address address1;
    private static Address address2;
    private static Address address3;
    private static Address address4;

    private static Client client1;
    private static Client client2;

    private static OrderDTO order1;
    private static OrderDTO order2;

    private static ItemDTO item1;
    private static ItemDTO item2;
    private static ItemDTO item3;
    private static ItemDTO item4;

    private static CartDTO cart1;
    private static CartDTO cart2;
    private static CartDTO cart3;
    private static CartDTO cart4;

    public static List<Client> getAllClients() {
        Address address1 = EntityFactory.getAddress1();
        Address address2 = EntityFactory.getAddress2();
        Address address3 = EntityFactory.getAddress3();
        Address address4 = EntityFactory.getAddress4();

        Set<Address> addresses1 = Set.of(address1, address2);
        Set<Address> addresses2 = Set.of(address3, address4);

        Client client1 = EntityFactory.getClient1();
        client1.setAddressList(addresses1);

        Client client2 = EntityFactory.getClient2();
        client2.setAddressList(addresses2);

        return List.of(client1, client2);
    }


    public static Address getAddress1() {
        address1 = new Address();
        address1.setPostcode("123456");
        address1.setCountry("Russia");
        address1.setCity("Spb");
        address1.setStreet("Test street 1");
        address1.setBuilding(1);
        address1.setApart(1);
        return address1;
    }

    public static Address getAddress2() {
        address2 = new Address();
        address2.setPostcode("234567");
        address2.setCity("Russia");
        address2.setCity("Spb");
        address2.setStreet("Test street 2");
        address2.setBuilding(1);
        address2.setApart(1);
        return address2;
    }

    public static Address getAddress3() {
        address3 = new Address();
        address3.setPostcode("234567");
        address3.setCity("Russia");
        address3.setCity("Spb");
        address3.setStreet("Test street 3");
        address3.setBuilding(1);
        address3.setApart(1);
        return address3;
    }

    public static Address getAddress4() {
        address4 = new Address();
        address4.setPostcode("234567");
        address4.setCity("Russia");
        address4.setCity("Spb");
        address4.setStreet("Test street 4");
        address4.setBuilding(1);
        address4.setApart(1);
        return address4;
    }

    public static Client getClient1() {
        client1 = new Client();
        client1.setFirstName("Test First Name 1");
        client1.setLastName("Test Last Name 1");
        client1.setEmail("test1@email.com");
        client1.setBirthDate(new Date(new java.util.Date().getTime()));
        client1.setId(1);
        client1.setActive(true);
        client1.setUserPass(encoder.encode("password"));
        client1.setRole(RoleEnum.USER);

        return client1;
    }

    public static Client getClient2() {
        client2 = new Client();
        client2.setFirstName("Test First Name 2");
        client2.setLastName("Test Last Name 2");
        client2.setEmail("test2@email.com");
        client2.setBirthDate(new Date(new java.util.Date().getTime()));
        client2.setId(2);
        client2.setActive(true);
        client2.setUserPass(encoder.encode("password"));
        client2.setRole(RoleEnum.USER);
        return client2;
    }

    public static OrderDTO getOrder1() {
        return order1;
    }

    public static OrderDTO getOrder2() {
        return order2;
    }

    public static ItemDTO getItem1() {
        return item1;
    }

    public static ItemDTO getItem2() {
        return item2;
    }

    public static ItemDTO getItem3() {
        return item3;
    }

    public static ItemDTO getItem4() {
        return item4;
    }

    public static CartDTO getCart1() {
        return cart1;
    }

    public static CartDTO getCart2() {
        return cart2;
    }

    public static CartDTO getCart3() {
        return cart3;
    }

    public static CartDTO getCart4() {
        return cart4;
    }
}
