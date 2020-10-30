package project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO implements Serializable {
    private long id;
    @Size(min = 1, message = "Country: Minimum length is 1 character")
    private String country;
    @Size(min = 1, message = "City: Minimum length is 1 character")
    private String city;
    @Size(min = 1, message = "Postcode: Minimum length is 1 character")
    @Min(value = 1, message = "Postcode: Minimum length is 1 character")
    @Digits(integer = 8, fraction = 0, message = "Postcode should be digit")
    private String postcode;
    @Size(min = 1, message = "Street: Minimum length is 1 character")
    private String street;
    @Digits(integer = 8, fraction = 0, message = "Building number should be digit")
    @Min(value = 1, message = "Building: Minimum value is 1")
    private int building;
    @Digits(integer = 8, fraction = 0, message = "Apart number should be digit")
    @Min(value = 1, message = "Apart: Minimum value is 1")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AddressDTO)) {
            return false;
        }
        AddressDTO that = (AddressDTO) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
