/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex05;

import static ex05.Ex05.randomGenerate;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

/**
 *
 * @author Oriol Poveda
 */

public class noDual {

    public static void main(String[] args) {
        
        int longitudClau = 2048;
        
        for (int i = 0; i < 100; i++) {
            KeyPair keys = randomGenerate(longitudClau);
            PublicKey publicKey = keys.getPublic();
            PrivateKey privateKey = keys.getPrivate();
            
            System.out.println((i+1)+"- " + "\n\n" + publicKey);
            System.out.println((i+1)+"- " + "\nprivate key: " + privateKey);
        }
    }
    
    
    
    public static KeyPair randomGenerate(int longitudClau) {
        KeyPair keys = null;
	
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(longitudClau);
            keys = keyGen.genKeyPair();
	
        } catch (Exception ex) {
            System.err.println("Generador no disponible.");
	}
	
        return keys;
	
    }
    
    public static byte[] encryptData(byte[] data, PublicKey pub) {
        byte[] encryptedData = null;
            
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding","SunJCE");
            cipher.init(Cipher.ENCRYPT_MODE, pub);
            encryptedData = cipher.doFinal(data);
        } catch (Exception ex) {
            System.err.println("Error xifrant: " + ex);
        }
            
        return encryptedData;
    }

    public static byte[] decryptData(byte[] data, PrivateKey pri) {
        byte[] decryptedData = null;
	
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding","SunJCE");
            cipher.init(Cipher.DECRYPT_MODE, pri);
            decryptedData = cipher.doFinal(data);
        } catch (Exception ex) {
            System.err.println("Error desxifrant: " + ex);
        }
        
        return decryptedData;
    }
}
