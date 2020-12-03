/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex04;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Oriol Poveda
 */

public class Ex04 {
    public static void main(String[] args) throws IOException {
        
           Scanner teclat = new Scanner(System.in);
           String fitxer, paraula, clau;
           int kSize = 256;
           
           System.out.print("Introdueix la clau: ");
           clau = teclat.nextLine();
           System.out.print("Nom del arxiu: ");
           fitxer = teclat.nextLine();
           
           Path path = Paths.get(fitxer);
           
           SecretKey sKey = passwordKeyGeneration(clau, kSize);
           
           byte[] info;
           info = Files.readAllBytes(path);
           byte[] encrypt;
           encrypt = encryptData(sKey, info);
           
           byte[] decrypt;
           decrypt = decryptData(sKey, encrypt);
           
           String encriptat;
           encriptat = new String(encrypt);
           System.out.println("Text encriptat: " + encriptat);    
           String desencriptat;
           desencriptat = new String(decrypt);
           System.out.println("Text desencriptat: " + desencriptat);
           
           String[] pathDividt = fitxer.split("\\.");
           String nom = pathDividt[0];
           String ext = pathDividt[1];
           
           try {
               File file = new File(nom + "_X." + ext);
               FileWriter fw = new FileWriter(file);
               BufferedWriter bw = new BufferedWriter(fw);
               bw.write(encriptat);
               bw.close();
               System.out.println(file + " creat");
           } catch (Exception ex) {
               System.err.println("Error");
           }
           
           try {
               File arxiu = new File(nom + "_Y." + ext);
               FileWriter fws = new FileWriter(arxiu);
               BufferedWriter bws = new BufferedWriter(fws);
               bws.write(desencriptat);
               bws.close();
               System.out.println(arxiu + " creat");
               
           } catch (Exception ex) {
               System.err.println("Error");
           }

    }

    public static SecretKey passwordKeyGeneration(String text, int keySize) {
	SecretKey sKey = null;
        
	if ((keySize == 128) || ( keySize == 192) || (keySize == 256)) {
            try {
                byte[] data = text.getBytes("UTF-8");
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hash = md.digest(data);
                byte[] key = Arrays.copyOf(hash, keySize/8);
                sKey = new SecretKeySpec(key, "AES");
            } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
                System.out.println("Error generant la clau: " + ex);
            }
	}
        
	return sKey;
    }

    public static byte[] encryptData(SecretKey sKey, byte[] data) {
	byte[] encryptedData = null;
        
	try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, sKey);
            encryptedData = cipher.doFinal(data);
	}catch (Exception ex) {
            System.err.println("Error xifrant les dades: " + ex);
	}
        
	return encryptedData;
    }

    public static byte[] decryptData(SecretKey sKey1, byte[] data) {
        byte[] encryptedData = null;
        
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, sKey1);
            encryptedData = cipher.doFinal(data);
        } catch (Exception ex) {
            System.err.println("Error desxifrant les dades: " + ex);
        }
        
        return encryptedData;
    }
    
    
    
}