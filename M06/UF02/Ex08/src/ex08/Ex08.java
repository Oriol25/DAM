
package ex08;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Oriol Poveda
 * 
 */

public class Ex08 {


    public static void main(String[] args) throws ParseException {
        
        String nom, naixement, model, fabricacio;
        int edat, cv;
        boolean novel, manual;
        Date datasdf;
        
        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
        Scanner teclat = new Scanner(System.in);
        boolean continuar = true;
        int cont = 0, opcio = 0;
        
        
        EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("$objectdb/db/pEx08.odb");
        EntityManager em = emf.createEntityManager();
        
        
        em.getTransaction().begin();
        while (continuar) {
            System.out.println("Introduiex un propietari");
            
            System.out.println("Introdueix un nom");
            nom = teclat.nextLine();
            
            System.out.println("Introdueix any de naixament (dd/MM/yyyy)");
            naixement = teclat.next();
            datasdf = fecha.parse(naixement);
            
            System.out.println("Introdueix la edat:");
            edat = teclat.nextInt();
            
            System.out.println("Es novel? true o false");
            novel = teclat.nextBoolean();
            
            Propietari p;
            p = new Propietari(nom, datasdf, edat, novel);
            
            em.persist(p);
            
            System.out.println("Vols afegir un usuari mes? true:1 - false:0");
            cont = teclat.nextInt();
            teclat.nextLine();
            
            if (cont == 1)
                continuar = true;
            else
                continuar = false;
            
        }
        
        continuar = true;
        while (continuar) {
            System.out.println("Introduiex un vehicle");
            
            System.out.println("Introdueix un Model");
            model = teclat.nextLine();
            
            System.out.println("Introdueix una data de fabricacio (dd/MM/yyyy)");
            fabricacio = teclat.next();
            datasdf = fecha.parse(fabricacio);
            
            System.out.println("Introdueix els cavalls de potencia:");
            cv = teclat.nextInt();
            
            System.out.println("Es manual? true o false");
            manual = teclat.nextBoolean();
            
            Vehicles v;
            v = new Vehicles(model, datasdf, cv, manual);
            
            em.persist(v);
            
            System.out.println("Vols afegir un vehicle mes? true:1 - false:0");
            cont = teclat.nextInt();
            teclat.nextLine();
            
            if (cont == 1)
                continuar = true;
            else
                continuar = false;
            
        }
        
        em.getTransaction().commit();
        
        continuar = true;
        
        while (continuar) {
            
            System.out.println("0. Mostrar propietaris");
            System.out.println("1. Mostrar vehicles");
            System.out.println("2. Sortir");
            opcio = teclat.nextInt();
            
            if (opcio == 0)
                llistarPropietaris(em);
            else if (opcio == 1)
                llistarVehicles(em);
            else 
                continuar = false;
   
        }
        
    }

    private static void llistarPropietaris(EntityManager em) {
        TypedQuery<Propietari> query =
        em.createQuery("SELECT p FROM Propietari p", Propietari.class);
        List<Propietari> results = query.getResultList();
        for (Propietari p : results) {
            System.out.println(p);
        }

    }

    private static void llistarVehicles(EntityManager em) {
        TypedQuery<Vehicles> query =
        em.createQuery("SELECT v FROM Vehicles v", Vehicles.class);
        List<Vehicles> results = query.getResultList();
        for (Vehicles v : results) {
            System.out.println(v);
        }
       
    }
    
}
