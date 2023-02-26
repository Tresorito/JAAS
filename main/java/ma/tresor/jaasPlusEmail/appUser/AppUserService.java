package ma.tresor.jaasPlusEmail.appUser;

import lombok.AllArgsConstructor;
import ma.tresor.jaasPlusEmail.token.ConfirmationToken;
import ma.tresor.jaasPlusEmail.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final AppUserReposority appUserReposority;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserReposority.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("email not found"));
    }

    @Transactional
    public String signUp(AppUser appUser){
        boolean present = appUserReposority.findByEmail(appUser.getEmail()).isPresent();

        if (present) {
            if (!appUser.isEnable()){

                String token = UUID.randomUUID().toString() ;
                ConfirmationToken confirmationToken = new ConfirmationToken(
                        token,
                        LocalDateTime.now(),
                        LocalDateTime.now().plusMinutes(11),
                        null,
                        appUser
                );
                tokenService.saveToken(confirmationToken);
                return token ;
            }
            else {
                throw new IllegalStateException("Email already exists");
            }


        }

        String encodePass = passwordEncoder.encode(appUser.getPassword());
        appUser.setPassword(encodePass);
        appUserReposority.save(appUser);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(11),
                null,
                appUser
        );

        tokenService.saveToken(confirmationToken);

        return token;
    }

    public Optional<AppUser> findUser(String email){
        return appUserReposority.findByEmail(email);
    }

}
