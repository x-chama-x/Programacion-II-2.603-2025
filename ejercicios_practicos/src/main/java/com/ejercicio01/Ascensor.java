package com.ejercicio01;

public class Ascensor {
    private int pisoActual;
    private final int PISO_MAX = 10;
    private final int PISO_MIN = 0;

    public Ascensor() {
        this.pisoActual = 0;
    }

    public int getPisoActual() {
        return pisoActual;
    }

    public void subir(int pisos) {
        if (pisoActual + pisos <= PISO_MAX) {
            pisoActual += pisos;
        } else {
            System.out.println("No se puede subir más allá del piso " + PISO_MAX);
        }
    }

    public void bajar(int pisos) {
        if (pisoActual - pisos >= PISO_MIN) {
            pisoActual -= pisos;
        } else {
            System.out.println("No se puede bajar más allá del piso " + PISO_MIN);
        }
    }
}
