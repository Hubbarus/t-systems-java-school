package project.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum RoleEnum implements GrantedAuthority {
    USER("User"),
    ADMIN("Admin"),
    ANON("Anon");

    private String role;

    RoleEnum(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + role.toUpperCase();
    }
}
