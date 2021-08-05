package ar.nic.springsecurity.services;

import ar.nic.springsecurity.entity.Bill;
import com.sun.org.apache.xpath.internal.operations.String;
import lombok.AllArgsConstructor;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BillingService {

    private BillingRepository billingRepository;

    public void save(Bill bill) {
        Logger logger = LoggerFactory.getLogger(BillingService.class);
        billingRepository.save(bill);
        logger.info(new StringBuilder().append("Bill Saved ").append(bill.toString()).toString());
    }

    public Iterable<Bill> list() {
        return billingRepository.findAll();
    }

    public Optional<Bill> getById(Long id){
        return billingRepository.findById(id);
    }
}
