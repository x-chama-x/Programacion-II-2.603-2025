package backend.Models;

public abstract class Persona {
    private static int contadorId = 1;
    protected int id;
    protected String dni;
    protected String nombre;
    protected String apellido;

    public Persona() {
        this.id = generarIdUnico();
    }

    public Persona(String dni, String nombre, String apellido) {
        this.id = generarIdUnico();
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Persona(int id, String dni, String nombre, String apellido) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        actualizarContador(id);
    }

    private static synchronized int generarIdUnico() {
        return contadorId++;
    }

    private static synchronized void actualizarContador(int id) {
        if (id >= contadorId) {
            contadorId = id + 1;
        }
    }

    public static int getProximoId() {
        return contadorId;
    }

    public static void reiniciarContador() {
        contadorId = 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombreCompleto() {
        return this.nombre + " " + this.apellido;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'';
    }
}
