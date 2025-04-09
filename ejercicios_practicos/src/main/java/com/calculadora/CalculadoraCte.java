package com.calculadora;

public class CalculadoraCte {
    public static final double PI = 3.14159;
    public static int cantidadCalculos = 0;

    public static double areaCirculo (double radio) {
        cantidadCalculos++;
        return PI * Math.pow(radio, 2);
    }

    public static double areaCirculo (int radio) {
        cantidadCalculos++;
        return PI * Math.pow(radio, 2);
    }
}
