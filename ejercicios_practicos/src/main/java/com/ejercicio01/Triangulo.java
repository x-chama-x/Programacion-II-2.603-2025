package com.ejercicio01;

public class Triangulo {
    private double lado1;
    private double lado2;
    private double lado3;

    public Triangulo(double lado1, double lado2, double lado3) {
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;
    }

    public double getLado1() {
        return lado1;
    }

    public double getLado2() {
        return lado2;
    }

    public double getLado3() {
        return lado3;
    }

    public double calcularArea() {
        double s = (lado1 + lado2 + lado3) / 2;
        return Circulo.redondear(Math.sqrt(s * (s - lado1) * (s - lado2) * (s - lado3)));
    }

    public double calcularPerimetro() {
        return Circulo.redondear(lado1 + lado2 + lado3);
    }
}
