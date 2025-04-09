package com.calculadora;

import static com.calculadora.CalculadoraCte.*;

public class Main {
    public static void main(String[] args) {

        // Calcular el área de dos círculos diferentes
        double areaCirculo1 = areaCirculo(5.0);
        double areaCirculo2 = areaCirculo(10);

        // Mostrar los resultados
        System.out.println("Área del primer círculo: " + areaCirculo1);
        System.out.println("Área del segundo círculo: " + areaCirculo2);

        // Mostrar la cantidad de cálculos realizados
        System.out.println("Cantidad de cálculos realizados: " + cantidadCalculos);
    }
}
