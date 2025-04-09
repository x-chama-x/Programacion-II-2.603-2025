package com.productoescalar;

public class Main {
    public static void main(String[] args) {
        // Crear dos vectores
        Vector vector1 = new Vector(1.0, 2.0, 3.0);
        Vector vector2 = new Vector(5.0, 5.0, 6.0);

        // Calcular el producto escalar
        double resultado = vector1.productorEscalar(vector2);
        System.out.println("El producto escalar es: " + resultado);
    }
}