package ar.nic.springsecurity.services.fiserv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Field;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.*;

/*
 * Fiserv Payments gateway
 */
@Service
public class PaymentService {

    private String key;

    private Logger logger = LoggerFactory.getLogger(PaymentService.class);

    public PaymentService(){
        this.key = "sharedsecret";
        logger.info("PaymentService initiated...");
    }

    /*
     * Get fields used to calculate the hash concatenated.
     */
    public String getExtendedConcat(Payment payment) throws IllegalAccessException {
        Map<String, String> dictionary = new HashMap<String, String>();
        for (Field f : payment.getClass().getDeclaredFields()) {
            Object field = f.get(payment);
            if (field != null) {
                if (!field.toString().isEmpty() && !f.getName().equals("sharedsecret") && !f.getName().equals("cardnumber") && !f.getName().equals("cvm") && !f.getName().equals("expyear") && !f.getName().equals("expmonth") ){
                    dictionary.put(f.getName(), f.get(payment).toString());
                }
            }
        }
        List<String> employeeByKey = new ArrayList<>(dictionary.keySet());
        Collections.sort(employeeByKey);
        StringJoiner sj = new StringJoiner("|");
        for(String s : employeeByKey){
            sj.add(dictionary.get(s).toString());
        }
        logger.info(sj.toString());
        return sj.toString();
    }

    /*
     * Get HMAC256 from a payment object
     */
    public String getHMAC256(Payment payment)
            throws SignatureException, NoSuchAlgorithmException, InvalidKeyException, IllegalAccessException {
            String data = this.getExtendedConcat(payment);
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);
            String base64HmacSha256 = Base64.getEncoder().encodeToString(mac.doFinal(data.getBytes()));
            return base64HmacSha256;
    }

    private static String toHexString(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return formatter.toString();
    }
}
