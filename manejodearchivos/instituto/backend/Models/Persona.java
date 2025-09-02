package instituto.backend.Models;

public class Persona {
    
    private final int dni;
    private final String nombre;
    private final String apellido;
    private final String fechaNac;

    public Persona(int dni, String nombre, String apellido, String fechaNac) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
    }

    public int getDni() { return dni; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getFechaNac() { return fechaNac; }

    @Override
    public String toString() {
        return dni + "," + nombre + "," + apellido + "," + fechaNac;
    }
}
