package ar.nic.springsecurity.services.fiserv;

public class PaymentPostback {
    private String status;

    private String approval_code;

    private String fail_reason;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
