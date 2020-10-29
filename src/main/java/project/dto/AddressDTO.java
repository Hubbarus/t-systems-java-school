package project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO implements Serializable {
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
