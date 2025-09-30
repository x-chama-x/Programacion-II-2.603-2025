package views;

import java.util.Scanner;

public class VistaEstudiante {
    private Scanner scanner = new Scanner(System.in);

    public int menu() {
        System.out.println("\n--- GESTIÓN DE ESTUDIANTES ---");
        System.out.println("1. Agregar estudiante");
        System.out.println("2. Listar estudiantes");
        System.out.println("3. Actualizar estudiante");
        System.out.println("4. Eliminar estudiante");
        System.out.println("0. Volver");
        System.out.print("Opción: ");
        return scanner.nextInt();
    }

    public Scanner getScanner() {
        return scanner;
    }
}