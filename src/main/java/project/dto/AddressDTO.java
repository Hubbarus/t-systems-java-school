package project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private long id;
    private String country;
    private String city;
    private String postcode;
    private String street;
    private int building;
    private int apart;
}
