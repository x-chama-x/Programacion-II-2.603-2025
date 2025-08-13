package ejercicio2tiendaprodtec;

import ejercicio2tiendaprodtec.menu.*;
import ejercicio2tiendaprodtec.productos.Producto;

import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    private static Sistema instanciaUnica;
    private ArrayList<Producto> Stock;
    private MenuContexto menuContexto;

    private Sistema() {
        // Constructor privado para evitar instanciación externa
        this.menuContexto = new MenuContexto();
    }

    public static Sistema getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new Sistema();
        }
        return instanciaUnica;
    }

    public void iniciar() {
        menuContexto.setEstrategia(new MenuInicial(menuContexto));
        Scanner scanner = new Scanner(System.in);
        while (true) {
            menuContexto.mostrarMenu();
            System.out.print("Ingrese una opción: ");
            int opcion = scanner.nextInt();
            menuContexto.ejecutarOpcion(opcion);
        }
    }
}
