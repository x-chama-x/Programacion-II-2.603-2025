package ejercicio2tiendaprodtec.menu;

public class MenuCliente implements EstrategiaMenu {
    @Override
    public void mostrarMenu() {
        System.out.println("=== Menú Cliente ===");
        System.out.println("1. Ver productos disponibles");
        System.out.println("2. Ver precios");
        System.out.println("3. Realizar compra");
        System.out.println("0. Salir");
    }

    @Override
    public void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1: // Implementar ver productos
                break;
            case 2: // Implementar ver precios
                break;
            // ... resto de opciones
        }
    }
}
