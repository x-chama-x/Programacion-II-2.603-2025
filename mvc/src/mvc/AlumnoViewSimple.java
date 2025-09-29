package mvc;

public class AlumnoViewSimple implements Vista {

    @Override
    public void mostrarAlumno(String nombre, int nota) {
        System.out.println("=== Vista Simple ===");
        System.out.println("Alumno: " + nombre);
        System.out.println("Nota: " + nota);
        System.out.println();
    }
}
