package ejercicio2tiendaprodtec.personas;

import ejercicio2tiendaprodtec.Direccion;

public abstract class Persona implements Contactable {
    private String cuit;
    private String nombre;
    private String telefono;
    private Direccion direccion;

    public Persona(String cuit, String nombre, String telefono, Direccion direccion) {
        this.cuit = cuit;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }
}
