package ejercicio2tiendaprodtec.personas;

public class Cliente extends Persona {
    public Cliente(String nombre, String cuit, String telefono, Direccion direccion, String clave) {
        super(nombre, cuit, telefono, direccion, clave);
    }

    @Override
    public String obtenerInformacionContacto() {
        return String.format("Cliente: %s - CUIT: %s - Tel: %s", super.getNombre(), super.getCuit(), super.getClave());
    }
}
