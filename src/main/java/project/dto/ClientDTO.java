package project.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ClientDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String username;
    private String userPass;
    private Date birthDate;
    private String email;
}
