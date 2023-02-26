package ma.tresor.jaasPlusEmail.appUser;

import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@NoArgsConstructor
public class AppUser implements UserDetails {

    @SequenceGenerator(name = "appUser_sequence",
                       sequenceName = "appUser_sequence")
    @GeneratedValue(generator = "appUser_sequence",
            strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private AppUserRole userRole = AppUserRole.USER;

    @Type(type="true_false")
    private boolean isAccountNonLocked = true;

    @Type(type="true_false")
    private boolean enable = false;

    public AppUser(String firstName,
                   String lastName,
                   String email,
                   String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
         SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
         return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
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
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AppUserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(AppUserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean isEnabled() {
        return isEnable();
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
