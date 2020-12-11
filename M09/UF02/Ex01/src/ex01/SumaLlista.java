package ex01;

import java.util.*;
import java.util.concurrent.*;

public class SumaLlista {
    
        //Se crea otra clase donde hay las funciones para sumar
    static class Suma implements Callable<Integer> {
	//variables globales
        private int operador1;
	private int operador2;
        
        //constructor de la clase Suma
	public Suma(int operador1, int operador2) {
            this.operador1 = operador1;
            this.operador2 = operador2;
	}
		
        //metodo para hacer la suma
	@Override
	public Integer call() throws Exception {
            return operador1 + operador2;
	}
    }

    public static void main(String[] args) throws
            InterruptedException, ExecutionException {
                        
        //Variables locales (Para hilos y arrays con la lista de tascas)                
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
        List<Suma> llistaTasques= new ArrayList<Suma>();
                        
        //Lanza 25 tascas donde sumara numeros aleatorios
        for (int i = 0; i < 25; i++) {
            Suma calcula = new Suma((int)(Math.random()*10), (int)(Math.random()*10));
            llistaTasques.add(calcula);
        }
                        
        /*Crea una array de lista para ejecutar la lista con las tascas 
            donde se han guardado las sumas*/
        List <Future<Integer>> llistaResultats;
        llistaResultats = executor.invokeAll(llistaTasques);
			
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
