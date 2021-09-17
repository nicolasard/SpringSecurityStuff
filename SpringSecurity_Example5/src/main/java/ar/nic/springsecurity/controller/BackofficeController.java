package ar.nic.springsecurity.controller;

import ar.nic.springsecurity.entity.Bill;
import ar.nic.springsecurity.entity.User;
import javafx.scene.canvas.GraphicsContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("backoffice")
public class BackofficeController {
    @GetMapping("add-bill")
    ModelAndView bills(ModelAndView modelAndView, Bill bill) {
        modelAndView.setViewName("backoffice/add-bill");
        return modelAndView;
    }

    @PostMapping("add-bill")
    ModelAndView bills(ModelAndView modelAndView,@Valid Bill bill, BindingResult bindingResult) {
        modelAndView.setViewName("backoffice/add-bill");
        return modelAndView;
    }
}
