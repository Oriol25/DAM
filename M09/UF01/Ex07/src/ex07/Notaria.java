/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex07;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 * @author Oriol Poveda
 * 
 */

public class Notaria {
    
    
    public static void main(String[] args) throws IOException {    
    
        boolean comprovant;
        byte[] data;
        byte[] signature;
        
        PublicKey pub = null;
        
        pub = RecuperarClauPublica(pub);
        
        data = Files.readAllBytes(Paths.get("missatge"));
        signature = Files.readAllBytes(Paths.get("firma_missatge"));
        
        comprovant = validateSignature(data, signature, pub);
        
        if (comprovant) {
            System.out.println("Comprovant signatura de l’arxiu missatge...OK");
        } else {
            System.out.println("Comprovant signatura de l’arxiu missatge...ERROR");
        }

    }
    
    public static boolean validateSignature(byte[] data, byte[] signature, PublicKey pub) {
        
        boolean isValid = false;
        try {
            Signature signer = Signature.getInstance("SHA1withRSA");
            signer.initVerify(pub);
            signer.update(data);
            isValid = signer.verify(signature);
        } catch (Exception ex) { 
            System.err.println("Error validant les dades: " + ex);
        }
        
        return isValid;   
    }
    
    public static PublicKey RecuperarClauPublica (PublicKey pub) {
        try {
            File filePublicKey = new File("clauPublica");
            FileInputStream fis = new FileInputStream("clauPublica");
            byte[] encodedPublicKey = new byte[(int) filePublicKey.length()];
            fis.read(encodedPublicKey);
            fis.close();
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(encodedPublicKey);
            pub = keyFactory.generatePublic(publicKeySpec);            
        } catch (Exception e) { System.err.println("Error llegint clau publica"); }
        return pub;
    }
    
}
