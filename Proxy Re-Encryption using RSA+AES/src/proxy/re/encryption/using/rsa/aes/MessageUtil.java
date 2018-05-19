/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxy.re.encryption.using.rsa.aes;

import java.math.BigInteger;
import java.util.Base64;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author singh
 */
public class MessageUtil {
    private String message;
    private BigInteger key;
    
    public MessageUtil(String message,SecretKey key)
    {
        this.message = message;
        this.key = new BigInteger(Base64.getEncoder().encodeToString(key.getEncoded()).getBytes());
    }
    
    public MessageUtil(String message,BigInteger key)
    {
        this.message = message;
        this.key = key;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
    
    public void setKey(SecretKey key)
    {
        this.key = new BigInteger(Base64.getEncoder().encodeToString(key.getEncoded()).getBytes());
    }
            
    public void setKey(BigInteger key)
    {
        this.key = key;
    }
    
    public String getMessage()
    {
        return message;
    }
    
    public SecretKey getKey()
    {
        String keyString = new String(key.toByteArray());
        byte[] decodedKey = Base64.getDecoder().decode(keyString);
        // Constructs a secret key from the given byte array 
        SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        return secretKey;
    }
    
    public BigInteger getBigIntKey()
    {
        return key;
    }
}
