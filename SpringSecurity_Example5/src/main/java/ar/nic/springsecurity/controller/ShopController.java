package ar.nic.springsecurity.controller;

import ar.nic.springsecurity.entity.Payment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.xml.transform.stream.StreamResult;
import java.io.PrintWriter;
import java.util.Enumeration;

@Controller
@RequestMapping("shop")
public class ShopController {

    @GetMapping("product")
    String product() {
        return "product";
    }

    @GetMapping("card-payment")
    String cardPaymentGet(Payment payment) {
        payment.setTxntype("preauth");
        return "card-payment";
    }

    @PostMapping("card-payment")
    String cardPaymentPost(Payment payment, HttpServletRequest request) {
        payment.setCurrency("999");

        Enumeration e = request.getParameterNames();
        while (e.hasMoreElements()) {
            String name = (String)e.nextElement();
            String value = request.getParameter(name);
            System.out.println(name + " = " + value);
        }

        request.setAttribute(
                View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
        return "redirect:https://webhook.site/4533b71e-9286-44dc-aa03-b925e83ce57f";
    }
}
