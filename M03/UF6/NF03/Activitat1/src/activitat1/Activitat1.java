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
        String numerator = "";
        int num = 0;
        String divisor = "";
        int div = 0;
        int res = 0;
        boolean sortir = true;
             
        while (sortir) {
            
            try {
            System.out.print("Enter the numerator: ");
            numerator = teclat.nextLine();
            
                if (((numerator.charAt(0) == 'q') || (numerator.charAt(0) == 'Q')) && (numerator.length() == 1)) {
                    sortir = false;
                } else {
                    num = Integer.parseInt(numerator); 

                    System.out.print("Enter the divisor: ");
                    divisor = teclat.nextLine();

                    div = Integer.parseInt(divisor);

                    res = num / div;
                    System.out.println(num + " / " + div + " is " + res + "\n");

                }
            } catch (NumberFormatException e) {
                System.out.println("You entered bad data.");
                System.out.println("Please try again.\n");
            } catch (ArithmeticException e) {
                System.out.println("You can't divide " + num + " by 0\n");
            }
        }
    }
}
