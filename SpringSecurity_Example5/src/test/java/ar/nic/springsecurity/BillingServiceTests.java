package ar.nic.springsecurity;

import ar.nic.springsecurity.entity.Bill;
import ar.nic.springsecurity.entity.CurrencyType;
import ar.nic.springsecurity.entity.Payment;
import ar.nic.springsecurity.services.BillingService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest //This makes Spring to resolve the bean injection. https://www.youtube.com/watch?v=Ekr4jxOIf4c a good video about Junit tests
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //TODO: Sort the run not by name but an explicit order.
public class BillingServiceTests {

    @Autowired
    BillingService billingService;

    @Test
    public void createBillTest() {
        Bill bill = new Bill();
        bill.setBillNumber("00012");
        bill.setDescription("Bill description 123");
        bill.setCurrency(CurrencyType.EUR);
        bill.setStatus(Bill.Status.UNPAYED);
        bill.setTotal("100.3");
        bill.setExpiration(LocalDateTime.now().plusDays(3));
        bill.setCreateDate(LocalDateTime.now());
        billingService.save(bill);
        Assert.assertEquals(new Long(1),bill.getId());
    }

    @Test
    public void getBillByIdTest() {
        // Warning: This won't run if createBillTests ran before.
        Optional<Bill> bill = billingService.getById(new Long(1));
        Assert.assertEquals("Bill description 123",bill.get().getDescription());
        Assert.assertEquals("00012",bill.get().getBillNumber());
    }

    @Test
    public void getBillsByUserTest() {

    }

    @Test
    public void zzzaddPaymentToBillTest() {
        Optional<Bill> bill = billingService.getById(new Long(1));
        Payment payment = new Payment();
        payment.setAcquirerID("some-random-uuid-if-you-can");
        payment.setPaymentType(Payment.PaymentType.FISERV_CREDITCARD);
        payment.setStatus(Payment.PaymentStatus.CREATED);
        payment.setBill(bill.get());
        billingService.savePayment(payment);
    }
}
