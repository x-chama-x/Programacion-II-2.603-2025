package ejercicio1fabpelotas.procesos;

import ejercicio1fabpelotas.Maquina;
import ejercicio1fabpelotas.Pelota;

import java.util.ArrayList;

public class Cosido extends Proceso {
    private ArrayList<Maquina> maquinas;

    public Cosido(String nombre, Integer complejidad, String descripcion, ArrayList<Maquina> maquinas) {
        super(nombre, complejidad, descripcion);
        this.maquinas = maquinas;
    }

    @Override
    public void ejecutarProceso(Pelota pelota) {
        if (maquinas != null && !maquinas.isEmpty()) {
            for (Maquina maquina : maquinas) {
                maquina.ejecutarFuncion(pelota);
            }
        } else {
            System.out.println("No hay máquinas disponibles para el proceso de cosido.");
        }
    }
}
