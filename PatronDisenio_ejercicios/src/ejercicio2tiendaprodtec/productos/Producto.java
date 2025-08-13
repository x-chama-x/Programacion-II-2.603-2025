package ejercicio2tiendaprodtec.productos;

import ejercicio2tiendaprodtec.categorias.Categoria;

public abstract class Producto {
    private Integer ID;
    private String nombre;
    private double precioActual;
    private Integer stockDisponible;
    private Categoria categoria;

    public Producto(Integer ID, String nombre, double precioActual, Integer stockDisponible, Categoria categoria) {
        this.ID = ID;
        this.nombre = nombre;
        this.precioActual = precioActual;
        this.stockDisponible = stockDisponible;
        this.categoria = categoria;
    }
}
