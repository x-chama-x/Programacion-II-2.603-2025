package ejercicio2tiendaprodtec.menu;

import ejercicio2tiendaprodtec.Sistema;
import ejercicio2tiendaprodtec.productos.Producto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuStock implements EstrategiaMenu {
    private Sistema sistema;

    public MenuStock() {
        this.sistema = Sistema.getInstancia();
    }

    @Override
    public void mostrarMenu() {
        System.out.println("\n=== Productos en Stock por Categoría ===\n");

        if (sistema.getStock().isEmpty()) {
            System.out.println("No hay productos en stock.");
            return;
        }

        Map<String, List<Producto>> productosPorCategoria = new HashMap<>();

        // Agrupar productos por categoría
        for (Producto producto : sistema.getStock()) {
            String nombreCategoria = producto.getCategoria().getNombre();
            productosPorCategoria.computeIfAbsent(nombreCategoria, k -> new ArrayList<>()).add(producto);
        }

        int opcion = 1;
        for (Map.Entry<String, List<Producto>> entrada : productosPorCategoria.entrySet()) {
            System.out.println("\n>>> " + entrada.getKey() + " <<<");
            List<Producto> productosCategoria = entrada.getValue();

            for (Producto p : productosCategoria) {
                System.out.printf("%d. %-30s | Precio: $%.2f | Stock: %d unidades%n",
                        opcion++,
                        p.getNombre(),
                        p.getPrecioActual(),
                        p.getStockDisponible());
            }
        }
        System.out.println("\n0. Volver al menú anterior");
        System.out.println("\n=====================================");
    }

    @Override
    public void ejecutarOpcion(int opcion) {
        if (opcion == 0) {
            // Volver al menú anterior
            return;
        }
    }
}
