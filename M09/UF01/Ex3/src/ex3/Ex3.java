
package ex3;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Scanner;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Oriol Poveda
 */

public class Ex3 {

    public static void main(String[] args) {
      
        Scanner teclat = new Scanner(System.in);
        
        String palabra = "";
        int num = 0;
      
        System.out.print("Frase: ");
        palabra = teclat.nextLine();
        
        System.out.println("\nEls bytes generats son:\n");
        
        byte[] codi = passwordKeyGeneration(palabra, 256).getEncoded();
        
        for (int i=0; i<codi.length; i++) {
            //System.out.println(Integer.toBinaryString(codi[i] & 0xFF).replace(' ', '0')+" - "+codi[i]);         
            System.out.println(String.format("%8s", Integer.toBinaryString(codi[i] & 0xFF)).replace(' ', '0'));
        
        }
                
        teclat.close();
    }
    
    public static  SecretKey passwordKeyGeneration(String text, int keySize) {
        SecretKey sKey = null;
        
        if ((keySize == 128) || (keySize == 192) || ( keySize == 256) || 
                (keySize == 160) || (keySize == 512))  {
            try {
                byte[] data = text.getBytes("UTF-8");
                MessageDigest md = MessageDigest.getInstance("SHA-256");    
                byte[] hash = md.digest(data);
                byte[] key = Arrays.copyOf(hash, keySize/8);
                sKey = new SecretKeySpec(key, "AES");
                
            } catch (Exception ex) {
                System.err.println("Error generant la clau: " + ex);
                
            }
        }
        return sKey;
    } 
    
}
