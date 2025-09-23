package backend.Models;

import java.util.ArrayList;
import java.util.List;

public class Oficial extends Persona {
    private String rango;
    private List<Soldado> soldados;

    public Oficial() {
        super();
        this.soldados = new ArrayList<>();
    }

    public Oficial(String dni, String nombre, String apellido, String rango) {
        super(dni, nombre, apellido);
        this.rango = rango;
        this.soldados = new ArrayList<>();
    }

    public Oficial(int id, String dni, String nombre, String apellido, String rango) {
        super(id, dni, nombre, apellido);
        this.rango = rango;
        this.soldados = new ArrayList<>();
    }

    public String getRango() {
        return rango;
    }

    public void setRango(String rango) {
        this.rango = rango;
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
            soldado.setOficialId(this.getId());
        }
    }

    public void removerSoldado(Soldado soldado) {
        this.soldados.remove(soldado);
        soldado.setOficialId(0); // 0 indica sin oficial
    }

    public int getCantidadSoldados() {
        return soldados.size();
    }

    @Override
    public String toString() {
        return getId() + ";" + getDni() + ";" + getNombre() + ";" + getApellido() + ";" + rango;
    }

    public static Oficial fromString(String linea) {
        String[] datos = linea.split(";");
        if (datos.length == 5) {
            int id = Integer.parseInt(datos[0]);
            String dni = datos[1];
            String nombre = datos[2];
            String apellido = datos[3];
            String rango = datos[4];
            return new Oficial(id, dni, nombre, apellido, rango);
        }
        throw new IllegalArgumentException("Formato de línea inválido para Oficial: " + linea);
    }
}
