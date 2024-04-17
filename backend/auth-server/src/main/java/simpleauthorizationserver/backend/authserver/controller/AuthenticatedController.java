package simpleauthorizationserver.backend.authserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticatedController {

    @GetMapping("/authenticated")
    public String authenticated() {
        return "Authentication is Successful!";
    }
}
