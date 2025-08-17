package ejercicio2tiendaprodtec;

import ejercicio2tiendaprodtec.personas.Cliente;
import ejercicio2tiendaprodtec.productos.ProductoVendido;

import java.util.ArrayList;
import java.util.Date;

public class Venta {
    private Integer nFactura;
    private Date fecha;
    private double descuentoAplicado;
    private double montoFinal;
    private Cliente clienteAssociado;
    private ArrayList<ProductoVendido> productosVendidos;

    public Venta(Integer nFactura, Cliente cliente) {
        this.nFactura = nFactura;
        this.clienteAssociado = cliente;
        this.fecha = new Date();
        this.descuentoAplicado = 0.0;
        this.montoFinal = 0.0;
        this.productosVendidos = new ArrayList<>();
    }

    public void agregarProducto(ProductoVendido producto) {
        productosVendidos.add(producto);
        calcularMontoFinal();
    }

    private void calcularMontoFinal() {
        double subtotal = 0.0;
        for(ProductoVendido producto : productosVendidos) {
            subtotal += producto.getMontoTotalParcial();
        }
        this.montoFinal = subtotal * (1 - this.descuentoAplicado);
    }

    public void aplicarDescuento(double porcentajeDescuento) {
        if(porcentajeDescuento >= 0 && porcentajeDescuento <= 1) {
            this.descuentoAplicado = porcentajeDescuento;
            calcularMontoFinal();
        }
    }

    // Getters
    public Integer getNFactura() { return nFactura; }
    public Date getFecha() { return fecha; }
    public double getMontoFinal() { return montoFinal; }
    public Cliente getClienteAssociado() { return clienteAssociado; }
    public ArrayList<ProductoVendido> getProductosVendidos() { return productosVendidos; }
}
