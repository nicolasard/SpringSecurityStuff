package ar.nic.springsecurity.controller;

import ar.nic.springsecurity.entity.ConfirmationToken;
import ar.nic.springsecurity.entity.User;
import ar.nic.springsecurity.services.ConfirmationTokenService;
import ar.nic.springsecurity.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;

    private ConfirmationTokenService confirmationTokenService;

    @GetMapping("/sign-in")
    String signIn() {

        return "sign-in2";
    }

    @GetMapping("/sign-up")
    String signUpPage(User user) {

        return "sign-up2";
    }

    @PostMapping("/sign-up")
    String signUp(@Valid User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "sign-up2";
        }
        userService.signUpUser(user);

        return "redirect:/sign-in2";
    }

    @GetMapping("/sign-up/confirm")
    String confirmMail(@RequestParam("token") String token) {

        Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);

        optionalConfirmationToken.ifPresent(userService::confirmUser);

        return "redirect:/sign-in";
    }
}
