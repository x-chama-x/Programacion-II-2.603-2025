package com.ejercicio01;

public class NumeroFraccionario {
    private int numerador;
    private int denominador;

    public NumeroFraccionario(int numerador, int denominador) {
        this.numerador = numerador;
        this.denominador = denominador;
    }

    public int getNumerador() {
        return numerador;
    }

    public int getDenominador() {
        return denominador;
    }

    public NumeroFraccionario suma(NumeroFraccionario otro) {
        int nuevoNumerador = this.getNumerador() * otro.getDenominador() + otro.getNumerador() * this.getDenominador();
        int nuevoDenominador = this.getDenominador() * otro.getDenominador();
        return new NumeroFraccionario(nuevoNumerador, nuevoDenominador);
    }

    public void simplificar() {
        int mcd = mcd(this.numerador, this.denominador);
        this.numerador /= mcd;
        this.denominador /= mcd;
    }

    private int mcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return mcd(b, a % b);
    }
}
