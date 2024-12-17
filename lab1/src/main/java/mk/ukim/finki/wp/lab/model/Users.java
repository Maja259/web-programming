package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.wp.lab.model.enums.Roles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "event_users")
public class Users implements UserDetails {

    @Id
    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    @Enumerated(value = EnumType.STRING)
    private Roles role;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<EventBooking> bookings;

    public Users(String username, String password, String firstName, String lastName, Roles role) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
