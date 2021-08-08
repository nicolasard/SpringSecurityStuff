package ar.nic.springsecurity.services;

import ar.nic.springsecurity.entity.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {

}