package instituto.backend.Models;

public class Persona {
    
    private final int dni;
    private final String nombre;
    private final int edad;

    public Persona(int dni, String nombre, int edad) {
        this.dni = dni;
        this.nombre = nombre;
        this.edad = edad;
    }

    public int getDni() { return dni; }
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }

    @Override
    public String toString() {
        return dni + "," + nombre + "," + edad;
    }
}
