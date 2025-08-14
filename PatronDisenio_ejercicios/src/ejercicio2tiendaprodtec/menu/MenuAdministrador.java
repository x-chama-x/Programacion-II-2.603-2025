package ejercicio2tiendaprodtec.menu;

import ejercicio2tiendaprodtec.personas.Administrador;

public class MenuAdministrador implements EstrategiaMenu {
    private Administrador administrador;

    public MenuAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("=== Menú Administrador ===");
        System.out.println("1. Agregar cliente");
        System.out.println("2. Actualizar cliente");
        System.out.println("3. Eliminar cliente");
        System.out.println("4. ver clientes");
        System.out.println("5. Ver proveedores");
        System.out.println("6. Agregar proveedor");
        System.out.println("7. Actualizar stock");
        System.out.println("8. Ver ventas realizadas");
        System.out.println("0. Salir");
    }

    @Override
    public void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                administrador.agregarNuevoCliente();
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                administrador.verListadoClientes();
                break;
            case 5:
                break;
            case 6:
                break;
            case 0:
                System.out.println("Volviendo al menú principal...");
                break;
            default:
                System.out.println("Opción inválida");
                break;
        }
    }
}
