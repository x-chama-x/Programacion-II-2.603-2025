package instituto.frondend;

import java.util.Scanner;

public class Menu implements MenuConector {

    private int op;
    Scanner sc = new Scanner(System.in);

    @Override
    public void IniciarMenu() {
        do {
            System.out.println("\n===== MENÚ PRINCIPAL =====");
            System.out.println("1. Estudiantes");
            System.out.println("2. Materias");
            System.out.println("0. Salir del sistema");
            System.out.print("Elija una opción: ");
            op = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (op) {
                case 1 ->
                    estudiantes();
                case 2 ->
                    materias();
                case 0 ->
                    System.out.println("Saliendo del sistema...");
                default ->
                    System.out.println("Opción inválida, intente de nuevo.");
            }
        } while (op != 0);

    }

    public void estudiantes() {
        MenuEstudiante menu = new MenuEstudiante();
        menu.IniciarMenu();
    }

    public void materias() {
        MenuMateria menu = new MenuMateria();
        menu.IniciarMenu();
    }

}
