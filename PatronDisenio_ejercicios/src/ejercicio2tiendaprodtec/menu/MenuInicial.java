package ejercicio2tiendaprodtec.menu;

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

    @Override
    public void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                // Cambiar al menú de administrador
                contexto.setEstrategia(new MenuAdministrador());
                break;
            case 2:
                // Cambiar al menú de cliente
                contexto.setEstrategia(new MenuCliente());
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
