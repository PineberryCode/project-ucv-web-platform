package project.projectucvwebsystem.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import project.projectucvwebsystem.util.Role;

@Entity
@Table(name = "USER_TABLE")
@Getter
@Setter
public class User implements UserDetails { //User == Employee

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USER")
    private Long id_user;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role user_role;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private boolean enabled;

    public User () {}
    public User (String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorities = user_role.getPermissions().stream()
        .map(permissions -> new SimpleGrantedAuthority(permissions.name()))
        .collect(Collectors.toList());

        /*Role of the User*/
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+user_role.name()));

        return grantedAuthorities;
    }

    @Override
    public String getPassword() {return password;}

    @Override
    public String getUsername() {return username;}

    @Override
    public boolean isAccountNonExpired() {return true;}

    @Override
    public boolean isAccountNonLocked() {return true;}

    @Override
    public boolean isCredentialsNonExpired() {return true;}

    @Override
    public boolean isEnabled() {return enabled;}
    
}
