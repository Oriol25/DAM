
package ex07;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Scanner;

/**
 *
 * @author Oriol Poveda
 * 
 */

public class Signatura {
    
    public static void main(String[] args) throws IOException {
        
        Scanner teclat = new Scanner(System.in);
        
        System.out.println("Introdueix la frase");
        String missatge = teclat.nextLine();
        
        int longitudClau = 512;

        //Generem les calus
        System.out.print("\n\nGenerant claus publiques i provades "
                + "(arxius clauPublica i clauPrivada)");
        
        KeyPair keys = randomGenerate(longitudClau);
        PublicKey publicKey = keys.getPublic();
        PrivateKey privateKey = keys.getPrivate();
        
        System.out.print("... OK");
        
        publicKeyFile(keys);
        privateKeyFile(keys);
        
        byte[] data = missatge.getBytes();
        byte[] signar = signData(data,privateKey);
        
        archivobyte(data, "missatge");
        archivobyte(signar, "firma_missatge");
        
        System.out.println("\n");
        
        System.out.println("Generant arxiu missatge... OK");
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
    
    public static byte[] signData(byte[] data, PrivateKey priv) {
        byte[] signature = null;
        try {
            Signature signer = Signature.getInstance("SHA1withRSA");
            signer.initSign(priv);
            signer.update(data);
            signature = signer.sign();
        } catch (Exception ex) {
            System.err.println("Error signant les dades: " + ex);
        }
        
        System.out.println("\nSignant el missatge... OK");
                
              
        return signature;
        
    }
    
    public static void publicKeyFile(KeyPair keys) throws IOException {
        try { 
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keys.getPublic().getEncoded());
            FileOutputStream fos = new FileOutputStream("clauPublica");
            fos.write(x509EncodedKeySpec.getEncoded());
            fos.close();
        } catch (Exception e) { 
            System.out.print("... Error clauPublica");
        }
    }

    public static void privateKeyFile(KeyPair keys) throws IOException {
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keys.getPrivate().getEncoded());
            FileOutputStream fos = new FileOutputStream("clauPrivada");
            fos.write(pkcs8EncodedKeySpec.getEncoded());
            fos.close();
        } catch (Exception e) { 
            System.out.println("... Error clauPublica"); 
        }
    }

    public static void archivobyte(byte[] date,String nombrearchivo){
        try {
            FileOutputStream fos = new FileOutputStream(nombrearchivo);
            fos.write(date);
            fos.close();
        } catch (Exception e) { System.out.println("... Error " + nombrearchivo);}
        
    }
}
