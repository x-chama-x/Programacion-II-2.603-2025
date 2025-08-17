package ejercicio2tiendaprodtec.menu;

import ejercicio2tiendaprodtec.Sistema;
import ejercicio2tiendaprodtec.personas.Cliente;
import java.util.Scanner;

public class MenuCliente implements EstrategiaMenu {
    private Cliente clienteActual;
    private Scanner scanner;
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
        System.out.println("1. Ver productos disponibles");
        System.out.println("2. Realizar compra");
        System.out.println("3. Ver mis compras");
        System.out.println("0. Salir");
    }

    @Override
    public void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                clienteActual.mostrarProductosDisponibles();
                break;
            case 2:
                clienteActual.mostrarProductosDisponibles();
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
