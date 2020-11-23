package ar.nic.springsecurity.controller;

import ar.nic.springsecurity.entity.User;
import ar.nic.springsecurity.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/sign-in")
    String signIn() {

        return "sign-in2";
    }

    @GetMapping("/sign-up")
    String signUpPage(User user) {

        return "sign-up2";
    }

    @PostMapping("/sign-up")
    String signUp(User user) {

        userService.signUpUser(user);

        return "redirect:/sign-in2";
    }
}
