
package ex01jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
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
       mostrarOpciones();
        
    }
    
    public static Connection connection () {
     
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            JOptionPane.showMessageDialog(null, "Conexion exitosa");
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
        
        System.out.print("Introduce el nombre");
        nombre = teclado.nextLine();
        
        System.out.print("Introduce el dni");
        dni = teclado.nextLine();
        
        System.out.print("Introduce la fecha (yyyy-mm-dd)");
        fecha = teclado.nextLine();
        
        System.out.print("Introduce la dirección postal");
        direccionPostal = teclado.nextLine();
        
        System.out.print("Sexo? H: Hombre M: Mujer N: No especificado");
        sexo = teclado.next().charAt(1);
        
        if (sexo != 'H' && sexo != 'M') {
            sexo = 'N';   
        }
        
        System.out.print("Codigo Postal:");
        cp = teclado.nextInt();
        
        System.out.print("Poblacion:");
        poblacion = teclado.nextLine();
        
        //Statement comando = connection.createStatement();
        /*comando.executeUpdate("INSERT into alumnes (DNI, Nom, Naixement, Adreca, sexe, CP, Poblacio) "
                + "VALUES ('" + nombre + "', '" + dni +"', '" + fecha + "', '" + direccionPostal + "', '" +
                sexo + "', '" + cp + "', '" + poblacion + "');");*/
        
        connection.close();
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
                        
                    } else if (opcion == 3)
                    
                    break;
                case 2:
                    break;
               
            }
            
            opcion = 0;
            
        }
    }
    
}
