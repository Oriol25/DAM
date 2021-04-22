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
import java.util.Scanner;


/**
 *
 * @author Oriol
 */
public class OmplirVenda {
    public static void main (String[] args) {
        
        Scanner teclat = new Scanner(System.in);
        
        int idproducte;
        int quantitat;
        
        try {
            idproducte = Integer.parseInt(args[0]);
            quantitat = Integer.parseInt(args[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.print("ID del producte: ");
            idproducte = teclat.nextInt();
            System.out.print("Quantitat: ");
            quantitat = teclat.nextInt();
            System.out.println("");
        }
        
        ODB odb = ODBFactory.open("Producte_com.BD");
        IQuery query = new CriteriaQuery(Producte.class, 
                Where.equal("idproducte", idproducte));
        Objects<Producte> objectes = odb.getObjects(query);
        
        try {
            Producte pro = (Producte) objectes.getFirst();
            System.out.println("ID => " + idproducte + " : " + pro.getDescripcio() +
                    "\n\t*ESTOC-ACT: " + pro.getStockactual() + "\n\t*PVP: " + pro.getPvp() +
                    "\n\t*ESTOC-MIN: " + pro.getStockminim() + "\n");
            
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
    
    private static boolean actualitzaEstoc (Producte producte, ODB odb, int quantitat) {
        
        Comanda comanda = new Comanda();
        java.sql.Date dataActual = getCurrentDate();
        producte.addPropertyChangeListener(comanda);
        int nouEstoc = producte.getStockactual() - quantitat;
        
        boolean actualitzar = false;
        
        producte.setStockactual(nouEstoc);
        
        if (comanda.isDemana()) {
            System.out.println("FER COMANDA EN PRODUCTE: "
            + producte.getDescripcio() + " QUANTITAT " + quantitat);
            
            int numComanda = obtenirNumComanda(odb);
            comanda.setQuantitat(quantitat);
            comanda.setIdproducte(producte.getIdproducte());
            comanda.setNumcomanda(numComanda);
            comanda.setData(dataActual);
            odb.store(comanda);
            
            System.out.println("COMANDA " + numComanda + " GENERAT...");
            
        } else {
            odb.store(producte);
            System.out.println("ESTOC ACTUALITZAT");
            actualitzar = true;
        }
        
        return actualitzar;
    }
    
    private static int obtenirNumComanda(ODB odb) {
        Values val4 = odb.getValues(new
            ValuesCriteriaQuery(Comanda.class).max("numcomanda", "com_max"));
        ObjectValues ov4 = val4.nextValues();
        BigDecimal maxim = (BigDecimal) ov4.getByAlias("com_max");
        
        return maxim.intValue()+1;
    }
    
    private static int obtenirNumVenda (ODB odb) {
        Values val4 = odb.getValues(new 
            ValuesCriteriaQuery(Venda.class).max("numvenda", "ven_max"));
        ObjectValues ov4 = val4.nextValues();
        BigDecimal maxim = (BigDecimal) ov4.getByAlias("ven_max");
        
        return maxim.intValue()+1;
    }
    
    private static java.sql.Date getCurrentDate() {
        java.util.Date avui = new java.util.Date();
        
        return new java.sql.Date(avui.getTime());
    }
}
