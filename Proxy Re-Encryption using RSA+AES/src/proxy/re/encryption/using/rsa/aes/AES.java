package proxy.re.encryption.using.rsa.aes;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Divyansh
 */

 
public class AES {
 
    private static SecretKeySpec secretKey;
    private static byte[] key;
    
    public static void setKey(SecretKey myKey) 
    {
        MessageDigest sha = null;
        try {
            String str=new String(Base64.getEncoder().encodeToString(myKey.getEncoded()));
            key = str.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16); 
            secretKey = new SecretKeySpec(key, "AES");
        } 
        catch (Exception e) {
            e.printStackTrace();
        } 
    }
 
    public static String encrypt(String strToEncrypt, SecretKey secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return null;
    }
 
    
    public static String decrypt(String strToDecrypt, SecretKey secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return null;
    }
}