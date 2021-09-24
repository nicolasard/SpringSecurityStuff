package ar.nic.springsecurity.controller;

import ar.nic.springsecurity.entity.ConfirmationToken;
import ar.nic.springsecurity.entity.User;
import ar.nic.springsecurity.services.ConfirmationTokenService;
import ar.nic.springsecurity.services.UserService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserController {

	@Autowired
    private UserService userService;

    private ConfirmationTokenService confirmationTokenService;

    @GetMapping("/sign-in")
    String signIn() {
        return "sign-in";
    }

    @GetMapping("/sign-up")
    String signUpPage(User user) {
        return "sign-up";
    }

    @GetMapping("/confirmation")
    String confirmation() {
        return "confirmation";
    }

    @PostMapping("/sign-up")
    String signUp(@Valid User user, BindingResult bindingResult) {
        boolean hasErrors = false;
        if (userService.userAlreadyExists(user.getEmail())) {
            bindingResult.addError(new FieldError("user", "email", "Email already exists"));
            hasErrors = true;
        }
        if (bindingResult.hasErrors() || hasErrors) {
            return "sign-up";
        }
        userService.signUpUser(user);
        return "redirect:/confirmation";
    }

    @GetMapping("/sign-up/confirm")
    String confirmMail(@RequestParam("token") String token) {
        Optional<ConfirmationToken> optionalConfirmationToken = confirmationTokenService.findConfirmationTokenByToken(token);
        optionalConfirmationToken.ifPresent(userService::confirmUser);
        return "redirect:/sign-in";
    }
}
