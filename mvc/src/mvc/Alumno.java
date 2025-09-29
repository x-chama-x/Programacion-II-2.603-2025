package mvc;

public class Alumno {
    private final String nombre;
    private int nota;

    public Alumno(String nombre, int nota) {
        this.nombre = nombre;
        this.nota = nota;
    }

    public String getNombre() { return nombre; }
    public int getNota() { return nota; }

    public void setNota(int nota) { this.nota = nota; }
}
