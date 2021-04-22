/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex02;

import org.neodatis.odb.ODBFactory;

/**
 *
 * @author Alumne
 */
public class OmplirProductes {
    
    ODB odb = ODBFactory.open("Producte_com.BD");
    Producte p1 = Producte(1, "Barcelona, Una biografia", 10, 3, 160);
    Producte p2 = Producte(1, "La magia de l'ordre", 5, 2, 176);
    Producte p3 = Producte(1, "Tot es al teu cap", 20, 5, 193);
    Producte p4 = Producte(1, "Filosofia inacabada", 8, 3, 85);
    Producte p5 = Producte(1, "La resistencia intima", 7, 1, 159);
    Producte p6 = Producte(1, "El capital al segle xxi", 5, 2, 80);
    
    
}
