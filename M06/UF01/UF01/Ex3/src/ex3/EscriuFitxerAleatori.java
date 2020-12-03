/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex3;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @author Oriol Poveda
 */
public class EscriuFitxerAleatori {
    public static void main(String[] args) throws IOException {
        
                Scanner teclat = new Scanner(System.in);
		File fitxer = new File("fitxerAleatori.txt");
		//Crea un flux (stream) d'arxiu d'accés aleatori per llegir
		RandomAccessFile aleatoriFile = new RandomAccessFile(fitxer, "rw");
		//Les dades per inserir
                
     
                
		String dni[] = new String[5];
                String nom[] = new String[5];
                String cognom[] = new String[5];
                int edat[] = new int[5];
                int tel[] = new int[5];
                
                for (int i = 0; i < 5; i++) {
                    System.out.println("Nou id");
                    System.out.print("DNI: ");
                        dni[i] = teclat.next();
                    System.out.print("Nom: ");
                        nom[i] = teclat.next();
                    System.out.print("Cognom: ");
                        cognom[i] = teclat.next();
                    System.out.print("Edat: ");
                        edat[i] = teclat.nextInt();
                    System.out.print("Telefon: ");
                        tel[i] = teclat.nextInt();
                    System.out.println();
               
                }
                
		//Construeix un buffer (memòria intermèdia) de strings
		StringBuffer buffer = null;
                
                for (int i = 0; i < dni.length; i++) {
                    // 1 enter ocupa 4 bytes
                    aleatoriFile.writeInt(i+1);
                    // 4 bytes
                    
                    //25 caràcters a 2bytes/caràcter 50 bytes 
                    buffer = new StringBuffer(dni[i]);
                    buffer.setLength(25);
                    aleatoriFile.writeChars(buffer.toString());
                    
                    //25 caràcters a 2bytes/caràcter 50 bytes
                    buffer = new StringBuffer(nom[i]);
                    buffer.setLength(25);
                    aleatoriFile.writeChars(buffer.toString());
                    
                    //25 caràcters a 2bytes/caràcter 50 bytes
                    buffer = new StringBuffer(cognom[i]);
                    buffer.setLength(25);
                    aleatoriFile.writeChars(buffer.toString());
                    
                    // 1 enter ocupa 4 bytes
                    aleatoriFile.writeInt(edat[i]);
                    
                    // 1 enter ocupa 4 bytes
                    aleatoriFile.writeInt(tel[i]);
                    // 162 bytes                   
                }
		
		teclat.close();
		aleatoriFile.close();
	}

    
}
