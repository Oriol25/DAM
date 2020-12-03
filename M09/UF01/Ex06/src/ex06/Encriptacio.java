/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex06;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Scanner;
import javax.crypto.Cipher;


/**
 *
 * @author Oriol Poveda
 */

public class Encriptacio {
    
    public static void main(String[] args)  {
    
        Scanner teclat = new Scanner(System.in);
        String nomfitxer = teclat.next();
        
       
 
    }
    
    public static byte[] encryptData(byte[] data, PublicKey pub) {
        byte[] encryptedData = null;
            
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding","SunJCE");
            cipher.init(Cipher.ENCRYPT_MODE, pub);
            encryptedData = cipher.doFinal(data);
            
        }  catch (Exception ex) {
            System.err.println("Error xifrant: " + ex);
            
        }  
        
        return encryptedData;
    }

    
}
