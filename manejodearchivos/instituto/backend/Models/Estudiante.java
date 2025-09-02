package instituto.backend.Models;

public class Estudiante extends Persona {
    
    public Estudiante(int dni, String nombre, int edad) {
        super(dni, nombre, edad);
    }
    
    public static Estudiante fromString(String linea) {
        String[] partes = linea.split(",");
        return new Estudiante(Integer.parseInt(partes[0]), partes[1], Integer.parseInt(partes[2]));
    }
}
