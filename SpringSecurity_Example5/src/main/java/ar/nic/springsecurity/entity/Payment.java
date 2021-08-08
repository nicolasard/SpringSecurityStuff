package ar.nic.springsecurity.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "Payment")
public class Payment {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getAcquirerID() {
        return acquirerID;
    }

    public void setAcquirerID(String acquirerID) {
        this.acquirerID = acquirerID;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public PaymentStatus getStatus() {
        return Status;
    }

    public void setStatus(PaymentStatus status) {
        Status = status;
    }

    public enum PaymentType {
        FISERV_CREDITCARD,
        CASH,
        BITCOIN,
        ETHERIUM
    }

    public enum PaymentStatus {
        CREATED,
        WAITING,
        ACCEPTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private
    PaymentType paymentType;

    @NotNull
    private
    String acquirerID;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "id",insertable=false, updatable = false)
    private
    Bill bill;

    @NotNull
    private
    PaymentStatus Status;

}
