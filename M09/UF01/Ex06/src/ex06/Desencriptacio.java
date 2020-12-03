
package ex06;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;


/**
 *
 * @author Oriol Poveda
 */
public class Desencriptacio {
    public static void main (String[] args) {
        
         //Recuperar clauPublica
	try {
            File filePublicKey = new File("clauPublica");
            FileInputStream fis = new FileInputStream("clauPublica");
            byte[] encodedPublicKey = new byte[(int) filePublicKey.length()];
            fis.read(encodedPublicKey);
            fis.close();
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedPublicKey);
            PublicKey pb = keyFactory.generatePublic(publicKeySpec);            
         } catch (Exception e) { 
             System.out.println("Error llegint clau privada"); 
         }

        //Recuperar clauPrivada
	try {
            File filePrivateKey = new File("clauPrivada");
            FileInputStream fis = new FileInputStream("clauPrivada");
            byte[] encodedPrivateKey = new byte[(int) filePrivateKey.length()];
            fis.read(encodedPrivateKey);
            fis.close();
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(encodedPrivateKey);
            PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
            }
        catch (Exception e) { System.out.println("Error llegint clau privada"); }
    
  
    
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
