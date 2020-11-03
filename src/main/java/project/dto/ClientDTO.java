package project.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import project.entity.enums.RoleEnum;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * @author paulponomarev
 * Client DTO class.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDTO implements UserDetails, Serializable {
    private long id;
    @Size(min = 2, message = "Name length must be at least 2 characters")
    private String firstName;
    @Size(min = 2, message = "Last Name length must be at least 2 characters")
    private String lastName;
    @Size(min = 2, message = "Password must be at least 4 characters")
    private String userPass;
    private Date birthDate;
    @Email(message = "Incorrect e-mail address")
    private String email;
    private boolean active;
    private RoleEnum role;
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<AddressDTO> addressList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getPassword() {
        return userPass;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    }
}
