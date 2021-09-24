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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import javax.validation.Valid;

@Controller
@RequestMapping("backoffice")
public class BackofficeController {
		
	@Autowired
	BillingService billingService;
	
	@GetMapping({"/"})
    ModelAndView bills(ModelAndView modelAndView) {
        Iterable<Bill> bills;
        bills = billingService.list();
        modelAndView.addObject("bills",bills);
        modelAndView.setViewName("backoffice/list-bills");
        return modelAndView;
    }
	
	@GetMapping({"bill","bill/{id}"})
    ModelAndView bill(ModelAndView modelAndView, @PathVariable(name = "id", required = false) Long id) {
    	Bill bill = new Bill();
    	if (id!=null) {
    		Optional<Bill> billAtDB = billingService.getById(id);		
        	if (billAtDB.isPresent()) {
        		bill = billAtDB.get();
        	}
    	}
    	modelAndView.addObject(bill);
        modelAndView.setViewName("backoffice/add-bill");
        return modelAndView;
    }

    @PostMapping("bill")
    ModelAndView bill(ModelAndView modelAndView,@Valid Bill bill, BindingResult bindingResult) {
        modelAndView.setViewName("backoffice/add-bill");
        if (bindingResult.hasErrors()) {
        	modelAndView.addObject(bindingResult.getModel());
        	return modelAndView;
        }
        modelAndView.addObject(bill);
        billingService.save(bill);
        return modelAndView;
    }

}
