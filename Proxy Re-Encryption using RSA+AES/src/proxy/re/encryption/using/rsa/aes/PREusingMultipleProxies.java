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
public class PREusingMultipleProxies {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            
            //Initialise Sender, Server and Reciever
            Encryption sender = new Encryption();
            ProxyReEncryption server1 = new ProxyReEncryption();
            ProxyReEncryption server2 = new ProxyReEncryption();
            ProxyReEncryption server3 = new ProxyReEncryption();
            Encryption randomnode1 = new Encryption();
            Encryption randomnode2 = new Encryption();
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
            
            //Re-Encryption 1
            MessageUtil serverUtil1 = server1.reEncrypt(senderUtil, sender, randomnode1);
            //Print Re-Encrypted Key
            System.out.println("Re-Encrypted Key at Step 1: "+serverUtil1.getBigIntKey());
            
            
            //Re-Encryption 2
            MessageUtil serverUtil2 = server2.reEncrypt(senderUtil, randomnode1, randomnode2);
            //Print Re-Encrypted Key
            System.out.println("Re-Encrypted Key at Step 2: "+serverUtil2.getBigIntKey());
            
            
            //Re-Encryption 3
            MessageUtil serverUtil3 = server3.reEncrypt(senderUtil, randomnode2, reciever);
            //Print Re-Encrypted Key
            System.out.println("Re-Encrypted Key at Step 3: "+serverUtil3.getBigIntKey());
            
            
            //Decryption
            MessageUtil recieverUtil = reciever.decryptKey(serverUtil3);
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

