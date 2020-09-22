package project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.entity.enums.RoleEnum;

import java.sql.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String userPass;
    private Date birthDate;
    private String email;
    private boolean active;
    private RoleEnum role;
    private Set<AddressDTO> addressList;
}
