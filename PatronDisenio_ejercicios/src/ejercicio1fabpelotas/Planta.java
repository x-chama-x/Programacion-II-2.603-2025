package ejercicio1fabpelotas;

import ejercicio1fabpelotas.procesos.Proceso;

import java.util.ArrayList;

public class Planta {
    private String color;
    private double superficie;
    private ArrayList<Maquina> maquinas;
    private ArrayList<Proceso> procesos;

    public Planta(String color, double superficie) {
        this.color = color;
        this.superficie = superficie;
        this.maquinas = new ArrayList<>();
        this.procesos = new ArrayList<>();
    }

    public void agregarMaquina(Maquina maquina) {
        maquinas.add(maquina);
    }

    public void agregarProceso(Proceso proceso) {
        procesos.add(proceso);
    }

    public Pelota fabricarPelota() {
        Pelota pelota = new Pelota();

        // Ejecutar todos los procesos configurados en la planta
        for (Proceso proceso : procesos) {
            proceso.ejecutarProceso(pelota);
        }

        return pelota;
    }

    public ArrayList<Maquina> getMaquinas() {
        return maquinas;
    }
}
