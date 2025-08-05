package ejercicio1fabpelotas;

import java.util.ArrayList;

public abstract class Empleado {
    private Integer DNI;
    private String nomApe;
    private ArrayList<Integer> telefonos;

    public Empleado(Integer DNI, String nomApe, ArrayList<Integer> telefonos) {
        this.DNI = DNI;
        this.nomApe = nomApe;
        this.telefonos = telefonos;
    }

    public void mostrarDatos() {
        System.out.println("DNI: " + DNI);
        System.out.println("Nombre y Apellido: " + nomApe);
        System.out.print("Teléfonos: ");
        for (Integer telefono : telefonos) {
            System.out.print(telefono + " ");
        }
        System.out.println();
    }

    public String getNomApe() {
        return nomApe;
    }
}
