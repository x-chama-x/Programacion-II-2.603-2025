package instituto.Conections;

public class BD {
    
    public static void conectar(){
        BDEstudiantes bdEstudiantes = new BDEstudiantes();
        bdEstudiantes.inicializarBD();

        BDMaterias bdMaterias = new BDMaterias();
        bdMaterias.inicializarBD();

        BDMateriasEstudiantes bdMateriasEstudiantes = new BDMateriasEstudiantes();
        bdMateriasEstudiantes.inicializarBD();

        BDProfesores bdProfesores = new BDProfesores();
        bdProfesores.inicializarBD();

        BDProfesorMaterias bdProfesorMaterias = new BDProfesorMaterias();
        bdProfesorMaterias.inicializarBD();
    }
}
