
package servidor;

import java.net.*;
import java.io.*;

public class Client {
    
    public static void main(String[] args) throws Exception {
        
        String host = "localhost";
	int port = 60000;
	Socket client = new Socket(host, port);
        
        //FLUX DE SORTIDA AL SERVIDOR
        PrintWriter fsortida = new PrintWriter(client.getOutputStream(), true);
		
	//FLUX D'ENTRADA AL SERVIDOR
	BufferedReader fentrada = new BufferedReader(new InputStreamReader(client.getInputStream()));
		
	//FLUX PER A ENTRADA ESTÀNDARD
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        
        String cadena = "", eco = "";
        
        do {
            
            System.out.print("Introdueix la cadena: ");
            cadena = in.readLine();
            
            //Enviament cadena al servidor
            fsortida.println(cadena);
            
            //Rebuda cadena del servidor
            eco = fentrada.readLine();
            System.out.println("  => ECO: "+eco);
            System.out.println("");
 
            
        } while (!cadena.equals("*"));
	
	fsortida.close();
	fentrada.close();
	System.out.println("Finalització de l'enviament...");
	in.close();
	client.close();
        
    }
    
}
