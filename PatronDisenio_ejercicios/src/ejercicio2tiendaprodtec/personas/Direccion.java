package ejercicio2tiendaprodtec.personas;

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

    public String getCalle() {
        return calle;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getComuna() {
        return comuna;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }
}
