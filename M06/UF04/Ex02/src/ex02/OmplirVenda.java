/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex02;

import java.math.BigDecimal;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

import ElsMeusBeans.Producte;
import ElsMeusBeans.Comanda;
import ElsMeusBeans.Venda;


/**
 *
 * @author Alumne
 */
public class OmplirVenda {
    public static void main (String[] args) {
        int idproducte = 2;
        int quantitat = 4;
        
        ODB odb = ODBFactory.open("Producte_com.BD");
        IQuery query = new CriteriaQuery(Producte.class, 
                Where.equal("idproducte", idproducte));
        Objects<Producte> objectes = odb.getObjects(query);
        
        try {
            Producte pro = (Producte) objectes.getFirst();
            System.out.println("ID=>" + idproducte + " : " + pro.getDescripcio() +
                    "*ESTOC-ACT: " + pro.getStockactual() + "*PVP: " + pro.getPvp() +
                    "*ESTOC-MIN: " + pro.getStockminim());
            
            if (quantitat > 0) {
                java.sql.Date dataActual = getCurrentDate();
                System.out.println("QUANTITAT A VENDRE " + quantitat);
                
                if (actualitzaEstoc(pro, odb, quantitat)) {
                    int numVenda = obtenirNumVenda(odb);
                    Venda ven = 
                            new Venda(numVenda, idproducte, dataActual, quantitat);
                    odb.store(ven);
                    System.out.println("VENDA: " + numVenda + " INSERIA...");
                    
                } else {
                    System.out.println("VENDA NO INSERIDA - NO HI HA ESTOC...");
                }
            } else {
                System.out.println("NO EXISTEIX EL PRODUCTE");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("NO EXISTEIX EL PRODUCTE");
        } finally {
            odb.close();
        }
    }
    
    
    
}
