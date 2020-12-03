
package ex3;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author Oriol Poveda
 */

public class LlegirFitxerAleatori {
    public static void main(String[] args) throws IOException  {
        File fitxer = new File("fitxerAleatori.txt");
        //Crea un flux (stream) d'arxiu d'accés aleatori només lectura
	RandomAccessFile aleatoriFile = new RandomAccessFile(fitxer, "r");

        int apuntador = 0, edat, tel, id;
	char dni[] = new char[25], nom[] = new char[25], cognom[] = new char[25], aux;

        
        
        for (;;){
          aleatoriFile.seek(apuntador);//Apuntar a l'inici de cada llibre al fitxer
          //Llegeix ID
            id = aleatoriFile.readInt();
  
          //Llegeix DNI
            for(int i = 0; i<dni.length; i++) {
            aux = aleatoriFile.readChar();
            dni[i] = aux;
            }
            String dnis = new String(dni);
          // Llegeix Nom
            for(int i = 0; i<nom.length; i++) {
            aux = aleatoriFile.readChar();
            nom[i] = aux;
            }
            String noms = new String(nom);
          // Llegeix Cognoms
            for(int i = 0; i<cognom.length; i++) {
            aux = aleatoriFile.readChar();
            cognom[i] = aux;
            }
            String cognoms = new String(cognom);
          //Llegeix edat
            edat = aleatoriFile.readInt();
            
          //Llegeix telefono
            tel = aleatoriFile.readInt();
            
            System.out.println("ID: " + id);
            System.out.println("DNI: " + dnis);
            System.out.println("Nom: " + noms);
            System.out.println("Cognom: " + cognoms);
            System.out.println("Edat: " + edat);
            System.out.println("Telefon: " + tel +"\n\n");
            
            apuntador += 162;
            
            if (aleatoriFile.getFilePointer() == aleatoriFile.length()) break;
            
        }
        aleatoriFile.close();
    }
}
