package ejercicio2tiendaprodtec.menu;

import ejercicio2tiendaprodtec.personas.Cliente;
import ejercicio2tiendaprodtec.productos.Producto;
import java.util.ArrayList;
import ejercicio2tiendaprodtec.Sistema;
import java.util.Scanner;

public class MenuCliente implements EstrategiaMenu {
    private final Cliente clienteActual;
    private final Scanner scanner;
    private MenuContexto menuContexto;

    public MenuCliente(Cliente cliente) {
        this.clienteActual = cliente;
        this.scanner = new Scanner(System.in);
    }

    public void setMenuContexto(MenuContexto menuContexto) {
        this.menuContexto = menuContexto;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("\n=== Menú Cliente ===");
        System.out.println("1. Ver catálogo de productos");
        System.out.println("2. Realizar compra");
        System.out.println("3. Ver mis compras");
        System.out.println("0. Salir");
    }

    private void mostrarProductosParaCompra() {
        ArrayList<Producto> productos = Sistema.getInstancia().obtenerProductosEnStock();
        if (productos.isEmpty()) {
            System.out.println("No hay productos disponibles para comprar.");
            return;
        }

        System.out.println("\n=== Productos Disponibles para Compra ===");
        System.out.println("----------------------------------------");
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            System.out.printf("%d. %-30s | Precio: $%.2f | Stock: %d | Categoría: %s%n",
                    i + 1,
                    producto.getNombre(),
                    producto.getPrecioActual(),
                    producto.getStockDisponible(),
                    producto.getCategoria().getNombre());
        }
        System.out.println("----------------------------------------");
    }

    @Override
    public void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                MenuStock menuStock = new MenuStock();
                menuStock.mostrarMenu();
                System.out.print("\nIngrese una opción: ");
                int opcionStock = scanner.nextInt();
                menuStock.ejecutarOpcion(opcionStock);
                break;
            case 2:
                mostrarProductosParaCompra();
                System.out.print("\nSeleccione el número del producto que desea comprar (0 para cancelar): ");
                int seleccion = scanner.nextInt();

                if (seleccion == 0) {
                    System.out.println("Compra cancelada.");
                    return;
                }

                System.out.print("Ingrese la cantidad que desea comprar: ");
                int cantidad = scanner.nextInt();
                clienteActual.realizarCompra(seleccion, cantidad);
                break;
            case 3:
                clienteActual.verMisCompras();
                break;
            case 0:
                System.out.println("Volviendo al menú principal...");
                if (menuContexto != null) {
                    menuContexto.setEstrategia(new MenuInicial(menuContexto));
                }
                break;
            default:
                System.out.println("Opción no válida");
        }
    }
}
