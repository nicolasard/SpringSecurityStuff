package ar.nic.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateUser {
    @GetMapping("/create-user")
    public String securedPage() {
        return "CreateUser";
    }
}
