package instituto.backend.Models;

public class Materia {
    private final int codigo;
    private final String nombre;

    public Materia(int codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return codigo + "," + nombre;
    }
    
    public static Materia fromString(String linea) {
        String[] partes = linea.split(",");
        return new Materia(Integer.parseInt(partes[0]), partes[1]);
    }
}
