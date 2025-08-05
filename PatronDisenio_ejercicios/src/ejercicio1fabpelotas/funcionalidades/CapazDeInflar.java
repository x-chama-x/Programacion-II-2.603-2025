package ejercicio1fabpelotas.funcionalidades;

import ejercicio1fabpelotas.Pelota;

public class CapazDeInflar implements Funcionalidad {
    @Override
    public void ejecutarFuncion(Pelota pelota) {
        pelota.setInflada(true);
    }
}
