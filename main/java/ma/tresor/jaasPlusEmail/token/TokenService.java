package ma.tresor.jaasPlusEmail.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class TokenService {

    private final TokenRepository tokenRepository;

    public void saveToken(ConfirmationToken token) {
        tokenRepository.save(token);
    }

    public Optional<ConfirmationToken> ckeckToken(String token){
       return tokenRepository.findByToken(token);
    }




}
