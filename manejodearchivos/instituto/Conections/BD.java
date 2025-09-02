package instituto.Conections;

public class BD {
    
    public static void conectar(){
        BDEstudiantes bdEstudiantes = new BDEstudiantes();
        bdEstudiantes.inicializarBD();
    }
}
