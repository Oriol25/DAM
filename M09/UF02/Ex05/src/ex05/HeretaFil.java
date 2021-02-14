
package ex05;


public class HeretaFil extends Thread {

    String strImprimir;
    
    public HeretaFil(String strP) {
        strImprimir = strP;
    }
    
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(strImprimir + " (" + i + ")");
        }
    }
    
    public static void main(String[] args) {
       Thread primer = new HeretaFil("Fil 1 - ");
       Thread segon = new HeretaFil("Fil 2 - ");
       Thread tercer = new HeretaFil("Fil 3 - ");
       Thread quart = new HeretaFil("Fil 4 - ");
       Thread cinque = new HeretaFil("Fil 5 - ");
       Thread sise = new HeretaFil("Fil 6 - ");
       Thread sete = new HeretaFil("Fil 7 - ");
       Thread vuite = new HeretaFil("Fil 8 - ");
       Thread nove = new HeretaFil("Fil 9 - ");
       Thread dese = new HeretaFil("Fil 10 - ");
       
       primer.start();
       segon.start();
       tercer.start();
       quart.start();
       cinque.start();
       sise.start();
       sete.start();
       vuite.start();
       nove.start();
       dese.start();
       
       /*primer.run();
       segon.run();
       tercer.run();
       quart.run();
       cinque.run();
       sise.run();
       sete.run();
       vuite.run();
       nove.run();
       dese.run();*/
       
       System.out.println("Final Fil Princial");
        
    }
    
}
