package ejercicio2tiendaprodtec;

public class Proveedor extends Persona{
    private String paginaWeb;
    public Proveedor(String cuit, String nombre, String telefono, Direccion direccion, String paginaWeb) {
        super(cuit, nombre, telefono, direccion);
        this.paginaWeb = paginaWeb;
    }
}

