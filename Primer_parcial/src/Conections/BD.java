package Conections;

public class BD {

    public static void conectar(){
        BDSoldados bdSoldados = new BDSoldados();
        bdSoldados.inicializarBD();

        BDOficiales bdOficiales = new BDOficiales();
        bdOficiales.inicializarBD();

        BDCuarteles bdCuarteles = new BDCuarteles();
        bdCuarteles.inicializarBD();

        BDAsignaciones bdAsignaciones = new BDAsignaciones();
        bdAsignaciones.inicializarBD();

        BDReservas bdReservas = new BDReservas();
        bdReservas.inicializarBD();

        System.out.println("Todas las bases de datos han sido inicializadas correctamente.");
    }
}
