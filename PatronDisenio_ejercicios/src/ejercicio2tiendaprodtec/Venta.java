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
}
