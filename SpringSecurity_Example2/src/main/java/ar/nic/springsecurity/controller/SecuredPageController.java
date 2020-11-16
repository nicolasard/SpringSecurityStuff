package ar.nic.springsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecuredPageController {

    @GetMapping("/")
    public String securedPage() {
        return "SecuredPage";
    }

}
