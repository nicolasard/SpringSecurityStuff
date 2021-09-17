package ar.nic.springsecurity.entity;

import javax.validation.constraints.NotNull;

public class CardPayment {

    @NotNull
    private String name;

    @NotNull
    private String cardNumber;

    @NotNull
    private String expYear;

    @NotNull
    private String expMonth;

    @NotNull
    private String CVV;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpYear() {
        return expYear;
    }

    public void setExpYear(String expYear) {
        this.expYear = expYear;
    }

    public String getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(String expMonth) {
        this.expMonth = expMonth;
    }

    public String getCVV() {
        return CVV;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

}
