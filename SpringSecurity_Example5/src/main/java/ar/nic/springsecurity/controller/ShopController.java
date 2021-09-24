package ar.nic.springsecurity.controller;

import ar.nic.springsecurity.entity.Bill;
import ar.nic.springsecurity.entity.CardPayment;
import ar.nic.springsecurity.entity.CurrencyType;
import ar.nic.springsecurity.entity.Payment;
import ar.nic.springsecurity.services.fiserv.PaymentPostback;
import ar.nic.springsecurity.services.BillingService;
import ar.nic.springsecurity.services.fiserv.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
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
        ar.nic.springsecurity.entity.Payment payment = new ar.nic.springsecurity.entity.Payment();
        payment.setPaymentType(ar.nic.springsecurity.entity.Payment.PaymentType.FISERV_CREDITCARD);
        payment.setBill(bill);
        UUID uuid = UUID.randomUUID();
        payment.setAcquirerID(uuid.toString());
        payment.setStatus(ar.nic.springsecurity.entity.Payment.PaymentStatus.CREATED);
        billingService.savePayment(payment);
        modelAndView.addObject("bill",bill);
        CardPayment cardPayment = new CardPayment();
        modelAndView.addObject("payment",cardPayment);
        modelAndView.setViewName("shop/card-payment");
        return modelAndView;
    }

    @PostMapping(path = "/fiserv/card-payment/{id}")
    String cardPaymentPost(Payment payment, @PathVariable Long id,@RequestHeader String host, BindingResult bindingResult) throws IllegalAccessException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        if (bindingResult.hasErrors()){
            return "shop/card-payment";
        }
        ar.nic.springsecurity.services.fiserv.Payment fiservPayment = new ar.nic.springsecurity.services.fiserv.Payment();
        Bill bill = billingService.getById(id).get();
        UUID uuid = UUID.randomUUID();
        DateFormat df = new SimpleDateFormat("yyyy:MM:dd-kk:mm:ss");
        fiservPayment.setChargetotal(bill.getTotal());
        fiservPayment.setTxntype("sale");
        fiservPayment.setOid(uuid.toString());
        fiservPayment.setCurrency("EUR");
        fiservPayment.setTimezone(TimeZone.getDefault().getID());
        fiservPayment.setTxndatetime(df.format(new Date()));
        fiservPayment.setStorename("120995000");
        fiservPayment.setHash_algorithm("HMACSHA256");
        fiservPayment.setResponseSuccessURL("http://"+host+"/shop/fiserv/card-payment/postback");
        fiservPayment.setResponseFailURL("http://"+host+"/shop/fiserv/card-payment/postback");
        fiservPayment.setTransactionNotificationURL("http://"+host+"/shop/fiserv/card-payment/webhook");
        fiservPayment.setHashExtended(paymentService.getHMAC256(fiservPayment));
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
