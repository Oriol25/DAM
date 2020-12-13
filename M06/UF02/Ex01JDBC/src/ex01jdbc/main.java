
package ex01jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author Oriol Poveda
 */

public class main {
    
    public static final String URL = "jdbc:mysql://localhost:3306/ex01";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "";
     
    public static void main(String[] args) throws SQLException {
       //mostrarOpciones();
       modificarAlumno();
    }
    
    public static Connection connection () {
     
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos");
        }
        
        return connection;
    }
    
    public static void anadirAlumno() throws SQLException {
        
        Connection connection = null;
   
        try { 
            connection = connection();
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos");

        }
        
        Scanner teclado = new Scanner(System.in);
        
        String nombre = "", dni = "", fecha = "", direccionPostal = "";
        char sexo = 'N';
        int cp = 0;
        String poblacion = "";
        
        System.out.print("Introduce el nombre: ");
        nombre = teclado.nextLine();
        
        System.out.print("Introduce el dni: ");
        dni = teclado.nextLine();
        
        System.out.print("Introduce la fecha (yyyy-mm-dd): ");
        fecha = teclado.nextLine();
        
        System.out.print("Introduce la dirección postal: ");
        direccionPostal = teclado.nextLine();
        
        System.out.println("Sexo? Hombre [H] Mujer [M] No especificado [N]: ");
        sexo = teclado.next().charAt(0);
        
        if (sexo != 'H' && sexo != 'M') {
            sexo = 'N';   
        }
        
        System.out.print("Codigo Postal:");
        cp = teclado.nextInt();
        
        try  {
            Statement comando = connection.createStatement();
            comando.executeUpdate("INSERT into alumnes (DNI, Nom, Naixement, Adreca, sexe, CP) "
                    + "VALUES ('" + dni + "', '" + nombre +"', '" + fecha + "', '" + direccionPostal + "', '" +
                    sexo + "', '" + cp + "');");
        } catch (Exception e) {
            System.out.println("Error al insertar el alumno " + e);
          
        }
        
        connection.close();
    }
    
    public static void modificarAlumno() {
        
        Scanner teclado = new Scanner(System.in);
        
        String nom;
        String dni;
        String naixement;
        String adreca;
        String sexe;
        String cp;
        
        String select = "";
        
        Connection connection = null;
   
        try { 
            connection = connection();
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos");
                
        }
        
        //Mostramos las poblaciones y pedimos el codigo postal
        try {
            Statement comando = connection.createStatement();
            ResultSet registro = comando.executeQuery("SELECT * FROM alumnes");
            
            while (registro.next()==true) {
		nom = registro.getString("Nom");
		dni = registro.getString("DNI");
		naixement = registro.getString("Naixement");
		adreca = registro.getString("Adreca");
		sexe = registro.getString("Sexe");
		cp = registro.getString("CP");
                
                select += "Nom [" + nom + "] ";
                select += "DNI [" + dni + "] ";
                select += "Naixement [" + naixement + "] ";
                select += "Adreca [" + adreca + "] ";
                select += "Sexe [" + sexe + "] ";
                select += "CP [" + cp + "]";
                
                System.out.println(select);
                
                select = "";
            }    
                                
        } catch (Exception e) {
            System.out.println("Error al modificar una poblacion " + e);         
        }
        
    }
    
    public static void eliminarAlumnos() {
        
        
        
    }
    
    public static void anadirPoblacion() throws SQLException {
        
        Connection connection = null;
   
        try { 
            connection = connection();
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos");

        }
        
        Scanner teclado = new Scanner(System.in);
                
        String poblacio = "";
        int cp = 0;
        
        System.out.print("Nombre de la poblacion: ");
        poblacio = teclado.nextLine();
        
        System.out.print("Numero de codigo postal: ");
        cp = teclado.nextInt();
        
        try  {
            Statement comando = connection.createStatement();
            comando.executeUpdate("INSERT into poblacio (CP, Poblacio) "
                    + "VALUES ('" + cp + "', '" + poblacio + "');");
        } catch (Exception e) {
            System.out.println("Error al insertar una poblacion " + e);
          
        }
        
        connection.close();
               
    }
    
    public static void modificarPoblacion() {
        
        Scanner teclado = new Scanner(System.in);
        
        String cp ="";
        String poblacio="";
        String opcioCP ="";
        int cpInt = 0;
        
        Connection connection = null;
   
        try { 
            connection = connection();
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos");
                
        }
        
        //Mostramos las poblaciones y pedimos el codigo postal
        try  {
            Statement comando = connection.createStatement();
            ResultSet registro = comando.executeQuery("SELECT * FROM poblacio");
            
            while (registro.next()==true) {
                cp = registro.getString("CP");
                poblacio = registro.getString("Poblacio");
                
                System.out.println(cp + " " + poblacio);
                                
            }
            
            /*se repite porque despues del while es como si la variable registro
                perdiera su valor*/
            registro = comando.executeQuery("SELECT * FROM poblacio");
            
            System.out.println("\nEscribe el codigo postal que quieras modificar");
            opcioCP = teclado.next();
            
            while (registro.next()==true) {
                cp = registro.getString("CP");
                poblacio = registro.getString("Poblacio");
                
                if (opcioCP.equals(cp)) {                    
                    System.out.print("Poblacion ["+ poblacio +"]: ");
                    teclado.nextLine();
                    poblacio = teclado.nextLine();
                    
                    cpInt = Integer.parseInt(cp);
                    
                    try  {
                        Statement comandos = connection.createStatement();
                        comandos.executeUpdate("UPDATE poblacio SET Poblacio = '" + poblacio + "' WHERE CP = " + cpInt + ";");
                    } catch (Exception e) {
                        System.out.println("Error en el UDPATE: " + e);

                    }                 
                }                               
            }
           
        } catch (Exception e) {
            System.out.println("Error al modificar una poblacion " + e);
          
        }
        
    }
    
    public static void eliminarPoblacion() {
        
    }
    
    public static void mostrarOpciones() throws SQLException {
        
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;
        
        while (opcion != 3) {
            
            System.out.println("1. Modificar tabla de alumnos");
            System.out.println("2. Modificar tabla de poblaciones");
            System.out.println("3. Salir");
            opcion = teclado.nextInt();
            
            switch (opcion) {
                case 1:
                    System.out.println("\n\n1. Añadir alumnos");
                    System.out.println("2. Modificar alumnos");
                    System.out.println("3. Eliminar alumnos");
                    opcion = teclado.nextInt();
                    
                    if (opcion == 1) {
                        anadirAlumno();
                    } else if (opcion == 2){
                        modificarAlumno();
                    } else if (opcion == 3) {
                        eliminarAlumnos();
                    }
                    
                    break;
                case 2:
                    
                    System.out.println("\n\n1. Añadir poblacion");
                    System.out.println("2. Modificar poblacion");
                    System.out.println("3. Eliminar poblacion");
                    opcion = teclado.nextInt();
                    
                    if (opcion == 1) {
                        anadirPoblacion();
                    } else if (opcion == 2){
                        modificarPoblacion();
                    } else if (opcion == 3) {
                        eliminarPoblacion();
                    }
                    
                    break;
               
            }
                        
            System.out.println("\n\n");
            
        }
    }
}
