package ejercicio2tiendaprodtec.personas;

public class Proveedor extends Persona {
    private String paginaWeb;

    public Proveedor(String nombre, String cuit, String telefono, Direccion direccion, String clave, String paginaWeb) {
        super(nombre, cuit, telefono, direccion, clave);
        this.paginaWeb = paginaWeb;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    @Override
    public String obtenerInformacionContacto() {
        return String.format("Proveedor: %s - CUIT: %s - Tel: %s - Web: %s",
            super.getNombre(), super.getCuit(), super.getTelefono(), this.paginaWeb);
    }
}
