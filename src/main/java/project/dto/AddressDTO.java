package project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

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

    @Override
    public String toString() {
        return id + " | " + postcode + ", "
                + StringUtils.capitalize(country) + ", "
                + StringUtils.capitalize(city) + ", "
                + StringUtils.capitalize(street) + ", "
                + building + ", "
                + apart + ", ";

    }
}
