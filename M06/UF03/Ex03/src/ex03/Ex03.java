
package ex03;

import java.util.Scanner;
import org.xmldb.api.*;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;


public class Ex03 {
    
    // Driver per a eXist
    static String driver = "org.exist.xmldb.DatabaseImpl";
    // Col·lecció
    static Collection col = null;
    // URI col·leccio
    static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db";
    // Usuari
    static String usu = "admin";
    // Contrasenya
    static String usuPass = "alumne";
        
    static XPathQueryService service;
    
    static Scanner teclat = new Scanner(System.in);

    public static void main(String[] args) throws XMLDBException {
        
        int opcio = 0;
        
        try {
            Class cl = Class.forName(driver);
            Database database = (Database) cl.newInstance();
            DatabaseManager.registerDatabase(database);
        } catch(Exception e) {
            System.out.println("Error en iniciar eXistdb" + e);
        }
        
        col = DatabaseManager.getCollection(URI, usu, usuPass);
        
        service =(XPathQueryService) col.getService("XPathQueryService", "1.0");
        
        if (col == null){
            System.out.println("la coleccio no existeix");
        }        
        
        do {
            
            System.out.println("1 - Inserir");
            System.out.println("2 - Modificar");
            System.out.println("3 - Eliminar");
            System.out.println("0 - Sortir");
            System.out.print("Opcio: ");
            opcio = teclat.nextInt();
            
            switch (opcio) {
                case 1: inserir();
                    break;
                case 2: modificar();
                    break;
                case 3: eliminar();
                    break;
                default:
                    break;
            }
            
            
        } while (opcio != 0);
        
    }
    
    public static void inserir() throws XMLDBException {
        
        int numDep = 0;
        String nomDep = "";
        String localDep = "";
        ResourceSet result;
        
        System.out.print("\nNumero del departament: ");
        numDep = teclat.nextInt();
            
        System.out.print("Nom del departament: ");
        nomDep = teclat.next();
            
        System.out.print("Localitat del departament: ");
        localDep = teclat.next();
        
        ResourceSet result2 = service.query("for $dep in /departamentos/DEP_ROW[DEPT_NO = " + numDep + "] return $dep");
        
        ResourceIterator i;
        i = result2.getIterator();        
        if (!i.hasMoreResources()){
           result = service.query("update insert <DEP_ROW><DEPT_NO>"+ numDep + "</DEPT_NO><DNOMBRE>" + nomDep + "</DNOMBRE><LOC>"+ localDep + "</LOC></DEP_ROW> into /departamentos");
        } else { 
           System.out.println("La consulta no retorna res");
        }
        
        System.out.println("");
    }
    
    public static void modificar() throws XMLDBException {
        
        int numDep = 0;
        String nomDep = "";
        String localDep = "";
        ResourceSet result;
        ResourceSet result2;
        ResourceSet result3;
        
        System.out.print("\nNumero de departament: ");
        numDep = teclat.nextInt();
            
        System.out.print("Nom del departament: ");
        nomDep = teclat.next();
            
        System.out.print("Localitat del departament: ");
        localDep = teclat.next();
        
        
        result = service.query("for $dep in /departamentos/DEP_ROW[DEPT_NO = " + numDep + "] return $dep");
        
        ResourceIterator i;
        i = result.getIterator();
        if (i.hasMoreResources()){
            result2 = service.query("for $dep in /departamentos/DEP_ROW[DEPT_NO = " + numDep + "] return $dep");
            
            i = result2.getIterator();            
            while (i.hasMoreResources()) {
                Resource resource = i.nextResource();
                result = service.query("update replace /departamentos/DEP_ROW[DEPT_NO = " + numDep + "]/DNOMBRE with <DNOMBRE>" + nomDep + "</DNOMBRE>");
                result3 = service.query("update replace /departamentos/DEP_ROW[DEPT_NO = " + numDep + "]/LOC with <LOC>" + localDep + "</LOC>");
            }   
        } else { 
            System.out.println("la consulta no retorna res");
        }
        
        System.out.println("");
        
    }
    
    public static void eliminar() throws XMLDBException {
        
        int numDep = 0;
        ResourceSet result;
        
        System.out.print("\nNumero de departament a esborrar: ");
        numDep = teclat.nextInt();
        
        result = service.query("for $dep in /departamentos/DEP_ROW[DEPT_NO = " + numDep + "] return $dep");
        
        ResourceIterator i;
        i = result.getIterator();
        if (i.hasMoreResources()){
           result = service.query("update delete /departamentos/DEP_ROW[DEPT_NO = " + numDep + "]");
        } else { 
           System.out.println("La consulta no retorna res");
        }
        
        System.out.println("");
        
    }
    
}
