package ejercicio2tiendaprodtec;

public class Direccion {
    private String calle;
    private Integer numero;
    private String comuna;
    private String ciudad;
    private String codigoPostal;

    public Direccion(String calle, Integer numero, String comuna, String ciudad, String codigoPostal) {
        this.calle = calle;
        this.numero = numero;
        this.comuna = comuna;
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
    }
}
