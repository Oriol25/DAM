/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServidorText implements Runnable {

    Socket client;
    ServerSocket server;
    static int numClient;
    String cadena = "";
    String name;

    public ServidorText(Socket clientConnectat, ServerSocket server) {
	this.client = clientConnectat;
	this.server = server;
	this.numClient ++;
        this.name = null;
    }
        
    public static void main (String[] args) throws IOException {

        Scanner teclado = new Scanner(System.in);

        int numPort = 60000;
        ServerSocket servidor = new ServerSocket(numPort);

        System.out.print("Clients totals: ");
        int numClients = teclado.nextInt();

        Runnable[] arrayRunnable = new Runnable[numClients];
        Thread[] arrayThread = new Thread[numClients];
	
        // Determinem les vegades que es conectaran els clients
        for (int i = 0; i < arrayRunnable.length; i++) {

            Socket clientConnectat = servidor.accept();

            // Runnable
            arrayRunnable[i] = new ServidorText(clientConnectat, servidor);

            // Thread
            arrayThread[i] = new Thread(arrayRunnable[i]);
            arrayThread[i].start();

        }
    }

    @Override
    public void run() {

        try {
            PrintWriter fsortida = null;
            BufferedReader fentrada = null;

            System.out.println("Esperant connexió... ");
            System.out.println("Client " + this.numClient + " connectat... ");

            //FLUX DE SORTIDA AL CLIENT
            fsortida = new PrintWriter(this.client.getOutputStream(), true);


            //FLUX D'ENTRADA DEL CLIENT
            fentrada = new BufferedReader(new InputStreamReader(this.client.getInputStream()));


            while ((cadena = fentrada.readLine()) != null) {

                fsortida.println(cadena);
                
                if (cadena.startsWith("//log ")) {
                    this.name = cadena.substring(6, cadena.length()).toString();
                    System.out.println("Usuario: " + this.name + " logueado");
                    
                } else if (this.name != null) {
                    System.out.println("Rebent (" + this.name +"): " + cadena);              
                } else {
                    // USUARIO NO LOGUEADO
                    System.out.println("Usuario no logueado: " + this.numClient);
                }

            }
                          
            fentrada.close();
            fsortida.close();
            this.client.close();
            this.server.close();
			
	} catch (IOException e) {
            e.printStackTrace();
        }
    }
}
