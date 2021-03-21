
package activitat2;

import java.util.Scanner;


public class Activitat2 {

    public static void main(String[] args) {
                
        calcul(1, 10);


    }
    
    public static void calcul(int fila, int pedres) {
        if (fila == pedres) {
            System.out.println(fila + ", " + 0);
        } else if (fila > pedres) {
            System.out.println((fila - 1) + ", " + pedres);
        } else if (fila < pedres) {
            calcul(fila + 1, pedres - fila);
        } 
    }
    
}
