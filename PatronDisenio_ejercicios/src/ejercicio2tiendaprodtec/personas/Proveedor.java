package ejercicio2tiendaprodtec.personas;

public class Proveedor extends Persona {
    private String paginaWeb;
    public Proveedor(String cuit, String nombre, String telefono, Direccion direccion, String clave, String paginaWeb) {
        super(cuit, nombre, telefono, direccion, clave);
        this.paginaWeb = paginaWeb;
    }

    @Override
    public String obtenerInformacionContacto() {
        return "";
    }
}

