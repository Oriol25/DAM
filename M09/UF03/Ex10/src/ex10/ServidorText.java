
package ex10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class ServidorText implements Runnable {

    static Socket client;
    ServerSocket server;
    static int numClient;
    static int numClients;
    String cadena = "";
    String name;
    static PrintWriter fsortides[];

    public ServidorText(Socket clientConnectat, ServerSocket server) {
        this.numClient ++;
	this.client = clientConnectat;
	this.server = server;	
        this.name = null;
    }
        
    public static void main (String[] args) throws IOException {

        Scanner teclado = new Scanner(System.in);

        int numPort = 60000;
        ServerSocket servidor = new ServerSocket(numPort);

        System.out.print("Clients totals: ");
        numClients = teclado.nextInt();
        
        fsortides = new PrintWriter[numClients];

        Runnable[] arrayRunnable = new Runnable[numClients];
        Thread[] arrayThread = new Thread[numClients];
	
        // Determinem les vegades que es conectaran els clients
        for (int i = 0; i < arrayRunnable.length; i++) {

            Socket clientConnectat = servidor.accept();

            // Runnable
            arrayRunnable[i] = new ServidorText(clientConnectat, servidor);
            fsortides[i] = new PrintWriter(ServidorText.client.getOutputStream(), true);

            // Thread
            arrayThread[i] = new Thread(arrayRunnable[i]);
            arrayThread[i].start();

        }
    }

    @Override
    public void run() {

        try {
//            PrintWriter fsortida = null;
            BufferedReader fentrada = null;

            System.out.println("Esperant connexió... ");
            System.out.println("Client " + this.numClient + " connectat... ");

            //FLUX DE SORTIDA AL CLIENT
//            fsortida = new PrintWriter(this.client.getOutputStream(), true);


            //FLUX D'ENTRADA DEL CLIENT
            fentrada = new BufferedReader(new InputStreamReader(this.client.getInputStream()));


            while ((cadena = fentrada.readLine()) != null) {

                for (int i = 0; i < numClients; i++) {
                    if (cadena.startsWith("//log ")) {
                        this.name = cadena.substring(6, cadena.length()).toString();
                        System.out.println("Usuario: " + this.name + " logueado");
                    
    //                    fsortida.println("Bienvenido " + this.name);
                        fsortides[i].println("Bienvenido " + this.name);

                    } else if (this.name != null) {
                        System.out.println("Rebent (" + this.name +"): " + cadena); 
    //                    fsortida.println(cadena);
                            fsortides[i].println(this.name + ": " + cadena);
                    } else {
                        // USUARIO NO LOGUEADO
                        System.out.println("Usuario no logueado: " + this.numClient);
    //                    fsortida.println("Inicia Sesion primero");
                    }
                    
                }

            }
                          
            fentrada.close();
//            fsortida.close();
            this.client.close();
            this.server.close();
			
	} catch (IOException e) {
            e.printStackTrace();
        }
    }
}