package ejercicio2tiendaprodtec.personas;

import ejercicio2tiendaprodtec.Sistema;

import java.util.Scanner;

public class Administrador extends Persona {
    public Administrador(String nombre, String cuit, String telefono, Direccion direccion, String clave) {
        super(nombre, cuit, telefono, direccion, clave);
    }

    @Override
    public String obtenerInformacionContacto() {
        return String.format("Administrador: %s - CUIT: %s", super.getNombre(), super.getCuit());
    }

    public void agregarNuevoCliente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Crear Nuevo Cliente ===");

        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese CUIT: ");
        String cuit = scanner.nextLine();

        System.out.print("Ingrese teléfono: ");
        String telefono = scanner.nextLine();

        System.out.print("Ingrese clave: ");
        String clave = scanner.nextLine();

        // Datos de la dirección
        System.out.println("\nDatos de la dirección:");
        System.out.print("Calle: ");
        String calle = scanner.nextLine();

        System.out.print("Número: ");
        Integer numero = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        System.out.print("Comuna: ");
        String comuna = scanner.nextLine();

        System.out.print("Ciudad: ");
        String ciudad = scanner.nextLine();

        System.out.print("Código Postal: ");
        String codigoPostal = scanner.nextLine();

        // Crear objetos
        Direccion direccion = new Direccion(calle, numero, comuna, ciudad, codigoPostal);
        Cliente nuevoCliente = new Cliente(nombre, cuit, telefono, direccion, clave);

        // Agregar al sistema
        Sistema.getInstancia().agregarCliente(nuevoCliente);

        System.out.println("\nCliente creado exitosamente!");
    }

    public void verListadoClientes() {
        Sistema.getInstancia().verListadoClientes();
    }
}
