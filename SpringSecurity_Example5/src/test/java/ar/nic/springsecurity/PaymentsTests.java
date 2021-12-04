package ar.nic.springsecurity;

import ar.nic.springsecurity.services.fiserv.Payment;
import ar.nic.springsecurity.services.fiserv.PaymentService;
import org.junit.Assert;
import org.junit.Test;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

public class PaymentsTests {

    @Test
    public void TestIntegrationBookHash() throws IllegalAccessException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        Payment payment = new Payment();
        payment.setChargetotal("13.00");
        payment.setSharedsecret("sharedsecret");
        payment.setCurrency("978");
        payment.setPaymentMethod("M");
        payment.setResponseFailURL("https://localhost:8643/webshop/response_failure.jsp");
        payment.setResponseSuccessURL("https://localhost:8643/webshop/response_success.jsp");
        payment.setStorename("10123456789");
        payment.setTimezone("Europe/Berlin");
        payment.setTransactionNotificationURL("https://localhost:8643/webshop/transactionNotification");
        payment.setTxndatetime("2020:04:17-17:32:41");
        payment.setTxntype("sale");
        String expectedResult = "13.00|978|M|https://localhost:8643/webshop/response_failure.jsp|https://localhost:8643/webshop/response_success.jsp|10123456789|Europe/Berlin|https://localhost:8643/webshop/transactionNotification|2020:04:17-17:32:41|sale";
        String expectedHash = "8CVD62a88mwr/Nfc+t+CWB+XG0g5cqmSrN8JhFlQJVM=";
        PaymentService paymentService = new PaymentService();
        Assert.assertEquals(expectedResult,paymentService.getExtendedConcat(payment));
        Assert.assertEquals(expectedHash,paymentService.getHMAC256(payment));
    }

    @Test
    public void TestPaymenyHMacSha256() throws IllegalAccessException, NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        Payment payment = new Payment();
        payment.setChargetotal("13.00");
        payment.setSharedsecret("sharedsecret");
        payment.setCurrency("978");
        payment.setPaymentMethod("M");
        payment.setResponseFailURL("https://localhost:8643/webshop/response_failure.jsp");
        payment.setResponseSuccessURL("https://localhost:8643/webshop/response_success.jsp");
        payment.setStorename("10123456789");
        payment.setTimezone("Europe/Berlin");
        payment.setTransactionNotificationURL("https://localhost:8643/webshop/transactionNotification");
        payment.setTxndatetime("2020:04:17-17:32:41");
        payment.setTxntype("sale");
        payment.setHash_algorithm("HMACSHA256");

        String expectedResult = "13.00|978|HMACSHA256|M|https://localhost:8643/webshop/response_failure.jsp|https://localhost:8643/webshop/response_success.jsp|10123456789|Europe/Berlin|https://localhost:8643/webshop/transactionNotification|2020:04:17-17:32:41|sale";
        String expectedHash = "nI+XkhDBx27D31gte3b1TOfNlGhXiyf7j+JZ/bQwxsM=";
        PaymentService paymentService = new PaymentService();
        Assert.assertEquals(expectedResult,paymentService.getExtendedConcat(payment));
        Assert.assertEquals(expectedHash,paymentService.getHMAC256(payment));
    }
}
