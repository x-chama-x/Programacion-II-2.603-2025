package ejercicio2tiendaprodtec;

import ejercicio2tiendaprodtec.categorias.*;
import ejercicio2tiendaprodtec.menu.*;
import ejercicio2tiendaprodtec.personas.*;
import ejercicio2tiendaprodtec.productos.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    private static Sistema instanciaUnica;
    private ArrayList<Producto> Stock;
    private ArrayList<Cliente> clientes;
    private ArrayList<Proveedor> proveedores;
    private ArrayList<Administrador> administradores;
    private ArrayList<Venta> ventas;
    private MenuContexto menuContexto;
    private int ultimoNumeroFactura = 0;

    private Sistema() {
        // Constructor privado para evitar instanciación externa
        this.menuContexto = new MenuContexto();
        this.Stock = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.proveedores = new ArrayList<>();
        this.administradores = new ArrayList<>();
        this.ventas = new ArrayList<>();
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
        inicializarProductos(); // Agregar productos iniciales
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
        System.out.println("\n=== Asignar Producto al Proveedor ===");
        System.out.println("Productos disponibles:");
        for (int i = 0; i < Stock.size(); i++) {
            System.out.println((i + 1) + ". " + Stock.get(i).getNombre());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nSeleccione el número del producto a asignar (0 para ninguno): ");
        int opcion = scanner.nextInt();

        if (opcion > 0 && opcion <= Stock.size()) {
            Producto productoSeleccionado = Stock.get(opcion - 1);
            proveedor.asignarProducto(productoSeleccionado);
            System.out.println("Producto asignado exitosamente!");
        }

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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese una opción: ");
        int opcion = scanner.nextInt();
        menuStock.ejecutarOpcion(opcion);
    }

    public ArrayList<Producto> getStock() {
        return Stock;
    }

    public void agregarProductoAlStock(Producto producto) {
        Stock.add(producto);
    }

    private void inicializarProductos() {
        // Crear categorías
        Televisor televisores = new Televisor(1, "Televisores", "Televisores de última generación");
        Celular celulares = new Celular(2, "Celulares", "Smartphones de diferentes marcas");

        // Crear productos con stock inicial
        televisor_hitachi_C50 tv1 = new televisor_hitachi_C50(1, "Hitachi 50 pulgadas Smart", 250000.0, 5, televisores);
        Samsung_a15 cel1 = new Samsung_a15(2, "Samsung A15 128GB", 180000.0, 10, celulares);

        // Agregar productos al stock
        agregarProductoAlStock(tv1);
        agregarProductoAlStock(cel1);

        // Crear proveedores iniciales y asignarles productos
        Direccion direccionProv1 = new Direccion("Av. Corrientes", 1234, "CABA", "Buenos Aires", "C1043");
        Direccion direccionProv2 = new Direccion("Av. Santa Fe", 2468, "CABA", "Buenos Aires", "C1425");

        Proveedor prov1 = new Proveedor("ElectroTech", "30-12345678-9", "11-1234-5678", direccionProv1, "prov1", "www.electrotech.com");
        Proveedor prov2 = new Proveedor("SmartPhone Store", "30-98765432-1", "11-8765-4321", direccionProv2, "prov2", "www.smartphonestore.com");

        // Asignar productos a proveedores
        prov1.asignarProducto(tv1);
        prov2.asignarProducto(cel1);

        // Agregar proveedores al sistema
        proveedores.add(prov1);
        proveedores.add(prov2);
    }

    // Metodo para ver productos y sus proveedores
    public void verProductosYProveedores() {
        System.out.println("\n=== Productos y sus Proveedores ===");
        for (Producto producto : Stock) {
            System.out.println("\nProducto: " + producto.getNombre());
            System.out.println("Proveedores:");
            if (producto.getProveedores().isEmpty()) {
                System.out.println("- No tiene proveedores asignados");
            } else {
                for (Proveedor proveedor : producto.getProveedores()) {
                    System.out.println("- " + proveedor.getNombre() + " (CUIT: " + proveedor.getCuit() + ")");
                }
            }
        }
    }

    public void cargarStockAProductosFaltantes() {
        MenuAgregarStock menuAgregarStock = new MenuAgregarStock();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            menuAgregarStock.mostrarMenu();
            System.out.print("\nSeleccione el producto a cargar (0 para salir): ");
            int opcion = scanner.nextInt();
            if (opcion == 0) break;

            menuAgregarStock.ejecutarOpcion(opcion);
        }
    }

    public String obtenerInformacionProveedoresProducto(Producto producto) {
        StringBuilder info = new StringBuilder();
        info.append("Proveedores:\n");

        if (producto.getProveedores().isEmpty()) {
            info.append("   - No tiene proveedores asignados\n");
        } else {
            for (Proveedor proveedor : producto.getProveedores()) {
                info.append(String.format("   - %s (CUIT: %s)%n",
                    proveedor.getNombre(),
                    proveedor.getCuit()));
            }
        }
        return info.toString();
    }

    public Venta iniciarNuevaVenta(Cliente cliente) {
        ultimoNumeroFactura++;
        Venta nuevaVenta = new Venta(ultimoNumeroFactura, cliente);
        ventas.add(nuevaVenta);
        return nuevaVenta;
    }

    public boolean realizarCompra(Cliente cliente, Producto producto, int cantidad) {
        if (producto.getStockDisponible() >= cantidad) {
            Venta venta = iniciarNuevaVenta(cliente);
            double precioUnitario = producto.getPrecioActual();
            ProductoVendido productoVendido = new ProductoVendido(producto, precioUnitario, cantidad);
            venta.agregarProducto(productoVendido);

            // Actualizar stock
            producto.setStockDisponible(producto.getStockDisponible() - cantidad);
            return true;
        }
        return false;
    }

    public void registrarVenta(Cliente cliente, Producto producto, int cantidad, double total) {
        if (producto.getStockDisponible() >= cantidad) {
            producto.reducirStock(cantidad);
            Venta venta = new Venta(++ultimoNumeroFactura, cliente, producto, cantidad, total);
            ventas.add(venta);
        } else {
            System.out.println("No hay suficiente stock disponible.");
        }
    }

    public void mostrarComprasCliente(Cliente cliente) {
        System.out.println("\n=== Mis Compras ===");
        boolean tieneCompras = false;

        for (Venta venta : ventas) {
            if (venta.getClienteAssociado().equals(cliente)) {
                System.out.printf("Factura #%d - Producto: %s - Cantidad: %d - Total: $%.2f%n",
                    venta.getNumeroFactura(),
                    venta.getProducto().getNombre(),
                    venta.getCantidad(),
                    venta.getTotal());
                tieneCompras = true;
            }
        }

        if (!tieneCompras) {
            System.out.println("No tienes compras registradas.");
        }
    }

    public ArrayList<Venta> obtenerVentasCliente(Cliente cliente) {
        ArrayList<Venta> ventasCliente = new ArrayList<>();
        for (Venta venta : ventas) {
            if (venta.getClienteAssociado().equals(cliente)) {
                ventasCliente.add(venta);
            }
        }
        return ventasCliente;
    }

    public ArrayList<Venta> obtenerTodasLasVentas() {
        return new ArrayList<>(ventas);
    }

    public ArrayList<Producto> obtenerProductosEnStock() {
        ArrayList<Producto> productosDisponibles = new ArrayList<>();
        for (Producto producto : Stock) {
            if (producto.getStockDisponible() > 0) {
                productosDisponibles.add(producto);
            }
        }
        return productosDisponibles;
    }

    public Cliente buscarClientePorCredenciales(String nombre, String clave) {
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equals(nombre) && cliente.getClave().equals(clave)) {
                return cliente;
            }
        }
        return null;
    }
}
