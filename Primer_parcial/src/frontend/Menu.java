package frontend;

import backend.Models.Administrador;
import java.util.Scanner;

public class Menu implements MenuConector{

    private Scanner scanner;
    private Administrador adminLogueado;

    public Menu() {
        this.scanner = new Scanner(System.in);
        this.adminLogueado = null;
    }

    @Override
    public void IniciarMenu() {
        System.out.println("=== SISTEMA DE GESTIÓN DE BASE MILITAR ===");
        System.out.println("administrador hardcodeado para pruebas: usuario='admin', contraseña: '123456'");
        System.out.println("Bienvenido al sistema de administración militar");

        if (realizarLogin()) {
            System.out.println("\n¡Login exitoso! Bienvenido " + adminLogueado.getNombre() + " " + adminLogueado.getApellido());
            System.out.println("Cargo: " + adminLogueado.getCargo());

            // Crear e iniciar el menú principal con el administrador logueado
            MenuPrincipal menuPrincipal = new MenuPrincipal(adminLogueado);
            menuPrincipal.IniciarMenu();
        } else {
            System.out.println("Acceso denegado. El sistema se cerrará.");
        }
    }

    private boolean realizarLogin() {
        System.out.println("\n=== LOGIN DE ADMINISTRADOR ===");
        Administrador adminDefault = new Administrador(
            "12345678", "Admin", "Sistema", "admin", "123456", "Administrador General"
        );

        int intentos = 0;
        int maxIntentos = 3;

        while (intentos < maxIntentos) {
            System.out.print("Usuario: ");
            String usuario = scanner.nextLine().trim();

            System.out.print("Contraseña: ");
            String password = scanner.nextLine().trim();

            if (adminDefault.validarCredenciales(usuario, password)) {
                adminLogueado = adminDefault;
                return true;
            } else {
                intentos++;
                int intentosRestantes = maxIntentos - intentos;
                if (intentosRestantes > 0) {
                    System.out.println("Credenciales incorrectas. Intentos restantes: " + intentosRestantes);
                } else {
                    System.out.println("Máximo número de intentos alcanzado.");
                }
            }
        }

        return false;
    }
}
