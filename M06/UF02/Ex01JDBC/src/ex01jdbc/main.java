
package ex01jdbc;

import java.util.Scanner;

/**
 *
 * @author Oriol Poveda
 */

public class main {

    public static void main(String[] args) {
       
    }
    
    public static void mostrarOpciones() {
        
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
    
    public static void anadirAlumno() {
        
        Scanner teclado = new Scanner(System.in);
        
        String nombre = "", dni = "";
        
        System.out.println("Introduce el nombre");
        nombre = teclado.nextLine();
        
        System.out.println("Introduce el dni");
        dni = teclado.nextLine();
        
        System.out.println("");
        
    }
    
}
