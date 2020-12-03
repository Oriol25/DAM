
package ex2;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author Oriol Poveda
 */
public class EscriureFitxerObject {
    
    public static void main(String[] args) throws IOException {
    
    Scanner teclat = new Scanner(System.in);
        
     //Camp variable tipus Comarca
     Marca marca;
     //Declaració del fitxer
     File fitxer = new File("coche.txt");
     //Crea el flux de sortida
     FileOutputStream fileout = new FileOutputStream(fitxer);
     //Connectar el flux de bytes al flux de dades
     ObjectOutputStream dataOuMarc = new ObjectOutputStream(fileout);
     String marc[] = {"Audi","Citroen", "SEAT"};
     String modelo[] = {"R8", "Corvette", "Ibiza"};
     int any[] = {2016,2020,2015};
     String matricula[] = {"9875AGB", "6597HJY", "5234FRW"};
     
     for (int i = 0; i < marc.length; i++){
         marca = new Marca(marc[i], modelo[i], any[i], matricula[i]);
         dataOuMarc.writeObject(marca);//L'escriu al fixer
         
     }
     
     int opcio, anyA;
     String marcaA, modeloA, matriculaA;

     System.out.print("Marca? ");
     marcaA = teclat.next();
     System.out.print("Model? ");
     modeloA = teclat.next();
     System.out.print("Any? ");
     anyA = teclat.nextInt();
     System.out.print("Matriucla? ");
     matriculaA = teclat.next();
                
     Marca cotxe;
               
     cotxe = new Marca(marcaA, modeloA, anyA, matriculaA);
     dataOuMarc.writeObject(cotxe);

         
     teclat.close();
     dataOuMarc.close(); //Tanca el stream de sortida
     
    }

}
