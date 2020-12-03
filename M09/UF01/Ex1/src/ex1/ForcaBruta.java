
package ex1;

import java.util.Scanner;

/**
 *
 * @author Oriol Poveda
 */
public class ForcaBruta {
    
    public static void main(String[] args) {
        
        Scanner teclat = new Scanner(System.in);
        
        char abc[] = {97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,32};
        String frase = "";
        int espai = abc.length; //desp
        int es = 0; //avantdesp
        int conta = 0;
        
        System.out.println("Introduiex la frase encriptada");
        frase = teclat.nextLine();
        
        while (conta != abc.length) {
            for(int i = 0; i < frase.length(); i++){
                for (int k = 0; k < abc.length; k++) {
                    if (abc[k] == frase.charAt(i)) {
                        if ((k - espai) < 0) {
                            es = espai - k;
                            System.out.print(abc[abc.length - es]);
                   
                        } else {
                            System.out.print(abc[k - espai]);
                        }
                    }
                }
            }
            
            System.out.println();
            conta++;
            espai--;    
        }
        
        teclat.close();
        
    }
}
