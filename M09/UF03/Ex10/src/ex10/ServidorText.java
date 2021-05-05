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

    // CANTIDAD DE CLIENTES TOTALES
    static int totalCLients;
    
    // IDENTIFICADOR DEL CLIENTE
    static int numClient;
    
    //CLIENTES
    static Socket client;
    static Socket[] totalClients;
    
    //SERVER
    ServerSocket server;
    
    // GUARDAR MENSAJE RECIBIDO DEL CLIENTE
    String cadena = "";
    
    //SCANNER DEL TECLAT
    static Scanner teclado = new Scanner(System.in);
    

    public ServidorText(Socket clientConnectat, ServerSocket server, Socket[] totalClients) {
	this.client = clientConnectat;
	this.server = server;	
        this.totalClients = totalClients;
        this.numClient ++;
    }
        
    public static void main (String[] args) throws IOException {

        //PORT DEL SERVIDOR
        int numPort = 60000;
        
        //INICIAR EL SERVIDOR
        ServerSocket servidor = new ServerSocket(numPort);

        //DEMANEM EL MAXIM DE CLIENT
        System.out.print("Clients totals: ");
        totalCLients = teclado.nextInt();
        
        totalClients = new Socket[totalCLients];
        
        //INICIEM ELS FILS
        ServidorText[] arrayRunnable = new ServidorText[totalCLients];
        Thread[] arrayThread = new Thread[totalCLients];
        
        //Booleans
        boolean isNull;
        boolean clientStop;
	
        // Determinem les vegades que es conectaran els clients
        for (int i = 0; i < arrayRunnable.length; i++) {

            isNull = true;
            clientStop = false;
            
            try {
                client = servidor.accept();
            } catch (SocketException e) {
                isNull = false;
            }
            
            for (int j = 0; j <totalClients.length; j++) {
                
                if (totalClients[i] == null && clientStop == false) {
                    
                    totalClients[i] = client;
                    clientStop = true;
                    
                }
                
            }

            if(isNull) {
                // Runnable
                arrayRunnable[i] = new ServidorText(client, servidor, totalClients);

                // Thread
                arrayThread[i] = new Thread(arrayRunnable[i]);
                arrayThread[i].start();
            }
            
            

        }
    }

    @Override
    public void run() {

        try {
            PrintWriter fsortida = null;
            BufferedReader fentrada = null;

            System.out.println("Esperant connexió... ");
            System.out.println("Client " + this.numClient + " connectat... ");

            boolean estadoServer = true;

            while (estadoServer) {
                
                try {
                    //FLUX DE SORTIDA AL CLIENT
                    fsortida = new PrintWriter(this.client.getOutputStream(), true);

                    //FLUX D'ENTRADA DEL CLIENT
                    fentrada = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
                    
                    if ((cadena = fentrada.readLine()) != null) {
                        cadena = cadena.replace("//log ", "");
                        System.out.println("Nom: " + cadena);
                    }
                } catch (SocketException e) {
                    estadoServer = false;
                }
                
                while (estadoServer) {
                    
                    try {
                        cadena = fentrada.readLine();
                        
                    } catch (SocketException e) {estadoServer = false;}
                    
                    if (cadena.equals("//exit")) 
                        estadoServer = false;
                    
                    if (estadoServer) {
                        
                        if (cadena != null) {
                            for (int i = 0; i < totalClients.length; i++) {
				if (totalClients[i] != null) {
                                    
                                    try {
                                        fsortida = new PrintWriter(this.totalClients[i].getOutputStream(), true);
                                        fsortida.println(cadena);
                                    } catch (SocketException e) {
                                        
                                    }
				}
                            }

			System.out.println("Reben :" + cadena);
                        
                        }
                    }
                        
                }
                    
            }
            
            fentrada.close();
            fsortida.close();
            this.client.close();
            this.server.close();
			
        } catch (IOException s) {
            
        }
    }
}