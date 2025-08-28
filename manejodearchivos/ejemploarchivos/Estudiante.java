package ejemploarchivos;

public class Estudiante {

    private String dni;
    private String nombre;
    private String apellido;
    private String fechaNac;

    public Estudiante(String dni, String nombre, String apellido, String fechaNac){
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
    }

    public String getDni() { return dni; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getFechaNac() { return fechaNac; }

    @Override
    public String toString() {
        return dni + "," + nombre + "," + apellido + "," + fechaNac;
    }

    public static Estudiante fromString(String linea) {
        String[] partes = linea.split(",");
        return new Estudiante(partes[0], partes[1], partes[2], partes[3]);
    }
}
