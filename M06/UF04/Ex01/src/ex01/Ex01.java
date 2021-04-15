package ex01;

import ElsMeusBeans.Comanda;
import ElsMeusBeans.Producte;

public class Ex01 {


    public static void main(String[] args) {

        // Creo una array de productos
        Producte[] producte = new Producte[5];
        
        // Asigno a cada posicion de la array un producto
        producte[0] = new Producte(1, "Manzana",    10, 3, 10);
        producte[1] = new Producte(2, "Pera",       5, 4, 5);
        producte[2] = new Producte(3, "Coco",       8, 7, 20);
        producte[3] = new Producte(4, "Platano",    6, 6, 12);
        producte[4] = new Producte(5, "Fresa",      9, 4, 6);
        
        //Creo el pedido
        Comanda comanda = new Comanda();
        
        for (int i = 0; i < producte.length; i++) {
            
            // Añado los productos en la comanda
            producte[i].addPropertyChangeListener(comanda);
            
            System.out.print("Producte: " + producte[i].getDescripcio() + " ");
            System.out.print(producte[i].getStockactual() + " - ");
            System.out.println(producte[i].getStockminim());
                 
            producte[i].setStockactual(3);
            System.out.println("Stock actual modificat " + producte[i].getStockactual() + " ");
            
            if (comanda.isDemana()) {
                System.out.println("Fer comanda en producte: " + 
                    producte[i].getDescripcio());
            } else {
                System.out.println("No es necessari fer la comanda en prodcute: " + 
                    producte[i].getDescripcio());
            }
            
            System.out.println("");
            
        }
        
    }
    
}
