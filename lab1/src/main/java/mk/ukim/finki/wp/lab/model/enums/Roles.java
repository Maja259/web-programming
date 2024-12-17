package mk.ukim.finki.wp.lab.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Roles  implements GrantedAuthority {
    ROLES_USER,
    ROLES_ADMIN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
