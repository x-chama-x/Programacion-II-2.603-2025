package views;

import java.util.Scanner;

public class VistaMateria {
    private Scanner scanner = new Scanner(System.in);

    public int menu() {
        System.out.println("\n--- GESTIÓN DE MATERIAS ---");
        System.out.println("1. Agregar materia");
        System.out.println("2. Listar materias");
        System.out.println("3. Actualizar materia");
        System.out.println("4. Eliminar materia");
        System.out.println("5. Buscar materia por código");
        System.out.println("0. Volver");
        System.out.print("Opción: ");
        return scanner.nextInt();
    }

    public Scanner getScanner() {
        return scanner;
    }
}
