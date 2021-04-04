
package servidor;

import java.net.*;
import java.io.*;

public class ServidorTCP3 {
    
    public static void main (String[] args) throws Exception {
        
        int numPort = 60000;
	ServerSocket servidor = new ServerSocket(numPort);
	String cadena = "";
        int clients = 3;
        
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
