package instituto.backend.Models;

public class Materia {
    private final int cod;
    private final String nombre;

    public Materia(int cod, String nombre) {
        this.cod = cod;
        this.nombre = nombre;
    }

    public int getCod() { return cod; }
    public String getNombre() { return nombre; }

    @Override
    public String toString() {
        return cod + "," + nombre;
    }
    
    public static Materia fromString(String linea) {
        String[] partes = linea.split(",");
        return new Materia(Integer.parseInt(partes[0]), partes[1]);
    }
}
