package models;
import java.time.LocalDate;
public class Estudiante {
    private int legajo;
    private int dni;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    public Estudiante() {}
    public Estudiante(int dni, String nombre, String apellido, LocalDate
            fechaNacimiento) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
    }
    public Estudiante(int legajo, int dni, String nombre, String apellido, LocalDate
            fechaNacimiento) {
        this.legajo = legajo;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
    }
    public int getLegajo() { return legajo; }
    public int getDni() { return dni; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento =
            fechaNacimiento; }
    @Override
    public String toString() {
        return "Estudiante{" +
                "legajo=" + legajo +
                ", dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                '}';
    }
}
