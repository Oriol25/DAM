/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class ServidorTCP4 {
    
    public static void main (String[] args) throws Exception {
        
        //FLUX PER A ENTRADA ESTÀNDARD
	Scanner teclat = new Scanner(System.in);
        
        int numPort = 60000;
	ServerSocket servidor = new ServerSocket(numPort);
	String cadena = "";
        int clients = 0;
        
        System.out.print("Nombre de clientes: ");
        clients = teclat.nextInt();
        
        System.out.println("");
           
	for (int i = 0; i < clients; i++) {
            
            System.out.println("Esperant connexió... ");
            Socket clientConnectat = servidor.accept();
            System.out.println("Client " + (i+1) + " connectat... ");
	
            //FLUX DE SORTIDA AL CLIENT
            PrintWriter fsortida = new PrintWriter(clientConnectat.getOutputStream(), true);
            
            //FLUX D'ENTRADA DEL CLIENT
            BufferedReader fentrada = new BufferedReader(new InputStreamReader(clientConnectat.getInputStream()));
		
            while ((cadena = fentrada.readLine()) != null) {
			
                fsortida.println(cadena);
                if (cadena.equals("*")) {
                    System.out.println("Client " + (i+1) + " desconnectat\n");
                } else {
                    System.out.println("Rebent: "+cadena);
                }

                if (cadena.equals("*")) break;
			
            }
            
            fentrada.close();
            fsortida.close();
            clientConnectat.close();
            
        }
        	
        //TANCAR STREAMS I SOCKETS
        System.out.println("Tancant connexió... ");

        servidor.close();
        
    }
    
}
