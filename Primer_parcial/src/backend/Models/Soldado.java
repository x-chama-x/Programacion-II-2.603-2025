package backend.Models;

public class Soldado extends Persona {
    private int cuartelCodigo;
    private int oficialId;

    public Soldado() {
        super();
    }

    public Soldado(String dni, String nombre, String apellido) {
        super(dni, nombre, apellido);
    }

    public Soldado(String dni, String nombre, String apellido, int cuartelCodigo, int oficialId) {
        super(dni, nombre, apellido);
        this.cuartelCodigo = cuartelCodigo;
        this.oficialId = oficialId;
    }

    public Soldado(int id, String dni, String nombre, String apellido, int cuartelCodigo, int oficialId) {
        super(id, dni, nombre, apellido);
        this.cuartelCodigo = cuartelCodigo;
        this.oficialId = oficialId;
    }

    public int getCuartelCodigo() {
        return cuartelCodigo;
    }

    public void setCuartelCodigo(int cuartelCodigo) {
        this.cuartelCodigo = cuartelCodigo;
    }

    public int getOficialId() {
        return oficialId;
    }

    public void setOficialId(int oficialId) {
        this.oficialId = oficialId;
    }

    @Override
    public String toString() {
        return getId() + ";" + getDni() + ";" + getNombre() + ";" + getApellido() + ";" + cuartelCodigo + ";" + oficialId;
    }

    public static Soldado fromString(String linea) {
        String[] datos = linea.split(";");
        if (datos.length == 6) {
            int id = Integer.parseInt(datos[0]);
            String dni = datos[1];
            String nombre = datos[2];
            String apellido = datos[3];
            int cuartelCodigo = Integer.parseInt(datos[4]);
            int oficialId = Integer.parseInt(datos[5]);
            return new Soldado(id, dni, nombre, apellido, cuartelCodigo, oficialId);
        }
        throw new IllegalArgumentException("Formato de línea inválido para Soldado: " + linea);
    }
}
