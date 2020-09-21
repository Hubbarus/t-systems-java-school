package project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.entity.enums.RoleEnum;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private String userPass;
    private Date birthDate;
    private String email;
    private RoleEnum role;
    private List<AddressDTO> addressList;
}
