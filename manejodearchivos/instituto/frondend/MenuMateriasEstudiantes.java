package instituto.frondend;

import instituto.backend.Controllers.MateriasEstudiantesDAO;
import instituto.backend.Controllers.DAOEstudiantes;
import instituto.backend.Controllers.MateriasDAO;
import instituto.backend.Models.MateriasEstudiantes;
import instituto.backend.Models.Estudiante;
import instituto.backend.Models.Materia;
import java.util.List;
import java.util.Scanner;

public class MenuMateriasEstudiantes implements MenuConector {

    private final MateriasEstudiantesDAO dao;
    private final DAOEstudiantes daoEstudiantes;
    private final MateriasDAO daoMaterias;
    private int op;
    private final Scanner sc = new Scanner(System.in);

    public MenuMateriasEstudiantes() {
        dao = new MateriasEstudiantesDAO();
        daoEstudiantes = new DAOEstudiantes();
        daoMaterias = new MateriasDAO();
    }

    @Override
    public void IniciarMenu() {
        do {
            System.out.println("\n******* MENÚ RELACIONES MATERIAS-ESTUDIANTES *******");
            System.out.println(" 1. Asignar estudiante a materia");
            System.out.println(" 2. Ver estudiantes de una materia");
            System.out.println(" 3. Ver materias de un estudiante");
            System.out.println(" 4. Eliminar estudiante de materia");
            System.out.println(" 5. Listar todas las relaciones");
            System.out.println(" 6. Eliminar todas las materias de un estudiante");
            System.out.println(" 7. Eliminar todos los estudiantes de una materia");
            System.out.println(" 0. SALIR");
            System.out.print(" **** OPCIÓN: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> asignarEstudianteAMateria();
                case 2 -> verEstudiantesDeMateria();
                case 3 -> verMateriasDeEstudiante();
                case 4 -> eliminarEstudianteDeMateria();
                case 5 -> listarTodasRelaciones();
                case 6 -> eliminarTodasMateriasDeEstudiante();
                case 7 -> eliminarTodosEstudiantesDeMateria();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción inválida, intente de nuevo.");
            }
        } while (op != 0);
    }

    private void asignarEstudianteAMateria() {
        System.out.print("Ingrese DNI del estudiante: ");
        int dniEstudiante = sc.nextInt();
        sc.nextLine();

        if (!daoEstudiantes.existe(dniEstudiante)) {
            System.out.println("ERROR: El estudiante no existe.");
            return;
        }

        System.out.print("Ingrese código de la materia: ");
        int codMateria = sc.nextInt();
        sc.nextLine();

        if (!daoMaterias.existe(codMateria)) {
            System.out.println("ERROR: La materia no existe.");
            return;
        }

        dao.crearRelacion(new MateriasEstudiantes(codMateria, dniEstudiante));
    }

    private void verEstudiantesDeMateria() {
        System.out.print("Ingrese código de la materia: ");
        int codMateria = sc.nextInt();
        sc.nextLine();

        if (!daoMaterias.existe(codMateria)) {
            System.out.println("ERROR: La materia no existe.");
            return;
        }

        // Obtener el nombre de la materia
        String nombreMateria = "";
        for (Materia m : daoMaterias.leerMaterias()) {
            if (m.getCodigo() == codMateria) {
                nombreMateria = m.getNombre();
                break;
            }
        }

        List<Integer> estudiantes = dao.obtenerEstudiantesPorMateria(codMateria);
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes asignados a esta materia.");
        } else {
            System.out.println("\n=== ESTUDIANTES DE LA MATERIA ===");
            System.out.println("Materia: " + codMateria + " - " + nombreMateria);
            System.out.println("Cantidad de estudiantes: " + estudiantes.size());
            System.out.println("\nDNI:\t\tNombre:\t\tApellido:\t\tFecha Nac:");
            System.out.println("================================================================");

            List<Estudiante> listaEstudiantes = daoEstudiantes.leerEstudiantes();
            for (Integer dni : estudiantes) {
                for (Estudiante e : listaEstudiantes) {
                    if (e.getDni() == dni) {
                        System.out.println(e.getDni() + "\t\t" + e.getNombre() + "\t\t" +
                                         e.getApellido() + "\t\t" + e.getFechaNac());
                        break;
                    }
                }
            }
        }
    }

    private void verMateriasDeEstudiante() {
        System.out.print("Ingrese DNI del estudiante: ");
        int dniEstudiante = sc.nextInt();
        sc.nextLine();

        if (!daoEstudiantes.existe(dniEstudiante)) {
            System.out.println("ERROR: El estudiante no existe.");
            return;
        }

        // Obtener los datos completos del estudiante
        String nombreEstudiante = "";
        String apellidoEstudiante = "";
        for (Estudiante e : daoEstudiantes.leerEstudiantes()) {
            if (e.getDni() == dniEstudiante) {
                nombreEstudiante = e.getNombre();
                apellidoEstudiante = e.getApellido();
                break;
            }
        }

        List<Integer> materias = dao.obtenerMateriasPorEstudiante(dniEstudiante);
        if (materias.isEmpty()) {
            System.out.println("El estudiante no está asignado a ninguna materia.");
        } else {
            System.out.println("\n=== MATERIAS DEL ESTUDIANTE ===");
            System.out.println("Estudiante: " + dniEstudiante + " - " + nombreEstudiante + " " + apellidoEstudiante);
            System.out.println("Cantidad de materias: " + materias.size());
            System.out.println("\nCódigo:\t\tNombre de la Materia:");
            System.out.println("================================================");

            List<Materia> listaMaterias = daoMaterias.leerMaterias();
            for (Integer codigo : materias) {
                for (Materia m : listaMaterias) {
                    if (m.getCodigo() == codigo) {
                        System.out.println(m.getCodigo() + "\t\t" + m.getNombre());
                        break;
                    }
                }
            }
        }
    }

    private void eliminarEstudianteDeMateria() {
        System.out.print("Ingrese DNI del estudiante: ");
        int dniEstudiante = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese código de la materia: ");
        int codMateria = sc.nextInt();
        sc.nextLine();

        dao.eliminarRelacion(codMateria, dniEstudiante);
    }

    private void listarTodasRelaciones() {
        List<MateriasEstudiantes> relaciones = dao.leerRelaciones();
        if (relaciones.isEmpty()) {
            System.out.println("No hay relaciones registradas.");
        } else {
            System.out.println("\nTodas las relaciones Materia-Estudiante:");
            System.out.println("Código Materia\tDNI Estudiante");
            for (MateriasEstudiantes me : relaciones) {
                System.out.println(me.getCodMateria() + "\t\t" + me.getCodEstudiante());
            }
        }
    }

    private void eliminarTodasMateriasDeEstudiante() {
        System.out.print("Ingrese DNI del estudiante: ");
        int dniEstudiante = sc.nextInt();
        sc.nextLine();

        if (!daoEstudiantes.existe(dniEstudiante)) {
            System.out.println("ERROR: El estudiante no existe.");
            return;
        }

        dao.eliminarTodasRelacionesEstudiante(dniEstudiante);
    }

    private void eliminarTodosEstudiantesDeMateria() {
        System.out.print("Ingrese código de la materia: ");
        int codMateria = sc.nextInt();
        sc.nextLine();

        if (!daoMaterias.existe(codMateria)) {
            System.out.println("ERROR: La materia no existe.");
            return;
        }

        dao.eliminarTodasRelacionesMateria(codMateria);
    }
}
