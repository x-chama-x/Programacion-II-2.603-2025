package backend.Models;

import java.util.ArrayList;
import java.util.List;

public class Cuartel {
    private int codigo;
    private String sector;
    private List<Soldado> soldados;

    public Cuartel() {
        this.soldados = new ArrayList<>();
    }

    public Cuartel(int codigo, String sector) {
        this.codigo = codigo;
        this.sector = sector;
        this.soldados = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public List<Soldado> getSoldados() {
        return soldados;
    }

    public void setSoldados(List<Soldado> soldados) {
        this.soldados = soldados;
    }

    // Métodos para gestionar la relación
    public void agregarSoldado(Soldado soldado) {
        if (!this.soldados.contains(soldado)) {
            this.soldados.add(soldado);
            soldado.setCuartelCodigo(this.codigo);
        }
    }

    public void removerSoldado(Soldado soldado) {
        this.soldados.remove(soldado);
        soldado.setCuartelCodigo(0); // 0 indica sin cuartel
    }

    public int getCantidadSoldados() {
        return soldados.size();
    }

    @Override
    public String toString() {
        return codigo + ";" + sector;
    }

    public static Cuartel fromString(String linea) {
        String[] datos = linea.split(";");
        if (datos.length == 2) {
            int codigo = Integer.parseInt(datos[0]);
            String sector = datos[1];
            return new Cuartel(codigo, sector);
        }
        throw new IllegalArgumentException("Formato de línea inválido para Cuartel: " + linea);
    }
}
