package com.INSPT;

public class Persona {
    private String nombre;
    private String apellido;
    private int DNI;
    private int edad;
    private String email;

    public Persona(String nombre, String apellido, int DNI, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.edad = 0;
        this.email = email;
    }

    public void establecerEdad(int edad) {
        if (edadNegativa(edad)) {
            System.out.println("La edad no puede ser negativa.");
            System.out.println("Se ha establecido la edad a 0.");
        } else {
            this.edad = edad;
        }
    }


    public String getNombre() {
        return nombre;
    }

    public String getApellido () {
        return apellido;
    }

    public int getDNI () {
        return DNI;
    }

    public int getEdad () {
        return edad;
    }

    public Boolean edadNegativa (int edad) {
        Boolean retorno = false;
        if (edad < 0) {
            retorno = true;
        }
        return retorno;
    }

    public Boolean esMayorDeEdad () {
        Boolean retorno = false;
        if (this.edad >= 18) {
            retorno = true;
        }
        return retorno;
    }

    public void mostrarDatos() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", DNI='" + DNI + '\'' +
                ", edad=" + edad +
                '}';
    }
}
