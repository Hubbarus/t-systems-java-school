package project.dto;

import lombok.Data;

@Data
public class AddressDTO {
    private long id;
    private String country;
    private String city;
    private String postcode;
    private String street;
    private int building;
    private int apart;
}
