/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package act4;

/**
 *
 * @author poved
 */
public class Act4_3arecursiva {
    
    public static int operacio(int a, int b) {
        if (a == 0) {
            return 1;
        } else {
            return operacio(a-1, b) * b;
        }
    }
    
    public static void main(String[] args) {
        System.out.println(operacio(4,8));
        
    }
}
