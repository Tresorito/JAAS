package ma.tresor.jaasPlusEmail.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest registration){
        return registrationService.register(registration);
    }

    @GetMapping(path = "confirm")
    public String confirmToken(@RequestParam(name = "token") String token){
        return registrationService.confirmToken(token);
    }
}
