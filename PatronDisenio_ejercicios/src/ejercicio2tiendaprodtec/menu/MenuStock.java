package ejercicio2tiendaprodtec.menu;

import ejercicio2tiendaprodtec.Sistema;
import ejercicio2tiendaprodtec.productos.Producto;

import java.util.*;

public class MenuStock implements EstrategiaMenu {
    private Sistema sistema;
    private Map<Integer, String> mapaCategorias;

    public MenuStock() {
        this.sistema = Sistema.getInstancia();
        this.mapaCategorias = new HashMap<>();
    }

    @Override
    public void mostrarMenu() {
        System.out.println("\n=== Categorías de Productos ===\n");

        if (sistema.getStock().isEmpty()) {
            System.out.println("No hay productos en stock.");
            return;
        }

        // Obtener categorías únicas
        Set<String> categorias = new HashSet<>();
        for (Producto producto : sistema.getStock()) {
            categorias.add(producto.getCategoria().getNombre());
        }

        // Mostrar categorías numeradas
        int opcion = 1;
        mapaCategorias.clear();
        for (String categoria : categorias) {
            System.out.println(opcion + ". " + categoria);
            mapaCategorias.put(opcion, categoria);
            opcion++;
        }

        System.out.println("\n0. Volver al menú anterior");
        System.out.println("\n============================");
    }

    @Override
    public void ejecutarOpcion(int opcion) {
        if (opcion == 0) {
            return;
        }

        String categoriaSeleccionada = mapaCategorias.get(opcion);
        if (categoriaSeleccionada != null) {
            mostrarProductosCategoria(categoriaSeleccionada);
        } else {
            System.out.println("Opción inválida");
        }
    }

    private void mostrarProductosCategoria(String categoria) {
        System.out.println("\n=== Productos de la categoría: " + categoria + " ===\n");

        for (Producto p : sistema.getStock()) {
            if (p.getCategoria().getNombre().equals(categoria)) {
                System.out.printf("%-30s | Precio: $%.2f | Stock: %d unidades%n",
                        p.getNombre(),
                        p.getPrecioActual(),
                        p.getStockDisponible());

                System.out.print(sistema.obtenerInformacionProveedoresProducto(p));
                System.out.println(); // Línea en blanco entre productos
            }
        }
        System.out.println("\nPresione Enter para continuar...");
        new Scanner(System.in).nextLine();
    }
}
