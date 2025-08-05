package ejercicio1fabpelotas.procesos;

import ejercicio1fabpelotas.Maquina;
import ejercicio1fabpelotas.Pelota;

public class Inflado extends Proceso {
    private Maquina maquina;

    public Inflado(String nombre, Integer complejidad, String descripcion, Maquina maquina) {
        super(nombre, complejidad, descripcion);
        this.maquina = maquina;
    }

    @Override
    public void ejecutarProceso(Pelota pelota){
        if (maquina != null) {
            maquina.ejecutarFuncion(pelota);
        } else {
            System.out.println("No hay una máquina disponible para inflar.");
        }
    }
}
