package ar.nic.springsecurity.services;

import ar.nic.springsecurity.entity.Bill;
import ar.nic.springsecurity.entity.Payment;
import ar.nic.springsecurity.entity.Bill.Status;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BillingService {

    private Logger logger = LoggerFactory.getLogger(BillingService.class);

    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private PaymentRepository paymentRepository;
    
    public String getUsername() {
    	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	String username;
    	if (principal instanceof UserDetails) {
    	  username = ((UserDetails)principal).getUsername();
    	} else {
    	  username = principal.toString();
    	}
    	logger.info(String.format("User %s will save a bill.",username));
		return username;
    }
    
    public Bill save(Bill bill) {
    	bill.setChangedUsername(this.getUsername());
        if (bill.getId()==null) {
        	logger.info("Saving new bill...");
        }else {
        	logger.info("Updating bill"+bill.getId());
        }
        if (bill.getStatus()==null){
        	bill.setStatus(Status.UNPAYED);
        }
        bill = billingRepository.save(bill);
        logger.info(new StringBuilder().append("Bill Saved ").append(bill.toString()).toString());
        return bill;
    }

    public Payment savePayment(Payment payment){
        payment = paymentRepository.save(payment);
        logger.info(new StringBuilder().append("Payment Saved ").append(payment.toString()).toString());
        return payment;
    }

    public Iterable<Bill> list() {
        return billingRepository.findAll();
    }

    public Optional<Bill> getById(Long id){
        return billingRepository.findById(id);
    }
}
