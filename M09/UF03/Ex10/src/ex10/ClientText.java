package ex10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oriol Poveda
 */
public class ClientText implements Runnable {
    
    String host = "localhost";
    int port = 60000;
    static Socket clientS;
    static String login;
  
    public ClientText(Socket client) {
        this.clientS = client;
    }
    
    public static void main (String[] args) throws Exception {
        
        String host = "localhost";
        int port = 60000;
        clientS = new Socket(host, port);
        Runnable run;
        Thread thread;
        
        //FLUX DE SORTIDA AL SERVIDOR
        PrintWriter fsortida = new PrintWriter(clientS.getOutputStream(), true);

        //FLUX D'ENTRADA AL SERVIDOR
        BufferedReader fentrada = new BufferedReader(new InputStreamReader(clientS.getInputStream()));

        //FLUX PER A ENTRADA ESTÀNDARD
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String cadena, eco;

        System.out.println("***********************************");
        System.out.println("              XAT");
        System.out.println("\n//log tuNombre");
        System.out.println("//exit\n");
        System.out.println("***********************************");
        
        System.out.println("Inserta el teu Nom");
        login = in.readLine();
          
        while (login == null || !login.startsWith("//log ")) {           
            System.out.println("Comanda incorrecta!");
            login = in.readLine();
            
        }
        
        login = login.replace("//log ", "");
          
        run = new ClientText(clientS);
        thread = new Thread(run);
        thread.start();
        
        fsortida.println(login);
                
        System.out.println("Usuari: " + login + " ha iniciat sessió" + "\n");
          
        do {
            cadena = in.readLine();

            //Enviament cadena al servidor
            fsortida.println(login + ": " + cadena);

        } while (!cadena.equals("//exit") && !cadena.equals(""));

        fsortida.close();
        fentrada.close();
        System.out.println("Finalització de l'enviament...");
        in.close();
        clientS.close();
      }

    @Override
    public void run() {
        BufferedReader fentrada = null;
        String eco = "";
        boolean finish = false;
        try {
            //FLUX D'ENTRADA AL SERVIDOR
            fentrada = new BufferedReader(new InputStreamReader(clientS.getInputStream()));
            while (!finish) {
                try {
                    eco = fentrada.readLine();
                } catch (NullPointerException e) {
                    
                }
                
                if (eco.equals("//exit")) {
                    finish = true;
                } else {
                    if (!eco.startsWith(login) && (!eco.contains("//exit"))) {
                        System.out.println(eco);
                    }
                }
                
            }
        } catch (IOException ex) {
            
        } finally {
            try {
                fentrada.close();
            } catch (IOException ex) {
               
            }
        }   
    }
}
