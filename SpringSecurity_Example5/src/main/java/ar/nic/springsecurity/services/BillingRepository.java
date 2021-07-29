package ar.nic.springsecurity.services;

import ar.nic.springsecurity.entity.Bill;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BillingRepository extends CrudRepository<Bill, Long> {

}
