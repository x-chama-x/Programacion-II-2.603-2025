package ejercicio2tiendaprodtec.productos;

import ejercicio2tiendaprodtec.categorias.Categoria;
import ejercicio2tiendaprodtec.personas.Proveedor;
import java.util.ArrayList;
import java.util.List;

public abstract class Producto {
    private Integer ID;
    private String nombre;
    private double precioActual;
    private Integer stockDisponible;
    private Categoria categoria;
    private List<Proveedor> proveedores;

    public Producto(Integer ID, String nombre, double precioActual, Integer stockDisponible, Categoria categoria) {
        this.ID = ID;
        this.nombre = nombre;
        this.precioActual = precioActual;
        this.stockDisponible = stockDisponible;
        this.categoria = categoria;
        this.proveedores = new ArrayList<>();
    }

    public Integer getID() {
        return ID;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioActual() {
        return precioActual;
    }

    public Integer getStockDisponible() {
        return stockDisponible;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }

    public void agregarProveedor(Proveedor proveedor) {
        if (!proveedores.contains(proveedor)) {
            proveedores.add(proveedor);
        }
    }

    public void removerProveedor(Proveedor proveedor) {
        proveedores.remove(proveedor);
    }

    public void actualizarStock(int cantidad) {
        this.stockDisponible += cantidad;
    }

    public void reducirStock(int cantidad) {
        if (cantidad > 0 && cantidad <= stockDisponible) {
            stockDisponible -= cantidad;
        }
    }

    public void setStockDisponible(Integer stockDisponible) {
        this.stockDisponible = stockDisponible;
    }
}
