package ejercicio2tiendaprodtec.menu;

import ejercicio2tiendaprodtec.Sistema;
import ejercicio2tiendaprodtec.personas.*;

import java.util.Scanner;

public class MenuInicial implements EstrategiaMenu {
    private MenuContexto contexto;
    private Scanner scanner;

    public MenuInicial(MenuContexto contexto) {
        this.contexto = contexto;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void mostrarMenu() {
        System.out.println("=== Menú Principal ===");
        System.out.println("1. Ingresar como Administrador");
        System.out.println("2. Ingresar como Cliente");
        System.out.println("0. Salir");
    }

    private boolean loginAdministrador() {
        System.out.println("\n=== Login Administrador ===");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Clave: ");
        String clave = scanner.nextLine();

        Administrador admin = Sistema.getInstancia().validarAdministrador(nombre, clave);
        if (admin != null) {
            contexto.setEstrategia(new MenuAdministrador(admin));
            return true;
        }
        return false;
    }

    private Cliente loginCliente() {
        System.out.println("\n=== Login Cliente ===");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Clave: ");
        String clave = scanner.nextLine();

        return Sistema.getInstancia().buscarClientePorCredenciales(nombre, clave);
    }

    @Override
    public void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                if (loginAdministrador()) {
                    System.out.println("Login exitoso!");
                } else {
                    System.out.println("Credenciales inválidas");
                }
                break;
            case 2:
                Cliente cliente = loginCliente();
                if (cliente != null) {
                    System.out.println("Login exitoso!");
                    contexto.setEstrategia(new MenuCliente(cliente));
                } else {
                    System.out.println("Credenciales inválidas");
                }
                break;
            case 0:
                System.out.println("¡Hasta luego!");
                System.exit(0);
                break;
            default:
                System.out.println("Opción inválida");
                break;
        }
    }
}
