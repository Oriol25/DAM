/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex03;

import ElsMeusBeans.BaseDades;
import ElsMeusBeans.Comanda;
import ElsMeusBeans.Producte;
import ElsMeusBeans.Venda;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Oriol
 */
public class Exemple {

    public static void main(String[] args) {
        
        Scanner teclat = new Scanner(System.in);
        
        String urldb = "jdbc:mysql://localhost:3306/fruteria";
        String usuari = "root";
        String contrasenya = "";
        String driver = "com.mysql.jdbc.Driver";
        
        int producte;
        int quantitat;
        
        BaseDades bd = new BaseDades(urldb, usuari, contrasenya, driver);
        bd.setCrearConnexio();
        
        if (args.length > 0) {
            producte = Integer.parseInt(args[0]);
            quantitat = Integer.parseInt(args[1]);
            
        } else {
            System.out.print("ID DEL PRODUCTE: ");
            producte = teclat.nextInt();
            System.out.print("QUANTITAT: ");
            quantitat = teclat.nextInt();
            
        }
        
        if (bd.getCrearConnexio()) {
            System.out.println("Connectat");
            
            System.out.println("====================================");
            System.out.println("LLISTA INICIAL DE PRODUCTES");
            VeureProductes(bd);
            
            //Crear una venda
            System.out.println("====================================");
            System.out.println("ES CREA VENDA DE ID " + producte + " AMB QUANTITAT " + quantitat);
            CrearVenda(bd, producte, quantitat);
            
            System.out.println("====================================");
            System.out.println("LLISTA PRODUCTES DESPRES DE CREAR VENDA");
            VeureProductes(bd);
            
            System.out.println("====================================");
            System.out.println("LLISTA VENDES");
            VeureVendes(bd);
            
            System.out.println("====================================");
            System.out.println("LLISTA DE COMANDES");
            VeureComandes(bd);
            
        } else {
            System.out.println("NO connectat");
        }
        
        bd.tancarConnexio();

    }
    
    private static void VeureProductes(BaseDades bd) {
        ArrayList<Producte> llista = new ArrayList <Producte>();
        llista = bd.consultaPro("SELECT * FROM PRODUCTE");
        if (llista != null) {
            for (int i = 0; i < llista.size(); i++) {
                Producte p = (Producte) llista.get(i);
                System.out.println("ID = > " + p.getIdproducte() + ": "
                    + p.getDescripcio() + "* Estoc: " + p.getStockactual()
                    + "* Pvp: " + p.getPvp() + " Estoc Minim: " 
                    + p.getStockminim());
            }
        }
    }
    
    private static void CrearVenda (BaseDades bd, int idproducte, int quantitat) {
        Producte prod = bd.consultarUnProducte(idproducte);
        java.sql.Date dataActual = getCurrentDate();
        if (prod != null) {
            if (bd.actualitzarStock(prod, quantitat, dataActual) > 0) {
                String taula = "VENDES";
                int idvenda = bd.obtenirUltimID(taula);
                Venda ven = new 
                    Venda(idvenda, prod.getIdproducte(), dataActual, quantitat);
                
                if (bd.inserirVenda(ven) > 0) {
                    System.out.println("VENDA INSERIDA...");
                }
            } else {
                System.out.println("NO ES POT FER LA VENDA, NO HI HA ESTOC...");
            }
        } else {
            System.out.println("NO HI HA EL PRODUCTE");
        }
    }
    
    private static void VeureComandes(BaseDades bd) {
        ArrayList<Comanda> llista = new ArrayList<Comanda>();
        llista = bd.consultaCom("SELECT * FROM COMANDES");
        
        if (llista != null) {
            for (int i = 0; i < llista.size(); i++) {
                Comanda c = (Comanda) llista.get(i);
                Producte prod = bd.consultarUnProducte(c.getIdproducte());
                System.out.println("ID Comanda => " + c.getNumcomanda()
                + "* Producte: " + prod.getDescripcio() + " * Quantitat: "
                + c.getQuantitat() + "* Data: " + c.getData());
            }
        }
    }
    
    private static void VeureVendes(BaseDades bd) {
        ArrayList<Venda> llista = new ArrayList<Venda>();
        llista = bd.consultaVen("SELECT * FROM VENDES");
        if (llista != null) {
            for (int i = 0; i < llista.size(); i++) {
                Venda p = (Venda) llista.get(i);
                Producte prod = bd.consultarUnProducte(p.getIdproducte());
                System.out.println("ID VENDA => " + p.getNumvenda() + "* Producte: "
                + prod.getDescripcio() + " * QUANTITAT: " + p.getQuantitat()
                + "* DATA: " + p.getDatavenda());
            }
        }
    }
    
    private static java.sql.Date getCurrentDate() {
        java.util.Date avui = new java.util.Date();
        return new java.sql.Date(avui.getTime());
    }
    
}
