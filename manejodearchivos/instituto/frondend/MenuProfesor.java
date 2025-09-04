package instituto.frondend;

import instituto.backend.Controllers.DAOProfesores;
import instituto.backend.Controllers.ProfesorMateriasDAO;
import instituto.backend.Models.Profesor;
import java.util.Scanner;

public class MenuProfesor implements MenuConector {

    private final DAOProfesores dao;
    private final ProfesorMateriasDAO daoRelaciones;
    private int op;
    private final Scanner sc = new Scanner(System.in);

    public MenuProfesor() {
        dao = new DAOProfesores();
        daoRelaciones = new ProfesorMateriasDAO();
    }

    public void IniciarMenu() {
        do {
            System.out.println("\n******* MENÚ PROFESORES *******");
            System.out.println(" 1. Lista profesores.");
            System.out.println(" 2. Agregar profesor.");
            System.out.println(" 3. Actualizar profesor.");
            System.out.println(" 4. Buscar profesor.");
            System.out.println(" 5. Eliminar profesor.");
            System.out.println(" 6. Ver materias del profesor.");
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
                case 6 -> verMateriasProfesor();
            }
        } while (op != 0);
    }

    public void listar(){
        System.out.println("LISTA DE PROFESORES...");
        System.out.println("DNI:\t\tNombre:\t\tApellido:\t\tFecha Nac:");
        for(Profesor p: dao.leerProfesores()){
            System.out.println(p.getDni()+"\t\t"+ p.getNombre()+"\t\t"+p.getApellido()+"\t\t"+p.getFechaNac());
        }
    }

    public void agregar(){
        System.out.print("Ingrese DNI: ");
        int dni = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Ingrese fecha de nacimiento (dd/mm/yyyy): ");
        String fechaNac = sc.nextLine();
        dao.crearProfesor(new Profesor(dni, nombre, apellido, fechaNac));
    }

    public void actualizar(){
        System.out.print("Ingrese DNI: ");
        int dni = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese nuevo nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese nuevo apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Ingrese nueva fecha de nacimiento (dd/mm/yyyy): ");
        String fechaNac = sc.nextLine();
        dao.actualizarProfesor(dni, new Profesor(dni, nombre, apellido, fechaNac));
    }

    public void buscar(){
        System.out.print("Ingrese DNI: ");
        int dni = sc.nextInt();
        sc.nextLine();
        System.out.println(dao.buscarPorDni(dni));
    }

    public void eliminar(){
        System.out.print("Ingrese DNI: ");
        int dni = sc.nextInt();
        sc.nextLine();

        if (dao.existe(dni)) {
            // Eliminar todas las relaciones del profesor primero
            daoRelaciones.eliminarTodasRelacionesProfesor(dni);
            // Luego eliminar el profesor
            dao.eliminarProfesor(dni);
            System.out.println("Profesor y todas sus relaciones eliminadas.");
        } else {
            System.out.println("ERROR: El profesor no existe.");
        }
    }

    private void verMateriasProfesor() {
        System.out.print("Ingrese DNI del profesor: ");
        int dni = sc.nextInt();
        sc.nextLine();

        if (!dao.existe(dni)) {
            System.out.println("ERROR: El profesor no existe.");
            return;
        }

        // Obtener los datos completos del profesor
        String nombreProfesor = "";
        String apellidoProfesor = "";
        for (var p : dao.leerProfesores()) {
            if (p.getDni() == dni) {
                nombreProfesor = p.getNombre();
                apellidoProfesor = p.getApellido();
                break;
            }
        }

        var materias = daoRelaciones.obtenerMateriasPorProfesor(dni);
        if (materias.isEmpty()) {
            System.out.println("El profesor no tiene materias asignadas.");
        } else {
            System.out.println("\nMaterias del profesor " + nombreProfesor + " " + apellidoProfesor + ":");
            System.out.println("Código\t\tNombre");
            System.out.println("----------------------------------------");

            // Importar el DAO de materias para obtener los detalles
            var daoMaterias = new instituto.backend.Controllers.MateriasDAO();
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
}
