package ar.nic.springsecurity.entity;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Field;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.*;

public class Payment {

    private String oid;

    private String chargetotal;

    private String currency;

    private String paymentMethod;

    private String responseFailURL;

    private String responseSuccessURL;

    private String sharedsecret;

    private String storename;

    private String timezone;

    private String transactionNotificationURL;

    private String txndatetime;

    private String txntpe;

    private String hashExtended;

    private String hash_algorithm;

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

    public String getTxntpe() {
        return txntpe;
    }

    public void setTxntpe(String txntpe) {
        this.txntpe = txntpe;
    }

    public String getExtendedConcat() throws IllegalAccessException {
        Map<String, String> dictionary = new HashMap<String, String>();
        for (Field f : getClass().getDeclaredFields()) {
            Object field = f.get(this);
            if (field != null) {
                if (!field.toString().isEmpty() && !f.getName().equals("sharedsecret")&& !f.getName().equals("hash_algorithm")) {
                    dictionary.put(f.getName(), f.get(this).toString());
                }
            }
        }

        List<String> employeeByKey = new ArrayList<>(dictionary.keySet());
        Collections.sort(employeeByKey);
        StringJoiner sj = new StringJoiner("|");
        for(String s : employeeByKey){
            sj.add(dictionary.get(s).toString());
        }
        return sj.toString();
    }

    public String getHash(String sharedScret) {
        try {
            return calculateHMAC(this.getExtendedConcat(),sharedScret);
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String toHexString(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }

    public static String calculateHMAC(String data, String key)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKeySpec);
        String base64HmacSha256 = Base64.getEncoder().encodeToString(mac.doFinal(data.getBytes()));

        return base64HmacSha256;
    }

    public String getHashExtended() {
        return hashExtended;
    }

    public void setHashExtended(String hashExtended) {
        this.hashExtended = hashExtended;
    }

    public String getHash_algorithm() {
        return hash_algorithm;
    }

    public void setHash_algorithm(String hash_algorithm) {
        this.hash_algorithm = hash_algorithm;
    }
}
