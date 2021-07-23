package ar.nic.springsecurity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecuredPageController {

    @GetMapping("/")
    public String securedPage() {
        return "SecuredPage";
    }

    // Provides info about the current logged user
    @GetMapping("/myUser")
    public String users(Authentication authentication) {
        return authentication.toString();
    }

    @GetMapping("/con2")
    String confirmation() {
        return "confirmation";
    }
}
