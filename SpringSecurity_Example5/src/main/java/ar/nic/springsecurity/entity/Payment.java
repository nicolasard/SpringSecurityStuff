package ar.nic.springsecurity.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Payment {

    public enum PaymentType {
        FISERV_CREDITCARD,
        CASH
    }

    public enum PaymentStatus {
        CREATED,
        WAITING,
        ACCEPTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    PaymentType paymentType;

    String acquirerID;

    Bill bill;

    PaymentStatus Status;

}
