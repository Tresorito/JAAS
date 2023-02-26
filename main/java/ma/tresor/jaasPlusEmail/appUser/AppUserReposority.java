package ma.tresor.jaasPlusEmail.appUser;

import ma.tresor.jaasPlusEmail.appUser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserReposority extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);
}
