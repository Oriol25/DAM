/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex2;

import java.security.NoSuchAlgorithmException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author Oriol Poveda
 */
public class Algoritme {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        
        byte[] codiUno=keygenKeyGeneration(128).getEncoded();
        
        System.out.println("Algoritme 1:\n\n");
        
        for (int i=0; i<codiUno.length; i++) {
            System.out.println(Integer.toBinaryString(codiUno[i] & 0xFF).replace(' ', '0')+" - "+codiUno[i]);         
        }
        
        System.out.println("\n\nAlgoritme 2:\n\n");
        
        byte[] codiDos=keygenKeyGeneration2(56).getEncoded();
        
        for (int j=0; j<codiDos.length; j++) {
            System.out.println(Integer.toBinaryString(codiDos[j] & 0xFF).replace(' ', '0')+ " - "+ codiDos[j]);
            
        }
        
        System.out.println("\n\nAlgoritme 3:\n\n");
        
        byte[] codiTres=keygenKeyGeneration3(256).getEncoded();
        
        for (int j=0; j<codiTres.length; j++) {
            System.out.println(Integer.toBinaryString(codiTres[j] & 0xFF).replace(' ', '0')+ " - "+ codiTres[j]);
            
        }
        
    }

    public static  SecretKey keygenKeyGeneration(int keySize) {
        SecretKey sKey = null;
        
        if ((keySize == 128) || (keySize == 192) || ( keySize == 256)) {
            try {
                KeyGenerator kgen = KeyGenerator.getInstance("AES");
                kgen.init(keySize);
                sKey= kgen.generateKey();
                
            } catch (NoSuchAlgorithmException ex) {
                System.err.println("Generador no disponible" );
                
            }
        }
        return sKey;
    } 

    public static SecretKey keygenKeyGeneration2(int keySize) {
        SecretKey sKey = null;
        
        if ((keySize == 56) || (keySize == 192) || (keySize == 256)) {
            try {
                KeyGenerator kgen = KeyGenerator.getInstance("DES");
                kgen.init(keySize);
                sKey= kgen.generateKey();
            } catch (NoSuchAlgorithmException ex) {
                System.err.println("Generador no disponible" );
            }
        }
        return sKey;
    }
    
    public static SecretKey keygenKeyGeneration3(int keySize) {
        SecretKey sKey = null;
        
        if ((keySize == 56) || (keySize == 192) || (keySize == 256)) {
            try {
                KeyGenerator kgen = KeyGenerator.getInstance("HmacSHA256");
                kgen.init(keySize);
                sKey= kgen.generateKey();
            } catch (NoSuchAlgorithmException ex) {
                System.err.println("Generador no disponible");
            }
        }
        return sKey;
    }
  
}
