
package ex01jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos");
        }
        
        return connection;
    }
    
    public static void anadirAlumno() {
        
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
        
    }
    
    public static void modificarAlumno() {
        
        Scanner teclado = new Scanner(System.in);
            
        String dni,nom,naixement,adreca, sexe, dniOpcio;
        int cp;
        
        Connection connection = null;
        
        try { 
            connection = connection();
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos");

        }
   
        // Mostramos las poblaciones y pedimos el codigo postal
        try {
            Statement comando = connection.createStatement();
            ResultSet registro;
            
            mostrarAlumnos();
            
            registro = comando.executeQuery("SELECT * FROM alumnes");
            
            System.out.println("\nEscribe el dni del la persona que quieras modificar");
            dniOpcio = teclado.next();
            
            while (registro.next()==true) {         
                dni = registro.getString("DNI");
                nom = registro.getString("Nom");
                naixement = registro.getString("Naixement");
                adreca = registro.getString("Adreca");
                sexe = registro.getString("sexe");
                cp = registro.getInt("cp");
                
                if (dniOpcio.equals(dni)) {
                    System.out.print("Introduce el nombre ["+ nom + "]: ");
                    teclado.nextLine();
                    nom = teclado.nextLine();
                    
                    System.out.print("Introduce la fecha [" + naixement + "]: ");
                    naixement = teclado.nextLine();

                    System.out.print("Introduce la dirección postal [" + adreca + "]: ");
                    adreca = teclado.nextLine();

                    System.out.println("Sexo [" + sexe + "]? Hombre [H] Mujer [M] No especificado [N]: ");
                    sexe = teclado.next();
                    if (!(sexe.equals("H")) && !(sexe.equals("M"))) {
                        sexe = "N";   
                    }

                    System.out.print("Codigo Postal:");
                    cp = teclado.nextInt();
                   
                    
                    try  {
                        Statement comandos = connection.createStatement();
                        comandos.executeUpdate("UPDATE alumnes SET nom = '" + nom + "', Naixement = '" + naixement + "', Adreca = '" + adreca + "', Sexe = '" + sexe + "', CP = '" + cp + "'WHERE DNI = '" + dniOpcio + "';");
                    } catch (Exception e) {
                        System.out.println("Error en el UDPATE: " + e);

                    }              
                }     
            }
                        
        } catch (Exception e) {
            System.out.println("Error al modificar el alumne " + e);
        }    
                  
    }
    
    public static void eliminarAlumnos() {
        
        Scanner teclado = new Scanner(System.in);
        
        String dni;
        int registro;
        
        Connection connection = null;
   
        try { 
            connection = connection();
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos");
        }
        
        mostrarAlumnos();
        System.out.println("DNI del alumno que quieras borrar");     
        dni = teclado.next();
        
        try {
            Statement comando = connection.createStatement();
            registro = comando.executeUpdate("DELETE FROM alumnes WHERE DNI = '" + dni + "';");
            //delete from articulos where
            
        } catch (Exception e){
            System.out.println("Error al borrar el alumno " + e);
        }
                      
    }
    
    public static void mostrarAlumnos() {
               
        Connection connection = null;
        
        try { 
            connection = connection();
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos");
        }    
        
        String dni,nom,naixement,adreca,sexe;
        int cp;
        
        try {
            Statement comando = connection.createStatement();
            ResultSet registro = comando.executeQuery("SELECT * FROM alumnes");
            
            while (registro.next()==true) {
                dni = registro.getString("DNI");
                nom = registro.getString("Nom");
                naixement = registro.getString("Naixement");
                adreca = registro.getString("Adreca");
                sexe = registro.getString("sexe");
                cp = registro.getInt("cp");
                
                System.out.println("DNI [" + dni + "] Nom [" + nom + "] Naixement [" + naixement + "] Adreça Postal [" + adreca + "] Sexe [" + sexe + "] Codigo Postal [" + cp + "]");
                                
            }
            
        } catch (Exception e) {
            System.out.println("Error al mostrar los alumnos " + e);
        }
    }
    
    public static void mostrarUnAlumno() {
        
        Scanner teclado = new Scanner(System.in);
        
        System.out.println("\n");
        
        Connection connection = null;
        
        try { 
            connection = connection();
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos");
        }    
        
        String dni,nom,naixement,adreca,sexe;
        int cp;
        String opcioDNI;
        
        mostrarAlumnos();
        
        System.out.println("DNI del alumno");
        opcioDNI = teclado.next();
        
        try {
            Statement comando = connection.createStatement();
            ResultSet registro = comando.executeQuery("SELECT * FROM alumnes WHERE DNI = '" + opcioDNI +"';");
            
            while (registro.next()==true) {
                dni = registro.getString("DNI");
                nom = registro.getString("Nom");
                naixement = registro.getString("Naixement");
                adreca = registro.getString("Adreca");
                sexe = registro.getString("sexe");
                cp = registro.getInt("cp");
                
                System.out.println("DNI [" + dni + "] \nNom [" + nom + "] \nNaixement [" + naixement + "] \nAdreça Postal [" + adreca + "] \nSexe [" + sexe + "] \nCodigo Postal [" + cp + "]\n");
                                
            }
            
        } catch (Exception e) {
            System.out.println("Error al mostrar el alumno: " + opcioDNI + " .\n" + e);
        }
        
    }
    
    public static void anadirPoblacion() {
        
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
        
        Scanner teclado = new Scanner(System.in);
        
        
        String dni, nom, naixement, adreca, sexe;
        int cpO, cp, conta = 0;
        int registro;
        char opcion = 0;
        
        Connection connection = null;
   
        try { 
            connection = connection();
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos");
        }
        
        mostrarPoblaciones();
        System.out.println("Codigo postal de la poblacion que quieras borrar");     
        cpO = teclado.nextInt();
        
        try {
            Statement comando = connection.createStatement();
            ResultSet registros = comando.executeQuery("SELECT * FROM alumnes WHERE '" + cpO + "'");
            
            while (registros.next()) {
                conta++;    
            }

        } catch (Exception e) {
            System.out.println("Error al buscar los alumnos con el mismo codigo postal " + e);
        }
        
        if (conta > 0) {
            System.out.println("Hay " + conta + " alumnes que tienen este codigo postal\nSi eliminas la poblacion tambien se eliminaran los alumnos\nSeguro? (Si = S)");
            opcion = teclado.next().charAt(0);
        } else {
            System.out.println("No hay ningun alumno con ese codigo postal, se puede borrar sin problemas");
            opcion = 'S';
        }
        
         
        try {
            
            if ((opcion == 'S') || (opcion == 's')) {
                Statement comando = connection.createStatement();
                registro = comando.executeUpdate("DELETE FROM Poblacio WHERE CP = '" + cpO + "';");
            }
            
           
            //delete from articulos where
            
        } catch (Exception e){
            System.out.println("Error al borrar el alumno " + e);
        }
        
    }
    
    public static void mostrarPoblaciones() {
        
        System.out.println("\n");
        
        Connection connection = null;
        
        try { 
            connection = connection();
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos");
        }    
        
        String nom;
        int cp;
        
        try {
            Statement comando = connection.createStatement();
            ResultSet registro = comando.executeQuery("SELECT * FROM poblacio");
            
            while (registro.next()==true) {
                cp = registro.getInt("CP");
                nom = registro.getString("Poblacio");
                
                System.out.println("Codigo Postal ["+ cp +"] Poblacio [" + nom +"]\n");
                          
            }
            
        } catch (Exception e) {
            System.out.println("Error al mostrar las poblaciones " + e);
        }
        
        
    }
    
    public static void mostrarUnaPoblacion() {
        
        System.out.println("\n");
        
        Scanner teclado = new Scanner(System.in);
        Connection connection = null;
        
        try { 
            connection = connection();
        } catch (Exception e) {
            System.out.println("Error al conectar con la base de datos");
        }    
        
        String nom;
        int cp, opcioCP;
        
        mostrarPoblaciones();
        
        System.out.println("Introduce el codigo postal: ");
        opcioCP = teclado.nextInt();
        
        try {
            Statement comando = connection.createStatement();
            ResultSet registro = comando.executeQuery("SELECT * FROM poblacio WHERE CP = '" + opcioCP + "';");
            
            while (registro.next()==true) {
                cp = registro.getInt("CP");
                nom = registro.getString("Poblacio");
                
                System.out.println("Codigo Postal ["+ cp +"] Poblacio [" + nom +"]\n");
                          
            }
            
        } catch (Exception e) {
            System.out.println("Error al mostrar las poblaciones " + e);
        }
        
    }
    
    public static void mostrarOpciones() throws SQLException {
        
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;
        int opcionM = 0;
        
        while (opcion != 3) {
            
            System.out.println("1. Modificar tabla de alumnos");
            System.out.println("2. Modificar tabla de poblaciones");
            System.out.println("3. Salir");
            opcion = teclado.nextInt();
            
            switch (opcion) {
                case 1:
                    System.out.println("\n1. Añadir alumnos");
                    System.out.println("2. Modificar alumnos");
                    System.out.println("3. Eliminar alumnos");
                    System.out.println("4. Mostrar alumnos");
                    System.out.println("5. Mostrar un solo alumno");
                    opcionM = teclado.nextInt();
                    
                    if (opcionM == 1) {
                        anadirAlumno();
                    } else if (opcionM == 2){
                        modificarAlumno();
                    } else if (opcionM == 3) {
                        eliminarAlumnos();
                    } else if (opcionM == 4) {
                        mostrarAlumnos();
                    } else if (opcionM == 5)  {
                        mostrarUnAlumno();
                    }
                    
                    break;
                case 2:
                    
                    System.out.println("\n1. Añadir poblacion");
                    System.out.println("2. Modificar poblacion");
                    System.out.println("3. Eliminar poblacion");
                    System.out.println("4. Mostrar poblaciones");
                    System.out.println("5. Mostrar una sola poblacion");
                    opcionM = teclado.nextInt();
                    
                    if (opcionM == 1) {
                        anadirPoblacion();
                    } else if (opcionM == 2){
                        modificarPoblacion();
                    } else if (opcionM == 3) {
                        eliminarPoblacion();
                    } else if (opcionM == 4) {
                        mostrarPoblaciones();
                    } else if (opcionM == 5) {
                        mostrarUnaPoblacion();
                    }
                    
                    break;
               
            }
            
            System.out.println("\n");
            
        }
    }    
}
