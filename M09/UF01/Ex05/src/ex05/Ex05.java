
package ex05;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;
import javax.crypto.Cipher;

/**
 *
 * @author Oriol Poveda
 */

public class Ex05 {

    public static void main(String[] args) {

        Scanner teclat = new Scanner(System.in);

        //Demanem la frase
        System.out.println("Frase: ");
        String frase = teclat.nextLine();
        
        byte[] byteFrase = frase.getBytes();
        int longitudClau = 512;

        //Genarem les claus
        KeyPair keys = randomGenerate(longitudClau);
        PublicKey publicKey = keys.getPublic();
        PrivateKey privateKey = keys.getPrivate();

        //Encriptem la frase amb la clau publica
        byte[] byteFraseEn = encryptData(byteFrase, publicKey);
        String fraseEn = new String(byteFraseEn);

        //Desencriptem la frase amb la clau privada
        byte[] byteFraseDes = decryptData(byteFraseEn, privateKey);
        String fraseDes = new String(byteFraseDes);
        
        //Mostrem el contingut
        System.out.println("\nClau publica: " + publicKey + "\n");
        System.out.println("Clau privada: " + privateKey + "\n");
        System.out.println("Frase: " + frase);
        System.out.println("Frase encriptada: " + fraseEn);
        System.out.println("Frase desencriptada: " + fraseDes);
        
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
            
        }  catch (Exception ex) {
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
