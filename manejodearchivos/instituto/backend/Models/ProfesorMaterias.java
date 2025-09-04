package instituto.backend.Models;

public class ProfesorMaterias {
    private final int dniProfesor;
    private final int codMateria;

    public ProfesorMaterias(int dniProfesor, int codMateria) {
        this.dniProfesor = dniProfesor;
        this.codMateria = codMateria;
    }

    public int getDniProfesor() {
        return dniProfesor;
    }

    public int getCodMateria() {
        return codMateria;
    }

    @Override
    public String toString() {
        return dniProfesor + "," + codMateria;
    }

    public static ProfesorMaterias fromString(String linea) {
        String[] partes = linea.split(",");
        return new ProfesorMaterias(Integer.parseInt(partes[0]), Integer.parseInt(partes[1]));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ProfesorMaterias that = (ProfesorMaterias) obj;
        return dniProfesor == that.dniProfesor && codMateria == that.codMateria;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(dniProfesor) * 31 + Integer.hashCode(codMateria);
    }
}
