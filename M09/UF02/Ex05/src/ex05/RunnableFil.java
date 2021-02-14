/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex05;


public class RunnableFil implements Runnable {

    String strImprimir;
    
    public RunnableFil(String strP) {
        strImprimir = strP;
    }
    
    public void run() {
        for (int i = 0; i < 5; i++){
            System.out.println(strImprimir + " (" + i +")");
        }
          
    }
    
    public static void main(String[] args) {
        
        RunnableFil objRunnable1 = new RunnableFil("Fil 1 -");
        RunnableFil objRunnable2 = new RunnableFil("Fil 2 -");
        RunnableFil objRunnable3 = new RunnableFil("Fil 3 -");
        RunnableFil objRunnable4 = new RunnableFil("Fil 4 -");
        RunnableFil objRunnable5 = new RunnableFil("Fil 5 -");
        RunnableFil objRunnable6 = new RunnableFil("Fil 6 -");
        RunnableFil objRunnable7 = new RunnableFil("Fil 7 -");
        RunnableFil objRunnable8 = new RunnableFil("Fil 8 -");
        RunnableFil objRunnable9 = new RunnableFil("Fil 9 -");
        RunnableFil objRunnable10 = new RunnableFil("Fil 10 -");
              
        
        Thread primer = new Thread(objRunnable1);
        Thread segon = new Thread(objRunnable2);
        Thread tercer = new Thread(objRunnable3);
        Thread quart = new Thread(objRunnable4);
        Thread cinque = new Thread(objRunnable5);
        Thread sise = new Thread(objRunnable6);
        Thread sete = new Thread(objRunnable7);
        Thread vuite = new Thread(objRunnable8);
        Thread nove = new Thread(objRunnable9);
        Thread dese = new Thread(objRunnable10);
        
        
        primer.run();
        segon.run();
        tercer.run();
        quart.run();
        cinque.run();
        sise.run();
        sete.run();
        vuite.run();
        nove.run();
        dese.run();
        
        System.out.println("Final Fil Principal");
        
    }
    
}
