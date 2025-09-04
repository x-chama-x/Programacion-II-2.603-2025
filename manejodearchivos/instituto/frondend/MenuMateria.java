package instituto.frondend;

import instituto.backend.Controllers.MateriasDAO;
import instituto.backend.Controllers.MateriasEstudiantesDAO;
import instituto.backend.Models.Materia;
import java.util.Scanner;

public class MenuMateria implements MenuConector {

    private final MateriasDAO dao;
    private final MateriasEstudiantesDAO daoRelaciones;
    private int op;
    private final Scanner sc = new Scanner(System.in);

    public MenuMateria() {
        dao = new MateriasDAO();
        daoRelaciones = new MateriasEstudiantesDAO();
    }

    public void IniciarMenu() {
        do {
            System.out.println("\n******* MENÚ MATERIAS *******");
            System.out.println(" 1. Lista materias.");
            System.out.println(" 2. Agregar materia.");
            System.out.println(" 3. Actualizar materia.");
            System.out.println(" 4. Buscar materia.");
            System.out.println(" 5. Eliminar materia.");
            System.out.println(" 6. Ver estudiantes de la materia.");
            System.out.println(" 0. SALIR.");
            System.out.print(" **** OPCIÓN: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> listar();
                case 2 -> agregar();
                case 3 -> actualizar();
                case 4 -> buscar();
                case 5 -> eliminar();
                case 6 -> verEstudiantesMateria();
            }
        } while (op != 0);
    }

    public void listar(){
        System.out.println("LISTA DE MATERIAS...");
        System.out.println("Código:\t\tNombre:");
        for(Materia m: dao.leerMaterias()){
            System.out.println(m.getCodigo()+"\t\t"+ m.getNombre());
        }
    }

    public void agregar(){
        System.out.print("Ingrese código: ");
        int codigo = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese nombre: ");
        String nombre = sc.nextLine();
        dao.crearMateria(new Materia(codigo, nombre));
    }

    public void actualizar(){
        System.out.print("Ingrese código: ");
        int codigo = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese nuevo nombre: ");
        String nombre = sc.nextLine();
        dao.actualizarMateria(codigo, new Materia(codigo, nombre));
    }

    public void buscar(){
        System.out.print("Ingrese código: ");
        int codigo = sc.nextInt();
        sc.nextLine();
        System.out.println(dao.buscarPorCodigo(codigo));
    }

    public void eliminar(){
        System.out.print("Ingrese código: ");
        int codigo = sc.nextInt();
        sc.nextLine();

        if (dao.existe(codigo)) {
            // Eliminar todas las relaciones de la materia primero
            daoRelaciones.eliminarTodasRelacionesMateria(codigo);
            // Luego eliminar la materia
            dao.eliminarMateria(codigo);
            System.out.println("Materia y todas sus relaciones eliminadas.");
        } else {
            System.out.println("ERROR: La materia no existe.");
        }
    }

    private void verEstudiantesMateria() {
        System.out.print("Ingrese código de la materia: ");
        int codigo = sc.nextInt();
        sc.nextLine();

        if (!dao.existe(codigo)) {
            System.out.println("ERROR: La materia no existe.");
            return;
        }

        // Obtener el nombre de la materia
        String nombreMateria = "";
        for (Materia m : dao.leerMaterias()) {
            if (m.getCodigo() == codigo) {
                nombreMateria = m.getNombre();
                break;
            }
        }

        var estudiantes = daoRelaciones.obtenerEstudiantesPorMateria(codigo);
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes asignados a esta materia.");
        } else {
            System.out.println("\n=== ESTUDIANTES DE LA MATERIA ===");
            System.out.println("Materia: " + codigo + " - " + nombreMateria);
            System.out.println("Cantidad de estudiantes: " + estudiantes.size());
            System.out.println("\nDNI:\t\tNombre:\t\tApellido:");
            System.out.println("================================================");

            // Obtener la lista completa de estudiantes
            var todosEstudiantes = new instituto.backend.Controllers.DAOEstudiantes().leerEstudiantes();

            for (Integer dni : estudiantes) {
                for (var e : todosEstudiantes) {
                    if (e.getDni() == dni) {
                        System.out.println(e.getDni() + "\t\t" + e.getNombre() + "\t\t" + e.getApellido());
                        break;
                    }
                }
            }
        }
    }
}
