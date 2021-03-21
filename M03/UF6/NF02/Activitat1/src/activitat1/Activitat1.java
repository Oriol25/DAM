/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activitat1;

import java.util.Scanner;


public class Activitat1 {

    public static void main(String[] args) {

        Scanner teclat = new Scanner(System.in);
        int total = 0;
        int num;
        
        System.out.print("Digues un número: ");
        num = teclat.nextInt();
        
        total += calcul(num);
        
        System.out.println(total);
        
    }
    
    public static int calcul(int num) {
        if (num == 1) {
            return 4;
        } else {
            return 4 * num + 4 * calcul(num / 2);
        }
    }
    
}
