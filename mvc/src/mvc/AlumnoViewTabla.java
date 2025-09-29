package mvc;

public class AlumnoViewTabla implements Vista {
    
    @Override
    public void mostrarAlumno(String nombre, int nota) {
        System.out.println("=== Vista en Tabla ===");
        System.out.println("+----------------+------+\n| Nombre ||\n + -- -- -- -- -- -- -- --+-- -- --+\n | "
                + String.format("%-14s", nombre) + " | "
                + String.format("%-4d", nota) + " |\n+----------------+------+\n"
        );
    }
}
