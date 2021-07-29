package ar.nic.springsecurity;

import ar.nic.springsecurity.entity.Bill;
import ar.nic.springsecurity.services.BillingService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BillingServiceTests {

    @Autowired
    BillingService billingService;

    @Test
    public void createBilltest() {
        Bill bill = new Bill();
        //billingService.save(bill);
    }
}
