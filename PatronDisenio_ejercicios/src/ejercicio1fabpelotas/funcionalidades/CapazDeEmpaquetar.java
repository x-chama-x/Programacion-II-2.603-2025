package ejercicio1fabpelotas.funcionalidades;

import ejercicio1fabpelotas.Pelota;

public class CapazDeEmpaquetar implements Funcionalidad {
    @Override
    public void ejecutarFuncion(Pelota pelota) {
        pelota.setEmpaquetada(true);
    }
}
