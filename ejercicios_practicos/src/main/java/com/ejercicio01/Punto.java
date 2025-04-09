package com.ejercicio01;

public class Punto {
    private double x;
    private double y;

    public Punto(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double calcularDistancia(Punto otroPunto) {
        return Math.sqrt(Math.pow(otroPunto.getX() - x, 2) + Math.pow(otroPunto.getY() - y, 2));
    }
}
