package ejercicio2tiendaprodtec.personas;

public abstract class Persona implements Contactable {
    private String nombre;
    private String cuit;
    private String telefono;
    private Direccion direccion;
    private String clave;

    public Persona(String nombre, String cuit, String telefono, Direccion direccion, String clave) {
        this.nombre = nombre;
        this.cuit = cuit;
        this.telefono = telefono;
        this.direccion = direccion;
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCuit() {
        return cuit;
    }

    public String getTelefono() {
        return telefono;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public String getClave() {
        return clave;
    }
}
