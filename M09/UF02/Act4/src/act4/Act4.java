/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package act4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Ayman Kaddar y Oriol Poveda
 */
public class Act4 {
    
    //Se crea otra clase donde hay las funciones para sumar
    static class Client implements Callable<Integer> {
	//variables globales
        private int operador1 = 1;
        
        //constructor de la clase Suma
	public Client(int operador1) {
            this.operador1 = operador1;
	}
		
        //metodo para hacer la suma
	@Override
	public Integer call() throws Exception {
            return operador1;
	}
    }
    
        public static int FILS = 4;
    public static int CLIENTS = 50;
    
    public static void main(String[] args) throws
            InterruptedException, ExecutionException  {
        
        
        //Variables locales (Para hilos y arrays con la lista de tascas)                
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        List<Client> llistaClients= new ArrayList<Client>();
        int articles = 0;
        int temps = 0; 
                        
        //Lanza 25 tascas donde sumara numeros aleatorios
        for (int i = 0; i < 50; i++) {
            Client client = new Client(i+1);
            llistaClients.add(client);
            
            articles = (int)(Math.random()*30) + 1;
                            
            System.out.println("Creat el client " + (i+1) + " amb " + articles + " articles.");
            
            for (int j = 0; j < articles; j++){
                temps = (int)(Math.random()*7) + 2;
                System.out.println(temps);
            }
            //Thread.sleep(3000);
  
        }
        
        /*Crea una array de lista para ejecutar la lista con las tascas 
            donde se han guardado las sumas*/
        List <Future<Integer>> llistaResultats;
        llistaResultats = executor.invokeAll(llistaClients);
			
        //Se espera que se terminen los hilos
        executor.shutdown();
			
        //Muestra el resultado
        for (int i = 0; i < llistaResultats.size(); i++) {
            Future<Integer> resultat = llistaResultats.get(i);
            try {
                System.out.println("Resultat tasca "+i+ " és:" +
                resultat.get());
            } catch (InterruptedException | ExecutionException e) {
					
            }
        }
    }
}
