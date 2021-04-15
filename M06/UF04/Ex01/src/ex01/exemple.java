
package ex01;

import ElsMeusBeans.Comanda;
import ElsMeusBeans.Producte;

public class exemple {
    
  public static void main(String[] args) {

        Producte producte = new Producte(1, "Portable MSI USB 3.0", 10, 3, 50);
        Comanda comanda = new Comanda();
        
        producte.addPropertyChangeListener(comanda);
        // Es canvia l'estoc actual, se li dona valor 2
        producte.setStockactual(2);
        
        if (comanda.isDemana()) {
            System.out.println("Fer comanda en producte: " + 
                    producte.getDescripcio());
        } else {
            System.out.println("No es necessari fer la comanda en prodcute: " + 
                    producte.getDescripcio());
        }

    }
}
