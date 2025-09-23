package backend.Models;

public class Asignacion {
    private int oficialId;
    private int soldadoId;

    public Asignacion() {}

    public Asignacion(int oficialId, int soldadoId) {
        this.oficialId = oficialId;
        this.soldadoId = soldadoId;
    }

    public int getOficialId() {
        return oficialId;
    }

    public void setOficialId(int oficialId) {
        this.oficialId = oficialId;
    }

    public int getSoldadoId() {
        return soldadoId;
    }

    public void setSoldadoId(int soldadoId) {
        this.soldadoId = soldadoId;
    }

    @Override
    public String toString() {
        return oficialId + ";" + soldadoId;
    }

    public static Asignacion fromString(String linea) {
        String[] datos = linea.split(";");
        if (datos.length == 2) {
            int oficialId = Integer.parseInt(datos[0]);
            int soldadoId = Integer.parseInt(datos[1]);
            return new Asignacion(oficialId, soldadoId);
        }
        throw new IllegalArgumentException("Formato de línea inválido para Asignacion: " + linea);
    }
}
