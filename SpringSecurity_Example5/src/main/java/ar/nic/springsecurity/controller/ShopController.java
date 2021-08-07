package ar.nic.springsecurity.controller;

import ar.nic.springsecurity.entity.Bill;
import ar.nic.springsecurity.services.fiserv.Payment;
import ar.nic.springsecurity.entity.PaymentPostback;
import ar.nic.springsecurity.services.BillingService;
import ar.nic.springsecurity.services.fiserv.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("shop")
public class ShopController {

    @Autowired
    BillingService billingService;

    @Autowired
    PaymentService paymentService;

    @GetMapping("billing")
    ModelAndView bills(ModelAndView modelAndView) {
        Bill bill = new Bill();
        bill.setBillNumber("F000-00001");
        bill.setDescription("Energy Company - January charges");
        bill.setCurrency("EUR");
        bill.setTotal("100.20");
        billingService.save(bill);
        Iterable<Bill> bills;
        bills = billingService.list();
        modelAndView.addObject("bills",bills);
        modelAndView.setViewName("shop/billing");
        return modelAndView;
    }

    @GetMapping("product")
    String product() {
        return "product";
    }

    @GetMapping("/fiserv/card-payment/{id}")
    ModelAndView cardPaymentGet(ModelAndView modelAndView, @PathVariable Long id) {
        Bill bill = billingService.getById(id).get();
        Payment payment = new Payment();
        modelAndView.addObject("bill",bill);
        modelAndView.addObject("payment",payment);
        modelAndView.setViewName("shop/card-payment");
        return modelAndView;
    }

    @PostMapping(path = "/fiserv/card-payment/{id}")
    String cardPaymentPost(Payment payment, @PathVariable Long id,@RequestHeader String host) throws IllegalAccessException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
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
        payment.setResponseSuccessURL("http://"+host+"/shop/fiserv/card-payment/postback");
        payment.setResponseFailURL("http://"+host+"/shop/fiserv/card-payment/postback");
        payment.setTransactionNotificationURL("http://"+host+"/shop/fiserv/card-payment/webhook");
        payment.setHashExtended(paymentService.getHMAC256(payment));
        return "shop/card-payment-confirm";
    }

    @PostMapping(path = "/fiserv/card-payment/postback")
    String cardPaymentPostback(Model model, @RequestParam MultiValueMap<String,String> paramMap)  {
        PaymentPostback paymentPostback = new PaymentPostback();
        paymentPostback.setStatus(paramMap.getFirst("status"));
        model.addAttribute("paymentPostBack",paymentPostback);
        return "shop/card-payment-postback";
    }

    @PostMapping(path = "/fiserv/card-payment/webhook")
    String cardPaymentPostback(@RequestParam MultiValueMap<String,String> paramMap)  {
        PaymentPostback paymentPostback = new PaymentPostback();
        paymentPostback.setStatus(paramMap.getFirst("status"));
        return "shop/card-payment-postback";
    }
}
