package ejercicio2tiendaprodtec.personas;

import ejercicio2tiendaprodtec.productos.Producto;

public class Proveedor extends Persona {
    private String paginaWeb;
    private Producto productoOfrecido;

    public Proveedor(String nombre, String cuit, String telefono, Direccion direccion, String clave, String paginaWeb) {
        super(nombre, cuit, telefono, direccion, clave);
        this.paginaWeb = paginaWeb;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public Producto getProductoOfrecido() {
        return productoOfrecido;
    }

    public void asignarProducto(Producto producto) {
        // Si ya tenía un producto asignado, lo removemos de la lista de proveedores
        if (this.productoOfrecido != null) {
            this.productoOfrecido.removerProveedor(this);
        }

        this.productoOfrecido = producto;
        // Nos agregamos como proveedor del producto
        if (producto != null) {
            producto.agregarProveedor(this);
        }
    }

    @Override
    public String obtenerInformacionContacto() {
        String infoProducto = productoOfrecido != null ?
            " - Producto: " + productoOfrecido.getNombre() : " - Sin producto asignado";
        return String.format("Proveedor: %s - CUIT: %s - Tel: %s - Web: %s%s",
            super.getNombre(), super.getCuit(), super.getTelefono(), this.paginaWeb, infoProducto);
    }
}
