package ejercicio2tiendaprodtec;

public abstract class Persona {
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
