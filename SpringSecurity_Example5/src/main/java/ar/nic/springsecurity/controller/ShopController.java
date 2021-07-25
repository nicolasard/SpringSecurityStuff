package ar.nic.springsecurity.controller;

import ar.nic.springsecurity.entity.Payment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

@Controller
@RequestMapping("shop")
public class ShopController {

    @GetMapping("billing")
    String bills() {
        return "billing";
    }

    @GetMapping("product")
    String product() {
        return "product";
    }

    @GetMapping("card-payment")
    String cardPaymentGet(Payment payment) {
        return "card-payment";
    }

    @PostMapping("card-payment")
    String cardPaymentPost(Payment payment) {
        UUID uuid = UUID.randomUUID();
        DateFormat df = new SimpleDateFormat("yyyy:MM:dd-kk:mm:ss");
        payment.setChargetotal("13.99");
        payment.setTxntpe("sale");
        payment.setOid(uuid.toString());
        payment.setCurrency("EUR");
        payment.setTimezone(TimeZone.getDefault().getID());
        payment.setTxdatetime(df.format(new Date()));
        payment.setStorename("120995000");
        payment.setHashExtended(payment.getHash("sharedsecret"));
        payment.setHash_algorithm("HMACSHA256");
        return "card-payment-confirm";
    }
}
