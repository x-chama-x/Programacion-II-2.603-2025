package com.ejercicio01;

public class Circulo {
    private double radio;

    public Circulo(double radio) {
        this.radio = radio;
    }

    public double getRadio() {
        return radio;
    }

    public double calcularArea() {
        return redondear(Math.PI * Math.pow(radio, 2));
    }

    public double calcularPerimetro() {
        return redondear(2 * Math.PI * radio);
    }

    public static double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}
