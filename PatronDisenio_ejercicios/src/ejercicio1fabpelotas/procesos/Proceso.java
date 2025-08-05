package ejercicio1fabpelotas.procesos;

import ejercicio1fabpelotas.Pelota;

public abstract class Proceso {
    private String nombre;
    private Integer complejidad;
    private String descripcion;

    public Proceso(String nombre, Integer complejidad, String descripcion) {
        this.nombre = nombre;
        this.complejidad = complejidad;
        this.descripcion = descripcion;
    }

    public abstract void ejecutarProceso(Pelota pelota);
}
