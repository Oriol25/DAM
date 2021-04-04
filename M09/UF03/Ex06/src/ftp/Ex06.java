
package ftp;

import java.io.*;
import org.apache.commons.net.ftp.*;

public class Ex06 {
    
    public static void main (String[] args) throws IOException {
        
        //FLUX PER A ENTRADA ESTÀNDARD
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
	//Servidor FTP
	FTPClient client = new FTPClient();
	String serverFTP;
	
	//Usuari FTP
	String usuari;
	String contrasenya;
        
        // ftp.urv.es
        System.out.print("Servidor FTP: ");
        serverFTP = in.readLine();
        
        // anonymous
        System.out.print("Usuari: ");
        usuari = in.readLine();
        
        // guest
        System.out.print("Contrasenya: ");
        contrasenya = in.readLine();
             
        System.out.println("Ens connectem al servidor: "+serverFTP);
	
	try {
			
            client.connect(serverFTP);
            boolean login = client.login(usuari, contrasenya);
			
            if (login)		
                System.out.println("Login correcte... ");	
            else {
				
		System.out.println("Login incorrecte... ");
		client.disconnect();
		System.exit(1);
				
            }
			
            System.out.println("Directori actual: "+client.printWorkingDirectory());
            FTPFile[] files = client.listFiles();
            System.out.println("Fitxers al directori actual: "+files.length);
			
            //Array par a visualitzar el tipus de fitxer
            String tipus[] = {"Fitxer", "Directori", "Enllaç simbolic"};
			
            for (int i=0; i<files.length; i++) {
				
                System.out.println("\t"+files[i].getName()+"=>"+tipus[files[i].getType()]);
				
            }
			
            boolean logout = client.logout();
				
            if (logout)			
                System.out.println("Logout del servidor FTP... ");
			
            else		
                System.out.println("Error en fer un logout... ");
			
            client.disconnect();
            System.out.println("Desconnectat... ");
			
        } catch (IOException ioe) {		
            ioe.printStackTrace();
			
        }
    }
}
