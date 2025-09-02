package instituto.backend.Models;

public class Estudiante extends Persona {
    
    public Estudiante(int dni, String nombre, String apellido, String fechaNac) {
        super(dni, nombre, apellido, fechaNac);
    }
    
    public static Estudiante fromString(String linea) {
        String[] partes = linea.split(",");
        return new Estudiante(Integer.parseInt(partes[0]), partes[1], partes[2], partes[3]);
    }
}
