/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activitat2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Activitat2 {


    public static void main(String[] args) throws FileNotFoundException, IOException {
        
        FileReader fr;
        BufferedReader br = null;
        
        Scanner teclat = new Scanner(System.in);
        
        String cadena;
        String archivo;
        
        int num = 0;
        int acumulador = 0;
        
        boolean dinsGrup = false;
        
        System.out.println("Name of the file?");
        archivo = teclat.next();
        
        System.out.println("");
        
        try {
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            
            while ((cadena = br.readLine()) != null) {
                try {
                    num = Integer.valueOf(cadena);
                    acumulador = acumulador + num;
                    dinsGrup = true;
                } catch (NumberFormatException e) {
                    if (dinsGrup) {
                        System.out.println("Sum = " + acumulador + "\n");
                    }
                    System.out.println(cadena);
                    acumulador = 0;
                }
            }
            
            System.out.println("Sum = " + acumulador);
       
            br.close();
            
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo");
        }       
    }
}
