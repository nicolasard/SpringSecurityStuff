package ar.nic.springsecurity.services;

import ar.nic.springsecurity.entity.Bill;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BillingService {

    private BillingRepository BillingRepository;

    public void save(Bill bill) {
        BillingRepository.save(bill);
    }

    public Iterable<Bill> list() {
        return BillingRepository.findAll();
    }

    public Optional<Bill> getById(Long id){
        return BillingRepository.findById(id);
    }
}
