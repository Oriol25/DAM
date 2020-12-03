
package ex3;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @author Oriol Poveda
 */

public class ConsultarFitxerAleatori {
 
    public static void main(String[] args)throws IOException    {
        
        File fitxer = new File("fitxerAleatori.txt");
        //Crea un flux (stream) d'arxiu d'accés aleatori només lectura
	RandomAccessFile aleatoriFile = new RandomAccessFile(fitxer, "r");

        int apuntador = 0, seleccio = 0, opcio;
        int id = 0 ,edat, tel;
        char dni[] = new char[25], nom[] = new char[25], cognom[] = new char[25], aux;
        Scanner teclat = new Scanner (System.in);
        String sel = "";
        
        System.out.println("Parametre a buscar?");
        System.out.println("1 - ID");
        System.out.println("2 - DNI");
        System.out.println("3 - Nom");
        System.out.println("4 - Cognom");
        System.out.println("5 - Edat");
        System.out.println("6 - Telefon");
        opcio = teclat.nextInt();
        System.out.println("Introduiex la dada");
        
        if (opcio == 1 || opcio == 5 || opcio == 6){
            seleccio = teclat.nextInt();
            
        } else {
            sel = teclat.next();
           
        }
                
        for (;;){
            
            if (apuntador >= aleatoriFile.length()){
                System.out.println("ERROR: ID incorrecte, no existeix aquesta persona");
            } else { //Apuntar a l'inici del llibre seleccionat al fitxer
                aleatoriFile.seek(apuntador);
                id = aleatoriFile.readInt(); //Llegeix ID

                // Llegeix dni
                for (int i = 0; i < dni.length; i++){
                    aux = aleatoriFile.readChar();
                    dni[i] = aux;

                }
                String dnis = new String(dni);

                // Llegeix nom
                for (int i = 0; i < nom.length; i++){
                    aux = aleatoriFile.readChar();
                    nom[i] = aux;

                }
                String noms = new String(nom);

                // Llegeix cognom
                for (int i = 0; i < cognom.length; i++){
                    aux = aleatoriFile.readChar();
                    cognom[i] = aux;

                }
                String cognoms = new String(cognom);

                edat = aleatoriFile.readInt(); //Llegeix Edat
                tel = aleatoriFile.readInt(); //Llegeix Telefon
                if (dnis.trim().toLowerCase().equals(sel.trim().toLowerCase()) || noms.trim().toLowerCase().equals(sel.trim().toLowerCase()) || cognoms.trim().toLowerCase().equals(sel.trim().toLowerCase()) || (tel == seleccio) || (edat == seleccio) || (id == seleccio)) {
                    System.out.println("ID: " + id);
                    System.out.println("DNI: " + dnis);
                    System.out.println("Nom: " + noms);
                    System.out.println("Cognom: " + cognoms);
                    System.out.println("Edat: " + edat);
                    System.out.println("Telefon: " + tel +"\n\n");
                    
                }
                
                apuntador +=162;

                if(aleatoriFile.getFilePointer()==aleatoriFile.length()) break;
                
            }
        }
        
        aleatoriFile.close();//Tancar el fitxer
        teclat.close();
    }
    
}
