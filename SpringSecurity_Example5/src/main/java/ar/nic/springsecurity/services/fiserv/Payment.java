package ar.nic.springsecurity.services.fiserv;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Field;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.*;

/*
 * Fiserv payment object used to request a new payment to the Connect IPG gateway
 */
public class Payment {

    protected String oid;

    protected String chargetotal;

    protected String currency;

    protected String paymentMethod;

    protected String responseFailURL;

    protected String responseSuccessURL;

    protected String sharedsecret;

    protected String storename;

    protected String timezone;

    protected String transactionNotificationURL;

    protected String txndatetime;

    protected String txntype;

    protected String hashExtended;

    protected String hash_algorithm;

    protected String cardnumber;

    protected String expmonth;

    protected String expyear;

    protected String cvm;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getChargetotal() {
        return chargetotal;
    }

    public void setChargetotal(String chargetotal) {
        this.chargetotal = chargetotal;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getResponseFailURL() {
        return responseFailURL;
    }

    public void setResponseFailURL(String responseFailURL) {
        this.responseFailURL = responseFailURL;
    }

    public String getResponseSuccessURL() {
        return responseSuccessURL;
    }

    public void setResponseSuccessURL(String responseSuccessURL) {
        this.responseSuccessURL = responseSuccessURL;
    }

    public String getSharedsecret() {
        return sharedsecret;
    }

    public void setSharedsecret(String sharedsecret) {
        this.sharedsecret = sharedsecret;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getTransactionNotificationURL() {
        return transactionNotificationURL;
    }

    public void setTransactionNotificationURL(String transactionNotificationURL) {
        this.transactionNotificationURL = transactionNotificationURL;
    }

    public String getTxndatetime() {
        return txndatetime;
    }

    public void setTxndatetime(String txndatetime) {
        this.txndatetime = txndatetime;
    }

    public String getTxntype() {
        return txntype;
    }

    public void setTxntype(String txntype) {
        this.txntype = txntype;
    }

    public String getHashExtended() {
        return hashExtended;
    }

    public String getHash_algorithm() {
        return hash_algorithm;
    }

    public void setHash_algorithm(String hash_algorithm) {
        this.hash_algorithm = hash_algorithm;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getExpmonth() {
        return expmonth;
    }

    public void setExpmonth(String expmonth) {
        this.expmonth = expmonth;
    }

    public String getExpyear() {
        return expyear;
    }

    public void setExpyear(String expyear) {
        this.expyear = expyear;
    }

    public String getCvm() {
        return cvm;
    }

    public void setCvm(String cvm) {
        this.cvm = cvm;
    }

    public void setHashExtended(String hashExtended) {
        this.hashExtended = hashExtended;
    }
}
