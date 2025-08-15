package ejercicio2tiendaprodtec;

import ejercicio2tiendaprodtec.menu.*;
import ejercicio2tiendaprodtec.personas.*;
import ejercicio2tiendaprodtec.productos.Producto;

import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    private static Sistema instanciaUnica;
    private ArrayList<Producto> Stock;
    private ArrayList<Cliente> clientes;
    private ArrayList<Proveedor> proveedores;
    private ArrayList<Administrador> administradores;
    private MenuContexto menuContexto;

    private Sistema() {
        // Constructor privado para evitar instanciación externa
        this.menuContexto = new MenuContexto();
        this.Stock = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.proveedores = new ArrayList<>();
        this.administradores = new ArrayList<>();
    }

    public static Sistema getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new Sistema();
        }
        return instanciaUnica;
    }

    // Esta función inicia el sistema y muestra el menú inicial
    public void iniciar() {
        crearAdminPorDefecto(); // Crear un administrador por defecto
        iniciarMenuPrincipal();
    }

    private void iniciarMenuPrincipal(){
        menuContexto.setEstrategia(new MenuInicial(menuContexto));
        Scanner scanner = new Scanner(System.in);
        while (true) {
            menuContexto.mostrarMenu();
            System.out.print("Ingrese una opción: ");
            int opcion = scanner.nextInt();
            menuContexto.ejecutarOpcion(opcion);
        }
    }

    // Métodos para gestionar productos, clientes, proveedores y administradores

    public void agregarAdmin(Administrador admin) {
        administradores.add(admin);
    }

    private void crearAdminPorDefecto() {
        Direccion d1 = new Direccion("Triunvirato", 158, "13", "CABA", "1567");
        Administrador a1 = new Administrador("admin", "20-12345678-9", "123456789", d1, "123");
        agregarAdmin(a1);
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void agregarProveedor(Proveedor proveedor) {
        proveedores.add(proveedor);
    }

    public Administrador validarAdministrador(String nombre, String clave) {
        for (Administrador admin : administradores) {
            if (admin.getNombre().equals(nombre) && admin.getClave().equals(clave)) {
                return admin;
            }
        }
        return null;
    }

    public boolean validarCliente(String nombre, String clave) {
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equals(nombre) && cliente.getClave().equals(clave)) {
                return true;
            }
        }
        return false;
    }

    public void verListadoClientes(){
        System.out.println("=== Listado de Clientes ===");
        for (Cliente cliente : clientes) {
            System.out.println(cliente.obtenerInformacionContacto());
        }
    }

    public void verListadoProveedores() {
        System.out.println("=== Listado de Proveedores ===");
        for (Proveedor proveedor : proveedores) {
            System.out.println(proveedor.obtenerInformacionContacto());
        }
    }

    public Cliente buscarClientePorCuit(String cuit) {
        for (Cliente cliente : clientes) {
            if (cliente.getCuit().equals(cuit)) {
                return cliente;
            }
        }
        return null;
    }

    public void actualizarCliente(Cliente clienteActualizado) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getCuit().equals(clienteActualizado.getCuit())) {
                clientes.set(i, clienteActualizado);
                return;
            }
        }
    }

    public Proveedor buscarProveedorPorCuit(String cuit) {
        for (Proveedor proveedor : proveedores) {
            if (proveedor.getCuit().equals(cuit)) {
                return proveedor;
            }
        }
        return null;
    }

    public void actualizarProveedor(Proveedor proveedorActualizado) {
        for (int i = 0; i < proveedores.size(); i++) {
            if (proveedores.get(i).getCuit().equals(proveedorActualizado.getCuit())) {
                proveedores.set(i, proveedorActualizado);
                return;
            }
        }
    }

    public void verProductosEnStock() {
        MenuStock menuStock = new MenuStock();
        menuStock.mostrarMenu();
    }

    public ArrayList<Producto> getStock() {
        return Stock;
    }
}
