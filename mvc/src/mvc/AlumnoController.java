package mvc;

public class AlumnoController {

    private final Alumno modelo;
    private final Vista vista;

    public AlumnoController(Alumno modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void setNota(int nota) {
        modelo.setNota(nota);
    }

    public void actualizarVista() {
        vista.mostrarAlumno(modelo.getNombre(), modelo.getNota());
    }

}
