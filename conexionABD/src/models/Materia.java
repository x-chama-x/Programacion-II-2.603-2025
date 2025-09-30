package models;
public class Materia {
    private int codigo;
    private String nombreMateria;
    public Materia() {}
    public Materia(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }
    public Materia(int codigo, String nombreMateria) {
        this.codigo = codigo;
        this.nombreMateria = nombreMateria;
    }
    public int getCodigo() { return codigo; }
    public String getNombreMateria() { return nombreMateria; }
    @Override
    public String toString() {
        return "Materia{" +
                "codigo=" + codigo +
                ", nombreMateria='" + nombreMateria + '\'' +
                '}';
    }
}
