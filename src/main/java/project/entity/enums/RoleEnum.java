package project.entity.enums;

import org.springframework.security.core.GrantedAuthority;

/**
 * Enumeration of roles for security.
 */
public enum RoleEnum implements GrantedAuthority {
    USER("User"),
    ADMIN("Admin");

    private String role;

    RoleEnum(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + role.toUpperCase();
    }
}
