package mvc;

public class AlumnoVistaCualquiera implements Vista {
    
    @Override
    public void mostrarAlumno(String nombre, int nota){
        System.out.println("Nombre --> " + nombre);
        System.out.println("Nota --> " + nota);
    }
}
