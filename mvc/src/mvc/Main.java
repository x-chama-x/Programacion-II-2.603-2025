package mvc;

public class Main {

    public static void main(String[] args) {
        // Crear modelo
        Alumno alumno = new Alumno("Miguel", 8);
        // Crear vistas
        AlumnoViewSimple vistaSimple = new AlumnoViewSimple();
        AlumnoViewTabla vistaTabla = new AlumnoViewTabla();
        AlumnoVistaCualquiera vistaCualqui = new AlumnoVistaCualquiera();
        // Crear controlador
        AlumnoController controlador1 = new AlumnoController(alumno, vistaSimple);
        AlumnoController controlador2 = new AlumnoController(alumno, vistaTabla);
        AlumnoController controlador3 = new AlumnoController(alumno, vistaCualqui);
        // Mostrar con ambas vistas
        controlador1.actualizarVista();
        controlador2.actualizarVista();
        controlador3.actualizarVista();
        
        // Cambiar nota mediante el controlador
        controlador1.setNota(10);
        // Volver a mostrar actualizaciones
        controlador1.actualizarVista();
        controlador2.actualizarVista();
        controlador3.actualizarVista();
    }

}
