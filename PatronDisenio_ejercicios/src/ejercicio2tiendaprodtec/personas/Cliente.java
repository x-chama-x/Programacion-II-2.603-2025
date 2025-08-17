package ejercicio2tiendaprodtec.personas;

import ejercicio2tiendaprodtec.Sistema;
import ejercicio2tiendaprodtec.productos.Producto;
import java.util.ArrayList;

public class Cliente extends Persona {
    private final Sistema sistema;

    public Cliente(String nombre, String cuit, String telefono, Direccion direccion, String clave) {
        super(nombre, cuit, telefono, direccion, clave);
        this.sistema = Sistema.getInstancia();
    }

    @Override
    public String obtenerInformacionContacto() {
        return String.format("Cliente: %s - CUIT: %s - Tel: %s", super.getNombre(), super.getCuit(), super.getTelefono());
    }

    public void mostrarProductosDisponibles() {
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

    public void realizarCompra(int seleccion, int cantidad) {
        ArrayList<Producto> productos = sistema.obtenerProductosEnStock();
        if (productos.isEmpty()) {
            System.out.println("No hay productos disponibles para comprar.");
            return;
        }

        if (seleccion < 1 || seleccion > productos.size()) {
            System.out.println("Selección no válida.");
            return;
        }

        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor a 0.");
            return;
        }

        Producto productoSeleccionado = productos.get(seleccion - 1);
        if (cantidad > productoSeleccionado.getStockDisponible()) {
            System.out.println("No hay suficiente stock disponible.");
            return;
        }

        double total = productoSeleccionado.getPrecioActual() * cantidad;
        sistema.registrarVenta(this, productoSeleccionado, cantidad, total);
        System.out.printf("Compra realizada con éxito. Total: $%.2f%n", total);
    }

    public void verMisCompras() {
        sistema.mostrarComprasCliente(this);
    }
}
