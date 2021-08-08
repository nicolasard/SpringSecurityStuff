package ar.nic.springsecurity.services;

import ar.nic.springsecurity.entity.Bill;
import ar.nic.springsecurity.entity.Payment;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BillingService {

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public Bill save(Bill bill) {
        Logger logger = LoggerFactory.getLogger(BillingService.class);
        bill = billingRepository.save(bill);
        logger.info(new StringBuilder().append("Bill Saved ").append(bill.toString()).toString());
        return bill;
    }

    public Payment savePayment(Payment payment){
        payment = paymentRepository.save(payment);
        return payment;
    }

    public Iterable<Bill> list() {
        return billingRepository.findAll();
    }

    public Optional<Bill> getById(Long id){
        return billingRepository.findById(id);
    }
}
