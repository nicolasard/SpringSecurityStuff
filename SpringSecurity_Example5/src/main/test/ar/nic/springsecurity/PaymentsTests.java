package ar.nic.springsecurity;

import ar.nic.springsecurity.entity.Payment;
import org.junit.Assert;
import org.junit.Test;

public class PaymentsTests {

    @Test
    public void testHash() throws IllegalAccessException {
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

        String expectedResult = "13.00|978|M|https://localhost:8643/webshop/response_failure.jsp|https://localhost:8643/webshop/response_success.jsp|10123456789|Europe/Berlin|https://localhost:8643/webshop/transactionNotification|2020:04:17-17:32:41|sale";
        String expectedHash = "8CVD62a88mwr/Nfc+t+CWB+XG0g5cqmSrN8JhFlQJVM=";
        Assert.assertEquals(expectedResult,payment.getExtendedConcat());
        Assert.assertEquals(expectedHash,payment.getHash("sharedsecret"));

    }
}
