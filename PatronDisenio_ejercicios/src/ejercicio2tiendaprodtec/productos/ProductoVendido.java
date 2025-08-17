package ejercicio2tiendaprodtec.productos;

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

    public Producto getProducto() {
        return producto;
    }

    public double getPrecioAlMomentoVenta() {
        return precioAlMomentoVenta;
    }

    public Integer getCantidadVendida() {
        return cantidadVendida;
    }

    public double getMontoTotalParcial() {
        return montoTotalParcial;
    }
}
