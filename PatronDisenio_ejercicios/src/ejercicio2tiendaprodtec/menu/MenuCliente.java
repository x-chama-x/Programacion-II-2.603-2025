package ejercicio2tiendaprodtec.menu;

import ejercicio2tiendaprodtec.Sistema;
import ejercicio2tiendaprodtec.personas.Cliente;
import ejercicio2tiendaprodtec.productos.Producto;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuCliente implements EstrategiaMenu {
    private Cliente clienteActual;
    private Sistema sistema;
    private Scanner scanner;

    public MenuCliente(Cliente cliente) {
        this.clienteActual = cliente;
        this.sistema = Sistema.getInstancia();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void mostrarMenu() {
        System.out.println("\n=== Menú Cliente ===");
        System.out.println("1. Ver productos disponibles");
        System.out.println("2. Realizar compra");
        System.out.println("3. Ver mis compras");
        System.out.println("0. Salir");
    }

    @Override
    public void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                mostrarProductosDisponibles();
                break;
            case 2:
                realizarCompra();
                break;
            case 3:
                verMisCompras();
                break;
            case 0:
                System.out.println("Volviendo al menú principal...");
                break;
            default:
                System.out.println("Opción no válida");
        }
    }

    private void mostrarProductosDisponibles() {
        ArrayList<Producto> productos = sistema.obtenerProductosEnStock();
        if (productos.isEmpty()) {
            System.out.println("No hay productos disponibles en stock.");
            return;
        }

        System.out.println("\n=== Productos Disponibles ===");
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            System.out.printf("%d. %s - Precio: $%.2f - Stock: %d%n",
                    i + 1,
                    producto.getNombre(),
                    producto.getPrecioActual(),
                    producto.getStockDisponible());
        }
    }

    private void realizarCompra() {
        ArrayList<Producto> productos = sistema.obtenerProductosEnStock();
        if (productos.isEmpty()) {
            System.out.println("No hay productos disponibles para comprar.");
            return;
        }

        mostrarProductosDisponibles();
        System.out.print("\nSeleccione el número del producto que desea comprar (0 para cancelar): ");
        int seleccion = scanner.nextInt();

        if (seleccion == 0) {
            System.out.println("Compra cancelada.");
            return;
        }

        if (seleccion < 1 || seleccion > productos.size()) {
            System.out.println("Selección no válida.");
            return;
        }

        Producto productoSeleccionado = productos.get(seleccion - 1);
        System.out.print("Ingrese la cantidad que desea comprar: ");
        int cantidad = scanner.nextInt();

        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor a 0.");
            return;
        }

        if (sistema.realizarCompra(clienteActual, productoSeleccionado, cantidad)) {
            System.out.printf("¡Compra realizada con éxito! Total: $%.2f%n",
                    productoSeleccionado.getPrecioActual() * cantidad);
        } else {
            System.out.println("No hay suficiente stock disponible.");
        }
    }

    private void verMisCompras() {
        var misCompras = sistema.obtenerVentasCliente(clienteActual);
        if (misCompras.isEmpty()) {
            System.out.println("No has realizado ninguna compra aún.");
            return;
        }

        System.out.println("\n=== Mis Compras ===");
        for (var venta : misCompras) {
            System.out.printf("\nFactura #%d - Fecha: %s%n",
                venta.getNFactura(),
                venta.getFecha());
            System.out.println("Productos:");
            for (var producto : venta.getProductosVendidos()) {
                System.out.printf("- %s x%d - Subtotal: $%.2f%n",
                    producto.getProducto().getNombre(),
                    producto.getCantidadVendida(),
                    producto.getMontoTotalParcial());
            }
            System.out.printf("Total: $%.2f%n", venta.getMontoFinal());
        }
    }
}
