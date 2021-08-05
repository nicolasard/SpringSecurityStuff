package ar.nic.springsecurity.controller;

import ar.nic.springsecurity.entity.Bill;
import ar.nic.springsecurity.entity.Payment;
import ar.nic.springsecurity.entity.PaymentPostback;
import ar.nic.springsecurity.services.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("shop")
public class ShopController {

    @Autowired
    BillingService billingService;

    @GetMapping("billing")
    ModelAndView bills(ModelAndView modelAndView) {
        Bill bill = new Bill();
        bill.setBillNumber("F000-00001");
        bill.setDescription("Energy Company - January charges");
        bill.setCurrency("ARS");
        bill.setTotal("100.20");
        billingService.save(bill);
        Iterable<Bill> bills;
        bills = billingService.list();
        modelAndView.addObject("bills",bills);
        modelAndView.setViewName("billing");
        return modelAndView;
    }

    @GetMapping("product")
    String product() {
        return "product";
    }

    @GetMapping("card-payment/{id}")
    ModelAndView cardPaymentGet(ModelAndView modelAndView, @PathVariable Long id) {
        Bill bill = billingService.getById(id).get();
        Payment payment = new Payment();
        modelAndView.addObject("bill",bill);
        modelAndView.addObject("payment",payment);
        modelAndView.setViewName("card-payment");
        return modelAndView;
    }

    @PostMapping("card-payment/{id}")
    String cardPaymentPost(Payment payment, @PathVariable Long id,@RequestHeader String host) throws IllegalAccessException {
        Bill bill = billingService.getById(id).get();
        UUID uuid = UUID.randomUUID();
        DateFormat df = new SimpleDateFormat("yyyy:MM:dd-kk:mm:ss");
        payment.setChargetotal(bill.getTotal());
        payment.setTxntype("sale");
        payment.setOid(uuid.toString());
        payment.setCurrency("EUR");
        payment.setTimezone(TimeZone.getDefault().getID());
        payment.setTxndatetime(df.format(new Date()));
        payment.setStorename("120995000");
        payment.setHash_algorithm("HMACSHA256");
        payment.setResponseSuccessURL("http://"+host+"/shop/card-payment/postback");
        payment.setResponseFailURL("http://"+host+"/shop/card-payment/postback");
        payment.setHashExtended(payment.getHash("sharedsecret"));
        return "card-payment-confirm";
    }

    @PostMapping(path = "/card-payment/postback")
    String cardPaymentPostback(Model model, @RequestParam MultiValueMap<String,String> paramMap)  {
        PaymentPostback paymentPostback = new PaymentPostback();
        paymentPostback.setStatus(paramMap.getFirst("status"));
        model.addAttribute("paymentPostBack",paymentPostback);
        return "card-payment-postback";
    }
}
