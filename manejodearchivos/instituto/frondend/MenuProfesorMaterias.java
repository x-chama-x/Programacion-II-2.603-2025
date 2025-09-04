package instituto.frondend;

import instituto.backend.Controllers.DAOProfesores;
import instituto.backend.Controllers.MateriasDAO;
import instituto.backend.Controllers.ProfesorMateriasDAO;
import instituto.backend.Models.ProfesorMaterias;
import java.util.Scanner;

public class MenuProfesorMaterias implements MenuConector {

    private final ProfesorMateriasDAO dao;
    private final DAOProfesores daoProfesores;
    private final MateriasDAO daoMaterias;
    private int op;
    private final Scanner sc = new Scanner(System.in);

    public MenuProfesorMaterias() {
        dao = new ProfesorMateriasDAO();
        daoProfesores = new DAOProfesores();
        daoMaterias = new MateriasDAO();
    }

    public void IniciarMenu() {
        do {
            System.out.println("\n******* MENÚ PROFESOR-MATERIAS *******");
            System.out.println(" 1. Listar todas las relaciones profesor-materia.");
            System.out.println(" 2. Asignar materia a profesor.");
            System.out.println(" 3. Ver materias de un profesor específico.");
            System.out.println(" 4. Ver profesores de una materia específica.");
            System.out.println(" 5. Eliminar asignación profesor-materia.");
            System.out.println(" 6. Eliminar todas las materias de un profesor.");
            System.out.println(" 7. Eliminar todos los profesores de una materia.");
            System.out.println(" 0. SALIR.");
            System.out.print(" **** OPCIÓN: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> listarTodasRelaciones();
                case 2 -> asignarMateriaProfesor();
                case 3 -> verMateriasDeProfesor();
                case 4 -> verProfesoresDeMateria();
                case 5 -> eliminarAsignacion();
                case 6 -> eliminarTodasMateriasProfesor();
                case 7 -> eliminarTodosProfesoresMateria();
            }
        } while (op != 0);
    }

    public void listarTodasRelaciones() {
        System.out.println("\nTODAS LAS RELACIONES PROFESOR-MATERIA:");
        System.out.println("DNI Profesor\tNombre Profesor\t\tCód. Materia\tNombre Materia");
        System.out.println("-------------------------------------------------------------------------");

        var relaciones = dao.leerRelaciones();
        if (relaciones.isEmpty()) {
            System.out.println("No hay relaciones profesor-materia registradas.");
            return;
        }

        for (var relacion : relaciones) {
            // Buscar datos del profesor
            String nombreProfesor = "No encontrado";
            for (var profesor : daoProfesores.leerProfesores()) {
                if (profesor.getDni() == relacion.getDniProfesor()) {
                    nombreProfesor = profesor.getNombre() + " " + profesor.getApellido();
                    break;
                }
            }

            // Buscar datos de la materia
            String nombreMateria = "No encontrada";
            for (var materia : daoMaterias.leerMaterias()) {
                if (materia.getCodigo() == relacion.getCodMateria()) {
                    nombreMateria = materia.getNombre();
                    break;
                }
            }

            System.out.println(relacion.getDniProfesor() + "\t\t" + nombreProfesor + "\t\t" +
                             relacion.getCodMateria() + "\t\t" + nombreMateria);
        }
    }

    public void asignarMateriaProfesor() {
        // Mostrar profesores disponibles
        System.out.println("\nPROFESORES DISPONIBLES:");
        System.out.println("DNI\t\tNombre\t\tApellido");
        System.out.println("----------------------------------------");
        for (var profesor : daoProfesores.leerProfesores()) {
            System.out.println(profesor.getDni() + "\t\t" + profesor.getNombre() + "\t\t" + profesor.getApellido());
        }

        System.out.print("\nIngrese DNI del profesor: ");
        int dniProfesor = sc.nextInt();
        sc.nextLine();

        if (!daoProfesores.existe(dniProfesor)) {
            System.out.println("ERROR: El profesor no existe.");
            return;
        }

        // Mostrar materias disponibles
        System.out.println("\nMATERIAS DISPONIBLES:");
        System.out.println("Código\t\tNombre");
        System.out.println("----------------------------------------");
        for (var materia : daoMaterias.leerMaterias()) {
            System.out.println(materia.getCodigo() + "\t\t" + materia.getNombre());
        }

        System.out.print("\nIngrese código de la materia: ");
        int codMateria = sc.nextInt();
        sc.nextLine();

        if (!daoMaterias.existe(codMateria)) {
            System.out.println("ERROR: La materia no existe.");
            return;
        }

        dao.crearRelacion(new ProfesorMaterias(dniProfesor, codMateria));
    }

    public void verMateriasDeProfesor() {
        System.out.print("Ingrese DNI del profesor: ");
        int dni = sc.nextInt();
        sc.nextLine();

        if (!daoProfesores.existe(dni)) {
            System.out.println("ERROR: El profesor no existe.");
            return;
        }

        // Obtener nombre del profesor
        String nombreProfesor = "";
        for (var profesor : daoProfesores.leerProfesores()) {
            if (profesor.getDni() == dni) {
                nombreProfesor = profesor.getNombre() + " " + profesor.getApellido();
                break;
            }
        }

        var materias = dao.obtenerMateriasPorProfesor(dni);
        if (materias.isEmpty()) {
            System.out.println("El profesor " + nombreProfesor + " no tiene materias asignadas.");
        } else {
            System.out.println("\nMaterias del profesor " + nombreProfesor + ":");
            System.out.println("Código\t\tNombre");
            System.out.println("----------------------------------------");

            for (int codMateria : materias) {
                for (var materia : daoMaterias.leerMaterias()) {
                    if (materia.getCodigo() == codMateria) {
                        System.out.println(materia.getCodigo() + "\t\t" + materia.getNombre());
                        break;
                    }
                }
            }
        }
    }

    public void verProfesoresDeMateria() {
        System.out.print("Ingrese código de la materia: ");
        int codigo = sc.nextInt();
        sc.nextLine();

        if (!daoMaterias.existe(codigo)) {
            System.out.println("ERROR: La materia no existe.");
            return;
        }

        // Obtener nombre de la materia
        String nombreMateria = "";
        for (var materia : daoMaterias.leerMaterias()) {
            if (materia.getCodigo() == codigo) {
                nombreMateria = materia.getNombre();
                break;
            }
        }

        var profesores = dao.obtenerProfesoresPorMateria(codigo);
        if (profesores.isEmpty()) {
            System.out.println("La materia " + nombreMateria + " no tiene profesores asignados.");
        } else {
            System.out.println("\nProfesores de la materia " + nombreMateria + ":");
            System.out.println("DNI\t\tNombre\t\tApellido");
            System.out.println("----------------------------------------");

            for (int dniProfesor : profesores) {
                for (var profesor : daoProfesores.leerProfesores()) {
                    if (profesor.getDni() == dniProfesor) {
                        System.out.println(profesor.getDni() + "\t\t" + profesor.getNombre() + "\t\t" + profesor.getApellido());
                        break;
                    }
                }
            }
        }
    }

    public void eliminarAsignacion() {
        System.out.print("Ingrese DNI del profesor: ");
        int dniProfesor = sc.nextInt();
        sc.nextLine();

        System.out.print("Ingrese código de la materia: ");
        int codMateria = sc.nextInt();
        sc.nextLine();

        if (dao.existe(dniProfesor, codMateria)) {
            dao.eliminarRelacion(dniProfesor, codMateria);
        } else {
            System.out.println("ERROR: La relación no existe.");
        }
    }

    public void eliminarTodasMateriasProfesor() {
        System.out.print("Ingrese DNI del profesor: ");
        int dni = sc.nextInt();
        sc.nextLine();

        if (!daoProfesores.existe(dni)) {
            System.out.println("ERROR: El profesor no existe.");
            return;
        }

        dao.eliminarTodasRelacionesProfesor(dni);
    }

    public void eliminarTodosProfesoresMateria() {
        System.out.print("Ingrese código de la materia: ");
        int codigo = sc.nextInt();
        sc.nextLine();

        if (!daoMaterias.existe(codigo)) {
            System.out.println("ERROR: La materia no existe.");
            return;
        }

        dao.eliminarTodasRelacionesMateria(codigo);
    }
}
