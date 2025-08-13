package ejercicio2tiendaprodtec;

import ejercicio2tiendaprodtec.productos.Producto;

public class ProductoVendido {
    private Producto producto;
    private double precioAlMomentoVenta;
    private Integer cantidadVendida;
    private double montoTotalParcial;

    public ProductoVendido(Producto producto, double precioAlMomentoVenta, Integer cantidadVendida) {
        this.producto = producto;
        this.precioAlMomentoVenta = precioAlMomentoVenta;
        this.cantidadVendida = cantidadVendida;
        this.montoTotalParcial = calcularMontoTotalParcial();
    }

    private double calcularMontoTotalParcial() {
        return precioAlMomentoVenta * cantidadVendida;
    }
}
