package ejercicio2tiendaprodtec.categorias;

import ejercicio2tiendaprodtec.productos.Producto;

import java.util.ArrayList;

public abstract class Categoria {
    private Integer ID;
    private String nombre;
    private String descripcion;
    private ArrayList<Producto> productos;

    public Categoria(Integer ID, String nombre, String descripcion) {
        this.ID = ID;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.productos = new ArrayList<>();
    }
}
