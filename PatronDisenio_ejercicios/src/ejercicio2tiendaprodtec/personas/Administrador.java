package ejercicio2tiendaprodtec.personas;

import ejercicio2tiendaprodtec.Direccion;

public class Administrador extends Persona {
    public Administrador(String cuit, String nombre, String telefono, Direccion direccion) {
        super(cuit, nombre, telefono, direccion);
    }

    @Override
    public String obtenerInformacionContacto() {
        return "";
    }
}

