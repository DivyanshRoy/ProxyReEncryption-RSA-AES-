/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxy.re.encryption.using.rsa.aes;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author singh
 */
public class Encryption {
    
    protected RSA rsa;
    
    public Encryption()
    {
        rsa = new RSA();
    }
    
    public MessageUtil encryptMessage(String message) throws NoSuchAlgorithmException
    {
        //AES Symmetric Key Generation and Encryption of Plaintext Message
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        SecretKey key = keyGen.generateKey();
        message = AES.encrypt(message, key);
        return new MessageUtil(message,key);
    }
    
    public MessageUtil encryptKey(MessageUtil messageUtil)
    {
        BigInteger key = rsa.encrypt(messageUtil.getBigIntKey());
        messageUtil.setKey(key);
        return messageUtil;
    }
    
    public MessageUtil decryptKey(MessageUtil messageUtil)
    {
        BigInteger key = rsa.decrypt(messageUtil.getBigIntKey());
        messageUtil.setKey(key);
        return messageUtil;
    }
    
    public String decryptMessage(MessageUtil messageUtil)
    {
        SecretKey key = messageUtil.getKey();
        String message = AES.decrypt(messageUtil.getMessage(), key);
        return message;
    }
    
}
