package ejercicio1fabpelotas.funcionalidades;

import ejercicio1fabpelotas.Pelota;

public class CapazDeCoser implements Funcionalidad {
    @Override
    public void ejecutarFuncion(Pelota pelota) {
        pelota.setCosida(true);
    }
}
