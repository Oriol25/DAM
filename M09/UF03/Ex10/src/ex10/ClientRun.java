/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JTextArea;

/**
 *
 * @author poved
 */
public class ClientRun  extends Thread{
    
    private BufferedReader fentrada;
    private JTextArea taChat;
    Socket client;
    
    static String cadena;
    
    public ClientRun (BufferedReader fentrada, JTextArea taChat, Socket client) {
        this.fentrada = fentrada;
        this.taChat = taChat;
        this.client = client;
    }
    
    @Override
    public void run() {
        try {
            
            while ((cadena = fentrada.readLine()) != null) {
                fentrada = new BufferedReader(new InputStreamReader(client.getInputStream()));
                System.out.println(cadena);
                
                if (cadena != null) {
                    System.out.println(cadena);
                } else {
                    System.out.println("null");
                }
                
                if (!cadena.startsWith("//")) {
                    taChat.setText(taChat.getText() + "\n" + cadena);
                }
                

            }
        } catch (IOException ex) {
            
        }

    }
    
    
}
