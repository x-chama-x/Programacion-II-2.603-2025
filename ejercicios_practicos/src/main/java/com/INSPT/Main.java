package com.INSPT;

public class Main {
    public static void main(String[] args) {
        Persona persona = new Persona("Juan", "Pérez", 12345678, "JuanPerez@hotmail.com");
        persona.establecerEdad(-50);
        persona.mostrarDatos();
        persona.establecerEdad(25);
        persona.mostrarDatos();
    }
}
