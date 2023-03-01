package ma.tresor.jaasPlusEmail.appUser;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
class AppUserReposorityTest {

    @Autowired
    private AppUserReposority appUserReposority;

    @AfterEach
    void tearDown() {
        appUserReposority.deleteAll();
    }

    @Test
    public void checkIfEmailExist(){
        //given
        AppUser appUser = new AppUser(
                "tresor",
                "tresor",
                "tresor@gmail.com",
                "tresor2003."
        );
        appUserReposority.save(appUser);

        //when
        Optional<AppUser> byEmail = appUserReposority.findByEmail("tresor@gmail.com");
        boolean emailPresent = byEmail.isPresent();


        //then
        assertThat(emailPresent).isTrue();

    }

    @Test
    public void checkIfEmailNotExist(){
        //given
        String email = "tresor@gmail.com";
        //when
        Optional<AppUser> appUserEmail = appUserReposority.findByEmail(email);
        boolean present = appUserEmail.isPresent();
        //then
        assertThat(present).isFalse();


    }

}