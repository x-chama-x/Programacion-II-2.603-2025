package frontend;

import backend.Models.Administrador;
import java.util.Scanner;

public class MenuPrincipal implements MenuConector {

    private Scanner scanner;
    private Administrador adminLogueado;
    private boolean sistemaActivo;

    public MenuPrincipal(Administrador adminLogueado) {
        this.scanner = new Scanner(System.in);
        this.adminLogueado = adminLogueado;
        this.sistemaActivo = true;
    }

    @Override
    public void IniciarMenu() {
        while (sistemaActivo) {
            mostrarMenuPrincipal();
            int opcion = leerOpcion();
            procesarOpcion(opcion);
        }
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\nSISTEMA DE GESTIÓN DE BASE MILITAR");
        System.out.println("Administrador: " + adminLogueado.getNombre() + " " + adminLogueado.getApellido());
        System.out.println("Cargo: " + adminLogueado.getCargo());

        System.out.println("\n1. Gestión de Soldados (FUNCIONA)");
        System.out.println("2. Gestión de Oficiales (FUNCIONA)");
        System.out.println("3. Gestión de Cuarteles");
        System.out.println("4. Asignar Soldados a Cuarteles");
        System.out.println("5. Asignar Oficiales a Soldados");
        System.out.println("6. Gestión de Reservas");
        System.out.println("0. Cerrar Sesión");

        System.out.print("\nSeleccione una opción: ");
    }

    private int leerOpcion() {
        try {
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                MenuSoldado menuSoldado = new MenuSoldado();
                menuSoldado.IniciarMenu();
                pausar();
                break;
            case 2:
                MenuOficial menuOficial = new MenuOficial();
                menuOficial.IniciarMenu();
                pausar();
                break;
            case 3:
                //MenuCuartel menuCuartel = new MenuCuartel();
                //menuCuartel.IniciarMenu();
                //pausar();
                break;
            case 4:
                //MenuAsignarSoldadosACuarteles menuAsignarSoldados = new MenuAsignarSoldadosACuarteles();
                //menuAsignarSoldados.IniciarMenu();
                //pausar();
                break;
            case 5:
                //MenuAsignarOficialesASoldados menuAsignarOficiales = new MenuAsignarOficialesASoldados();
                //menuAsignarOficiales.IniciarMenu();
                pausar();
                break;
            case 6:
                //MenuReservas menuReservas = new MenuReservas();
                //menuReservas.IniciarMenu();
                //pausar();
                break;
            case 0:
                cerrarSesion();
                break;
            default:
                System.out.println("Opción inválida. Por favor, intente de nuevo.");
        }
    }

    private void cerrarSesion() {
        System.out.println("\nCerrando sesión...");
        System.out.println("Hasta luego, " + adminLogueado.getNombre() + "!");
        sistemaActivo = false;
    }

    private void pausar() {
        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
}
