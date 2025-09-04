package instituto.backend.Models;

public class Profesor extends Persona {

    public Profesor(int dni, String nombre, String apellido, String fechaNac) {
        super(dni, nombre, apellido, fechaNac);
    }

    public static Profesor fromString(String linea) {
        String[] partes = linea.split(",");
        return new Profesor(Integer.parseInt(partes[0]), partes[1], partes[2], partes[3]);
    }
}
