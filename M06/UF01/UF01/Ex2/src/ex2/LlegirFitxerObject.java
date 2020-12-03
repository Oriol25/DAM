
package ex2;

import java.io.*;
import java.util.Scanner;
/**
 *
 * @author Oriol Poveda
 */

public class LlegirFitxerObject  {

       public static void main(String[] args) throws IOException, ClassNotFoundException  {
		//Camp variable tipus Comarca
           
                Scanner teclat = new Scanner(System.in);
                int op = 0;
                String pregunta;
                int preguntan;
                
		Marca marca;
		//Declaració del fitxer
		File fitxer = new File("coche.txt");
		//Crea el flux d'entrada
		FileInputStream filein = new FileInputStream(fitxer);
		//Connectar el flux de bytes al flux de dades
		ObjectInputStream dataInMarc = new ObjectInputStream(filein);
                
                System.out.println("Tria la opcio");
                System.out.println("1 - Mostrar tots els cotxes del fitxer");
                System.out.println("2 - Buscar un cotxe del fitxer");
                op = teclat.nextInt();
                
		if (op == 1) {
                    try {
			while (true){//Llegeix el fitxer
				//Llegeix la comarca
				marca = (Marca) dataInMarc.readObject();
				System.out.print("Marca (" + marca.getMarca() + ") ");
                                System.out.print("Model (" + marca.getModel() + ") ");
                                System.out.print("Any (" + marca.getAny()+ ") ");
                                System.out.print(" Matricula (" + marca.getMatricula() + ")\n");
                                
                                
			}
		} catch (EOFException eo) {}
                    
               } else if (op == 2) {
                   System.out.println("Quin parametre vols buscar");
                   System.out.println("1 - Marca");
                   System.out.println("2 - Model");
                   System.out.println("3 - Any");
                   System.out.println("4 - Matricula");
                   op = teclat.nextInt();
                   
                   if (op == 1){
                     System.out.println("Nom de la marca");
                     pregunta = teclat.next();
                      
                     
                      try {
                           
			while (true){//Llegeix el fitxer
				//Llegeix la comarca
                                 marca = (Marca) dataInMarc.readObject();
                                if (pregunta.equalsIgnoreCase(marca.getMarca())){
				System.out.print("Marca (" + marca.getMarca() + ") ");
                                System.out.print("Model (" + marca.getModel() + ") ");
                                System.out.print("Any (" + marca.getAny()+ ") ");
                                System.out.print(" Matricula (" + marca.getMatricula() + ")\n");
                                
                            }   
			
                     } 

                   }  catch (EOFException eo) {}
                   
                } else if (op == 2) {
                    System.out.println("Nom del model");
                     pregunta = teclat.next();
                      
                     
                      try {
                           
			while (true){//Llegeix el fitxer
				//Llegeix la comarca
                                 marca = (Marca) dataInMarc.readObject();
                                if (pregunta.equalsIgnoreCase(marca.getModel())){
				System.out.print("Marca (" + marca.getMarca() + ") ");
                                System.out.print("Model (" + marca.getModel() + ") ");
                                System.out.print("Any (" + marca.getAny()+ ") ");
                                System.out.print(" Matricula (" + marca.getMatricula() + ")\n");
                                
                            }   
			
                     } 

                   }  catch (EOFException eo) {}
                    
                } else if (op == 3) {
                    System.out.println("Any de fabricació");
                     preguntan = teclat.nextInt();
                      
                     
                      try {
                           
			while (true){//Llegeix el fitxer
				//Llegeix la comarca
                                 marca = (Marca) dataInMarc.readObject();
                                if (preguntan == marca.getAny()){
                                    System.out.print("Marca (" + marca.getMarca() + ") ");
                                    System.out.print("Model (" + marca.getModel() + ") ");
                                    System.out.print("Any (" + marca.getAny()+ ") ");
                                    System.out.print(" Matricula (" + marca.getMatricula() + ")\n");
                                
                                }   
			
                     } 

                   }  catch (EOFException eo) {}
                    
                } else if (op == 4) {
                    System.out.println("Serie de matricula");
                     pregunta = teclat.next();
                      
                     
                      try {
                           
			while (true){//Llegeix el fitxer
				//Llegeix la comarca
                                 marca = (Marca) dataInMarc.readObject();
                                if (pregunta.equalsIgnoreCase(marca.getMatricula())){
				System.out.print("Marca (" + marca.getMarca() + ") ");
                                System.out.print("Model (" + marca.getModel() + ") ");
                                System.out.print("Any (" + marca.getAny()+ ") ");
                                System.out.print(" Matricula (" + marca.getMatricula() + ")\n");
                                
                            }   
			
                     } 

                   }  catch (EOFException eo) {}
                    
                }

                   
               }
		dataInMarc.close();//Tanca el stream d'entrada
	}

    
}
