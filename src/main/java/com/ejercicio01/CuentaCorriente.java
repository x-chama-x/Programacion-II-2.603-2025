package com.ejercicio01;

public class CuentaCorriente {
    private String titular;
    private double saldo;

    public CuentaCorriente(String titular, double saldo) {
        this.titular = titular;
        this.saldo = saldo;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
        } else {
            System.out.println("El valor a depositar debe ser mayor que cero.");
        }
    }

    public void retirar(double valor) {
        if (valor > 0 && valor <= saldo) {
            saldo -= valor;
        } else {
            System.out.println("El valor a retirar debe ser mayor que cero y menor o igual al saldo.");
        }
    }

}
