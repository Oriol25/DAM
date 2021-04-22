/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex02;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import ElsMeusBeans.Producte;

/**
 *
 * @author Oriol
 */
public class OmplirProductes {
    
    public static void main(String[] args) {
       ODB odb = ODBFactory.open("Producte_com.BD");
    
    Producte p1 = new Producte(1, "Manaza",     10, 3, 10);
    Producte p2 = new Producte(2, "Pera",       5, 2, 5);
    Producte p3 = new Producte(3, "Coco",       8, 3, 20);
    Producte p4 = new Producte(4, "Platano",    6, 2, 12);
    Producte p5 = new Producte(5, "Fresa",      9, 4, 6);
    Producte p6 = new Producte(6, "Melocoton",  10, 5, 9);
    
    odb.store(p1);
    odb.store(p2);
    odb.store(p3);
    odb.store(p4);
    odb.store(p5);
    odb.store(p6);
 
    odb.close();    
    }
       
    
}
