
package ex06;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

/**
 *
 * @author Oriol Poveda
 */

public class Generació {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        int longitudClau = 512;

        
        //Generem les calus
        KeyPair keys = randomGenerate(longitudClau);
        PublicKey publicKey = keys.getPublic();
        PrivateKey privateKey = keys.getPrivate();

        try { 
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keys.getPublic().getEncoded());
            FileOutputStream fos = new FileOutputStream("clauPublica.txt");
            fos.write(x509EncodedKeySpec.getEncoded());
            fos.close();
        } catch (Exception e) { System.out.println("ERROR guardant clau publica"); }
        
        //Guardar una clauPrivada
	try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keys.getPrivate().getEncoded());
            FileOutputStream fos = new FileOutputStream("clauPrivada.txt");
            fos.write(pkcs8EncodedKeySpec.getEncoded());
            fos.close();
        } catch (Exception e) { System.out.println("ERROR guardant clau privada"); }
        
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
}
