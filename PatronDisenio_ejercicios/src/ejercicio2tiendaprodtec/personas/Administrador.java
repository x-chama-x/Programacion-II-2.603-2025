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

    public void verListadoProveedores() {
        Sistema.getInstancia().verListadoProveedores();
    }

    public void actualizarCliente() {
        Scanner scanner = new Scanner(System.in);
        Sistema sistema = Sistema.getInstancia();

        System.out.println("=== Actualizar Cliente ===");
        System.out.print("Ingrese CUIT del cliente a actualizar: ");
        String cuitBuscar = scanner.nextLine();

        Cliente clienteExistente = sistema.buscarClientePorCuit(cuitBuscar);
        if (clienteExistente == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        System.out.println("Cliente encontrado: " + clienteExistente.obtenerInformacionContacto());

        System.out.print("Ingrese nuevo nombre (Enter para mantener actual): ");
        String nombre = scanner.nextLine();
        nombre = nombre.isEmpty() ? clienteExistente.getNombre() : nombre;

        System.out.print("Ingrese nuevo teléfono (Enter para mantener actual): ");
        String telefono = scanner.nextLine();
        telefono = telefono.isEmpty() ? clienteExistente.getTelefono() : telefono;

        System.out.print("Ingrese nueva clave (Enter para mantener actual): ");
        String clave = scanner.nextLine();
        clave = clave.isEmpty() ? clienteExistente.getClave() : clave;

        // Datos de la dirección
        Direccion direccionActual = clienteExistente.getDireccion();
        System.out.println("\nDatos de la dirección:");

        System.out.print("Calle (Enter para mantener actual): ");
        String calle = scanner.nextLine();
        calle = calle.isEmpty() ? direccionActual.getCalle() : calle;

        System.out.print("Número (Enter para mantener actual): ");
        String numStr = scanner.nextLine();
        Integer numero = numStr.isEmpty() ? direccionActual.getNumero() : Integer.parseInt(numStr);

        System.out.print("Comuna (Enter para mantener actual): ");
        String comuna = scanner.nextLine();
        comuna = comuna.isEmpty() ? direccionActual.getComuna() : comuna;

        System.out.print("Ciudad (Enter para mantener actual): ");
        String ciudad = scanner.nextLine();
        ciudad = ciudad.isEmpty() ? direccionActual.getCiudad() : ciudad;

        System.out.print("Código Postal (Enter para mantener actual): ");
        String codigoPostal = scanner.nextLine();
        codigoPostal = codigoPostal.isEmpty() ? direccionActual.getCodigoPostal() : codigoPostal;

        // Crear nueva dirección y cliente
        Direccion nuevaDireccion = new Direccion(calle, numero, comuna, ciudad, codigoPostal);
        Cliente clienteActualizado = new Cliente(nombre, clienteExistente.getCuit(), telefono, nuevaDireccion, clave);

        // Actualizar en el sistema
        sistema.actualizarCliente(clienteActualizado);

        System.out.println("\nCliente actualizado exitosamente!");
    }

    public void agregarNuevoProveedor() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Crear Nuevo Proveedor ===");

        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese CUIT: ");
        String cuit = scanner.nextLine();

        System.out.print("Ingrese teléfono: ");
        String telefono = scanner.nextLine();

        System.out.print("Ingrese clave: ");
        String clave = scanner.nextLine();

        System.out.print("Ingrese página web: ");
        String paginaWeb = scanner.nextLine();

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
        Proveedor nuevoProveedor = new Proveedor(nombre, cuit, telefono, direccion, clave, paginaWeb);

        // Agregar al sistema
        Sistema.getInstancia().agregarProveedor(nuevoProveedor);

        System.out.println("\nProveedor creado exitosamente!");
    }

    public void actualizarProveedor() {
        Scanner scanner = new Scanner(System.in);
        Sistema sistema = Sistema.getInstancia();

        System.out.println("=== Actualizar Proveedor ===");
        System.out.print("Ingrese CUIT del proveedor a actualizar: ");
        String cuitBuscar = scanner.nextLine();

        Proveedor proveedorExistente = sistema.buscarProveedorPorCuit(cuitBuscar);
        if (proveedorExistente == null) {
            System.out.println("Proveedor no encontrado.");
            return;
        }

        System.out.println("Proveedor encontrado: " + proveedorExistente.obtenerInformacionContacto());

        System.out.print("Ingrese nuevo nombre (Enter para mantener actual): ");
        String nombre = scanner.nextLine();
        nombre = nombre.isEmpty() ? proveedorExistente.getNombre() : nombre;

        System.out.print("Ingrese nuevo teléfono (Enter para mantener actual): ");
        String telefono = scanner.nextLine();
        telefono = telefono.isEmpty() ? proveedorExistente.getTelefono() : telefono;

        System.out.print("Ingrese nueva clave (Enter para mantener actual): ");
        String clave = scanner.nextLine();
        clave = clave.isEmpty() ? proveedorExistente.getClave() : clave;

        System.out.print("Ingrese nueva página web (Enter para mantener actual): ");
        String paginaWeb = scanner.nextLine();
        paginaWeb = paginaWeb.isEmpty() ? proveedorExistente.getPaginaWeb() : paginaWeb;

        // Datos de la dirección
        Direccion direccionActual = proveedorExistente.getDireccion();
        System.out.println("\nDatos de la dirección:");

        System.out.print("Calle (Enter para mantener actual): ");
        String calle = scanner.nextLine();
        calle = calle.isEmpty() ? direccionActual.getCalle() : calle;

        System.out.print("Número (Enter para mantener actual): ");
        String numStr = scanner.nextLine();
        Integer numero = numStr.isEmpty() ? direccionActual.getNumero() : Integer.parseInt(numStr);

        System.out.print("Comuna (Enter para mantener actual): ");
        String comuna = scanner.nextLine();
        comuna = comuna.isEmpty() ? direccionActual.getComuna() : comuna;

        System.out.print("Ciudad (Enter para mantener actual): ");
        String ciudad = scanner.nextLine();
        ciudad = ciudad.isEmpty() ? direccionActual.getCiudad() : ciudad;

        System.out.print("Código Postal (Enter para mantener actual): ");
        String codigoPostal = scanner.nextLine();
        codigoPostal = codigoPostal.isEmpty() ? direccionActual.getCodigoPostal() : codigoPostal;

        // Crear nueva dirección y proveedor
        Direccion nuevaDireccion = new Direccion(calle, numero, comuna, ciudad, codigoPostal);
        Proveedor proveedorActualizado = new Proveedor(nombre, cuitBuscar, telefono, nuevaDireccion, clave, paginaWeb);

        // Actualizar en el sistema
        sistema.actualizarProveedor(proveedorActualizado);

        System.out.println("\nProveedor actualizado exitosamente!");
    }

    public void verProductosEnStock() {
        Sistema.getInstancia().verProductosEnStock();
    }

    public void cargarStockAProductosFaltantes() {
        Sistema.getInstancia().cargarStockAProductosFaltantes();
    }
}
