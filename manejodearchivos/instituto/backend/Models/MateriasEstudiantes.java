package instituto.backend.Models;

public class MateriasEstudiantes {
    private final int codMateria;
    private final int codEstudiante;

    public MateriasEstudiantes(int codMateria, int codEstudiante) {
        this.codMateria = codMateria;
        this.codEstudiante = codEstudiante;
    }

    public int getCodMateria() {
        return codMateria;
    }

    public int getCodEstudiante() {
        return codEstudiante;
    }

    @Override
    public String toString() {
        return codMateria + "," + codEstudiante;
    }

    public static MateriasEstudiantes fromString(String linea) {
        String[] partes = linea.split(",");
        return new MateriasEstudiantes(Integer.parseInt(partes[0]), Integer.parseInt(partes[1]));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MateriasEstudiantes that = (MateriasEstudiantes) obj;
        return codMateria == that.codMateria && codEstudiante == that.codEstudiante;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(codMateria) * 31 + Integer.hashCode(codEstudiante);
    }
}
