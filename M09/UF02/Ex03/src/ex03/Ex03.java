/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex03;


/**
 *
 * @author poved
 */

public class Ex03 {
    
    static long NUM = 13;

    public static void main(String[] args){
        
        
        System.out.println(calculaFibonacci(NUM));
        
    }    
    
    public static long calculaFibonacci(long numero) {
        double calcul = java.lang.Math.cos(54879854);
        if (numero==0) { return 0; }
        else if (numero==1) { return 1; }
        else {
            return (calculaFibonacci(numero-2) + calculaFibonacci(numero-1));
        }
    }
}
