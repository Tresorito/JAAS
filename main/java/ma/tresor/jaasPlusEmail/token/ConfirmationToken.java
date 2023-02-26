package ma.tresor.jaasPlusEmail.token;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ma.tresor.jaasPlusEmail.appUser.AppUser;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class ConfirmationToken  {

    @SequenceGenerator(name = "ConfirmationToken_sequence",
            sequenceName = "ConfirmationToken_sequence")
    @GeneratedValue(generator = "ConfirmationToken_sequence",
            strategy = GenerationType.SEQUENCE)
    @Id
    private Long id;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime confirmAt;

    @ManyToOne
    @JoinColumn(nullable = false,
            name = "appUser_id")
    private AppUser appUser;

    public ConfirmationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiresAt,
                             LocalDateTime confirmAt,
                             AppUser appUser) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.confirmAt = confirmAt;
        this.appUser = appUser;
    }
}
