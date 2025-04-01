package com.ejercicio01;

public class Main {
    public static void main(String[] args) {

        // probar la clase Punto
        Punto punto1 = new Punto(3.0, 4.0);
        Punto punto2 = new Punto(6.0, 8.0);
        System.out.println("Distancia entre puntos: " + punto1.calcularDistancia(punto2));

        // probar la clase Circulo
        Circulo circulo = new Circulo(5.0);
        System.out.println("Area triangulo: " + circulo.calcularArea());
        System.out.println("Perimetro triangulo: " + circulo.calcularPerimetro());

        // probar la clase Triangulo
        Triangulo triangulo = new Triangulo(3.0, 4.0, 5.0);
        System.out.println("Area circulo: " + triangulo.calcularArea());
        System.out.println("Perimetro circulo: " + triangulo.calcularPerimetro());

        // probar la clase NumeroFraccionario
        NumeroFraccionario fraccion1 = new NumeroFraccionario(1, 2);
        NumeroFraccionario fraccion2 = new NumeroFraccionario(3, 4);

        NumeroFraccionario resultado = fraccion1.suma(fraccion2);
        resultado.simplificar();
        System.out.println("Suma de fracciones: " + resultado.getNumerador() + "/" + resultado.getDenominador());

        // probar la clase CuentaCorriente
        CuentaCorriente cuenta = new CuentaCorriente("Juan Perez", 1000.0);
        System.out.println("Titular: " + cuenta.getTitular());
        System.out.println("Saldo inicial: " + cuenta.getSaldo());
        cuenta.depositar(500.0);
        System.out.println("Saldo después del depósito: " + cuenta.getSaldo());
        cuenta.retirar(200.0);
        System.out.println("Saldo después del retiro: " + cuenta.getSaldo());

        // probar la clase Ascensor
        Ascensor ascensor = new Ascensor();
        System.out.println("Piso actual: " + ascensor.getPisoActual());
        ascensor.subir(3);
        System.out.println("Piso actual después de subir: " + ascensor.getPisoActual());
        ascensor.bajar(2);
        System.out.println("Piso actual después de bajar: " + ascensor.getPisoActual());

    }
}