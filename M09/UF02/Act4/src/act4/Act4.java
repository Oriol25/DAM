/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package act4;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

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
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(FILS);
                        
        //Lanza 25 tascas donde sumara numeros aleatorios
        for (int i = 0; i < CLIENTS; i++) {
            Client client = new Client(i);
            executor.execute(client);
            Thread.sleep(3000);
  
        }
    }
}
