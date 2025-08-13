package ejercicio2tiendaprodtec.menu;

public class MenuAdministrador implements EstrategiaMenu{
    @Override
    public void mostrarMenu() {
        System.out.println("=== Menú Administrador ===");
        System.out.println("1. Agregar cliente");
        System.out.println("2. Actualizar cliente");
        System.out.println("3. Eliminar cliente");
        System.out.println("4. Agregar proveedor");
        System.out.println("5. Actualizar stock");
        System.out.println("6. Ver ventas realizadas");
        System.out.println("0. Salir");
    }

    @Override
    public void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1: // Implementar agregar cliente
                break;
            case 2: // Implementar actualizar cliente
                break;
            // ... resto de opciones
        }
    }
}
