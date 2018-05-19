/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxy.re.encryption.using.rsa.aes;

import java.math.BigInteger;

/**
 *
 * @author singh
 */
public class ProxyReEncryption extends Encryption{
 
    public ProxyReEncryption()
    {
        super();
    }
    
    public MessageUtil reEncrypt(MessageUtil messageUtil, Encryption sender, Encryption reciever)
    {
        this.rsa.e = reciever.rsa.e.multiply(sender.rsa.d);
        BigInteger key = messageUtil.getBigIntKey();
        key = rsa.encrypt(key);
        messageUtil.setKey(key);
        return messageUtil;
    }
}
