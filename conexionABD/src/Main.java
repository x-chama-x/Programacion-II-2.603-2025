import controllers.ControladorEstudiante;
import controllers.ControladorMateria;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("\n===== SISTEMA INSTITUTO =====");
            System.out.println("1. Gestionar Estudiantes");
            System.out.println("2. Gestionar Materias");
            System.out.println("0. Salir");
            System.out.print("Opción: ");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1 -> new ControladorEstudiante().iniciar();
                case 2 -> new ControladorMateria().iniciar();
            }
        } while (opcion != 0);
        System.out.println("Programa finalizado.");
    }
}
