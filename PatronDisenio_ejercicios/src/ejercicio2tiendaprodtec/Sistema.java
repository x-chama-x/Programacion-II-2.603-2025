package ejercicio2tiendaprodtec;

import ejercicio2tiendaprodtec.productos.Producto;

import java.util.ArrayList;

public class Sistema {
    private static Sistema instanciaUnica;
    private ArrayList<Producto> Stock;

    private Sistema() {
        // Constructor privado para evitar instanciación externa
    }

    public static Sistema getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new Sistema();
        }
        return instanciaUnica;
    }

    public void iniciar() {
        // Lógica para iniciar el sistema
        System.out.println("Sistema iniciado.");
    }
}
