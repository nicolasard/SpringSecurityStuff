package ar.nic.springsecurity.controller;

import ar.nic.springsecurity.entity.Bill;
import javafx.scene.canvas.GraphicsContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("backoffice")
public class BackofficeController {
    @GetMapping("add-bill")
    ModelAndView bills(ModelAndView modelAndView) {
        Bill bill = new Bill();
        modelAndView.setViewName("backoffice/add-bill");
        return modelAndView;
    }
}
