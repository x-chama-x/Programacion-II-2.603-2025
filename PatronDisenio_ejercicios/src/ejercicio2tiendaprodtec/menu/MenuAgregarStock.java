package ejercicio2tiendaprodtec.menu;

import ejercicio2tiendaprodtec.Sistema;
import ejercicio2tiendaprodtec.productos.Producto;
import java.util.*;

public class MenuAgregarStock implements EstrategiaMenu {
    private Sistema sistema;
    private Map<Integer, Producto> mapaProductos;

    public MenuAgregarStock() {
        this.sistema = Sistema.getInstancia();
        this.mapaProductos = new HashMap<>();
    }

    @Override
    public void mostrarMenu() {
        ArrayList<Producto> stock = sistema.getStock();

        if (stock.isEmpty()) {
            System.out.println("No hay productos en stock.");
            return;
        }

        // Obtener categorías únicas y organizar productos por categoría
        Map<String, List<Producto>> productosPorCategoria = new HashMap<>();

        for (Producto producto : stock) {
            String categoria = producto.getCategoria().getNombre();
            productosPorCategoria.computeIfAbsent(categoria, k -> new ArrayList<>()).add(producto);
        }

        System.out.println("\n=== Cargar Productos Faltantes al Stock ===\n");

        int opcion = 1;
        mapaProductos.clear();

        // Mostrar productos por categoría
        for (Map.Entry<String, List<Producto>> entry : productosPorCategoria.entrySet()) {
            System.out.println("=== " + entry.getKey() + " ===");

            for (Producto producto : entry.getValue()) {
                System.out.printf("%d. %-30s | Stock actual: %d unidades%n",
                    opcion,
                    producto.getNombre(),
                    producto.getStockDisponible());

                // Mostrar proveedores usando el método del Sistema
                System.out.print(sistema.obtenerInformacionProveedoresProducto(producto));
                System.out.println(); // Línea en blanco entre productos

                mapaProductos.put(opcion, producto);
                opcion++;
            }
        }

        System.out.println("0. Volver al menú anterior");
    }

    @Override
    public void ejecutarOpcion(int opcion) {
        if (opcion == 0) {
            return;
        }

        Producto productoSeleccionado = mapaProductos.get(opcion);

        if (productoSeleccionado != null) {
            Scanner scanner = new Scanner(System.in);

            System.out.printf("\nStock actual de %s: %d unidades%n",
                productoSeleccionado.getNombre(),
                productoSeleccionado.getStockDisponible());

            System.out.print("Ingrese la cantidad a agregar: ");
            int cantidadAgregar = scanner.nextInt();

            if (cantidadAgregar > 0) {
                productoSeleccionado.actualizarStock(cantidadAgregar);
                System.out.printf("Stock actualizado correctamente. Nuevo stock: %d unidades%n",
                    productoSeleccionado.getStockDisponible());
            } else {
                System.out.println("La cantidad debe ser mayor a 0");
            }
        } else {
            System.out.println("Opción inválida");
        }
    }
}
