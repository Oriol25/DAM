package ex1;

import java.io.File;

/**
 *
 * @author Oriol Poveda
 */

public class VeureInfo {
    public static void main(String[] args) {
                
        File fitxer = new File(args[0]);
        String[] arxius = fitxer.list();
        if (fitxer.exists()){
            if (fitxer.isDirectory()) {
                System.out.println("Dins del directori ");
                for(int i = 0; i < arxius.length; i++){
                    System.out.println(arxius[i]);
                }
            } else if (fitxer.isFile()) {
                System.out.println("Ruta           : "+fitxer.getPath());
		System.out.println("Ruta absoluta  : "+fitxer.getAbsolutePath());
		System.out.println("Es pot escriure: "+fitxer.canRead());
		System.out.println("Es pot llegir  : "+fitxer.canWrite());
		System.out.println("Grandaria      : "+fitxer.length());
		System.out.println("Es un directori: "+fitxer.isDirectory());
		System.out.println("Es un fitxer   : "+fitxer.isFile());

            }
            
            if (fitxer.isHidden()){
                
                System.out.println("El fitxer esta ocult");
                
            } else {
                
                System.out.println("El fitxer o directori no esta ocult");
                
            }
            
            if ((18529 - ((((fitxer.lastModified() / 1000) / 60) /60) /24) <3)){
                System.out.println("El fitxer se ha modificat fa menys de tres dies");
            } else {
                
                System.out.println("El fitxer se ha modificat fa mes de tres dies");
            }
           
            
      
        } else {
            
            System.out.println("El fitxer o directori no existeix");
        }
       
    }
    
}