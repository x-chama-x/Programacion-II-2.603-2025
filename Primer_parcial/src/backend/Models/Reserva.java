package backend.Models;

public class Reserva {
    private String codigoDeCuartel;
    private int soldadoId;

    public Reserva() {}

    public Reserva(String codigoDeCuartel, int soldadoId) {
        this.codigoDeCuartel = codigoDeCuartel;
        this.soldadoId = soldadoId;
    }

    public String getCodigoDeCuartel() {
        return codigoDeCuartel;
    }

    public void setCodigoDeCuartel(String codigoDeCuartel) {
        this.codigoDeCuartel = codigoDeCuartel;
    }

    public int getSoldadoId() {
        return soldadoId;
    }

    public void setSoldadoId(int soldadoId) {
        this.soldadoId = soldadoId;
    }

    @Override
    public String toString() {
        return codigoDeCuartel + ";" + soldadoId;
    }

    public static Reserva fromString(String linea) {
        String[] datos = linea.split(";");
        if (datos.length == 2) {
            String codigoDeCuartel = datos[0];
            int soldadoId = Integer.parseInt(datos[1]);
            return new Reserva(codigoDeCuartel, soldadoId);
        }
        throw new IllegalArgumentException("Formato de línea inválido para Reserva: " + linea);
    }
}
