/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxy.re.encryption.using.rsa.aes;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author singh
 */
public class ProxyReEncryptionUsingRSAAES {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            
            //Initialise Sender, Server and Reciever
            Encryption sender = new Encryption();
            ProxyReEncryption server = new ProxyReEncryption();
            Encryption reciever = new Encryption();
            
            //Input Message
            String message;
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter message: ");
            message = sc.nextLine();
            System.out.println();
            
            //Encryption
            MessageUtil senderUtil = sender.encryptMessage(message);
            
            //Print Key
            System.out.println("Key: "+senderUtil.getBigIntKey());
            
            senderUtil = sender.encryptKey(senderUtil);
            
            //Print Encrypted Key and Message
            System.out.println("Encrypted Key: "+senderUtil.getBigIntKey());
            System.out.println("Encrypted Message: "+senderUtil.getMessage());
            
            //Re-Encryption
            MessageUtil serverUtil = server.reEncrypt(senderUtil, sender, reciever);
            
            //Print Re-Encrypted Key
            System.out.println("Re-Encrypted Key: "+serverUtil.getBigIntKey());
            
            //Decryption
            MessageUtil recieverUtil = reciever.decryptKey(serverUtil);
            String decryptedMessage = reciever.decryptMessage(recieverUtil);
            
            //Print Decrypted Key
            System.out.println("Decrypted Key: "+recieverUtil.getBigIntKey()+"\n");
            
            //Print Decrypted Message
            System.out.println("Decrypted Message: \n"+decryptedMessage);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
