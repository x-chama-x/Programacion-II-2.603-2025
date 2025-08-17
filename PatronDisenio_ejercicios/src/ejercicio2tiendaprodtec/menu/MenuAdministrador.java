package ejercicio2tiendaprodtec.menu;

import ejercicio2tiendaprodtec.personas.Administrador;

public class MenuAdministrador implements EstrategiaMenu {
    private Administrador administrador;
    private MenuContexto menuContexto;

    public MenuAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public void setMenuContexto(MenuContexto menuContexto) {
        this.menuContexto = menuContexto;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("=== Menú Administrador ===");
        System.out.println("1. Agregar cliente");
        System.out.println("2. Actualizar cliente");
        System.out.println("3. Eliminar cliente");
        System.out.println("4. Ver clientes");
        System.out.println("5. Ver proveedores");
        System.out.println("6. Agregar proveedor");
        System.out.println("7. Actualizar proveedor");
        System.out.println("8. Ver productos en stock");
        System.out.println("9. cargar al stock productos faltantes");
        System.out.println("10. Ver ventas realizadas");
        System.out.println("0. Salir");
    }

    @Override
    public void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                administrador.agregarNuevoCliente();
                break;
            case 2:
                administrador.actualizarCliente();
                break;
            case 3:
                break;
            case 4:
                administrador.verListadoClientes();
                break;
            case 5:
                administrador.verListadoProveedores();
                break;
            case 6:
                administrador.agregarNuevoProveedor();
                break;
            case 7:
                administrador.actualizarProveedor();
                break;
            case 8:
                administrador.verProductosEnStock();
                break;
            case 9:
                administrador.cargarStockAProductosFaltantes();
                break;
            case 10:
                administrador.verVentasRealizadas();
                break;
            case 0:
                System.out.println("Volviendo al menú principal...");
                if (menuContexto != null) {
                    menuContexto.setEstrategia(new MenuInicial(menuContexto));
                }
                break;
            default:
                System.out.println("Opción inválida");
                break;
        }
    }
}
