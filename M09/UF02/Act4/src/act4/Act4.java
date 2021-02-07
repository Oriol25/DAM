/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package act4;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 *
 * @author Ayman Kaddar y Oriol Poveda
 */
public class Act4 {
    
    static class Client implements Runnable {
        private int cliente = 1;
        private int articles = (int) (Math.random()*30 + 1);
        
        //constructor de la clase Client
	public Client(int cliente) {
            this.cliente = cliente;
            System.out.println("Creat el client " + cliente + " amb " + articles + " articles");   
	}
		
        //metodo run
        @Override
        public void run() {
            int sec = 1000;
            int temps;
            
            System.out.println("Client " + cliente + " passa per caixa...");
            
            for (int i = 1; i < articles; i++) {
                temps = (int) (Math.random()* 7 + 2);
                try {
                    Thread.sleep(temps * sec);
                }  catch (InterruptedException ex) {
                    System.err.println(ex);
                }
                System.out.println("Client " + cliente + " article " + i + "/ " + articles + " (" + temps + " segons)");
                
                if (i == articles) {
                    System.out.println(" FINALITZAT");
                }             
            }            
        }
    }
    
    public static int TOTALCLIENTS = 50;
    public static int MILISEC = 3000;
    public static int HILOS = 5;
    
    public static void main(String[] args) throws
            InterruptedException, ExecutionException  {
       
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool (5);
        
        for(int i = 0; i <= TOTALCLIENTS; i++) {
            Client client = new Client(i);
            Thread.sleep(MILISEC);
            executor.execute(client);
        }
       
        executor.shutdown();
     
    }
}
