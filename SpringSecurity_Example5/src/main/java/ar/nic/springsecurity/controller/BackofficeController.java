package ar.nic.springsecurity.controller;

import ar.nic.springsecurity.entity.Bill;
import ar.nic.springsecurity.entity.Bill.Status;
import ar.nic.springsecurity.services.BillingService;
import ar.nic.springsecurity.services.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
		
	@Autowired
	BillingService billingService;
	
    @GetMapping("add-bill")
    ModelAndView bills(ModelAndView modelAndView, Bill bill) {
        modelAndView.setViewName("backoffice/add-bill");
        return modelAndView;
    }

    @PostMapping("add-bill")
    ModelAndView bills(ModelAndView modelAndView,@Valid Bill bill, BindingResult bindingResult) {
        modelAndView.setViewName("backoffice/add-bill");
        billingService.save(bill);
        return modelAndView;
    }
}
