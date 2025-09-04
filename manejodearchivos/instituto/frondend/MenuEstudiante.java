package instituto.frondend;

import instituto.backend.Controllers.DAOEstudiantes;
import instituto.backend.Controllers.MateriasEstudiantesDAO;
import instituto.backend.Models.Estudiante;
import java.util.Scanner;

public class MenuEstudiante implements MenuConector {
    
    private final DAOEstudiantes dao;
    private final MateriasEstudiantesDAO daoRelaciones;
    private int op;
    private final Scanner sc = new Scanner(System.in);

    public MenuEstudiante() {
        dao = new DAOEstudiantes();
        daoRelaciones = new MateriasEstudiantesDAO();
    }
    
    public void IniciarMenu() {
        do {
            System.out.println("\n******* MENÚ ESTUDIANTES *******");
            System.out.println(" 1. Lista estudiantes.");
            System.out.println(" 2. Agregar estudiante.");
            System.out.println(" 3. Actualizar estudiante.");
            System.out.println(" 4. Buscar estudiante.");
            System.out.println(" 5. Eliminar estudiante.");
            System.out.println(" 6. Ver materias del estudiante.");
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
                case 6 -> verMateriasEstudiante();
            }
        } while (op != 0);
    }

    public void listar(){
        System.out.println("LISTA DE ESTUDIANTES...");
        System.out.println("DNI:\t\tNombre:\t\tApellido:\t\tFecha Nac:");
        for(Estudiante e: dao.leerEstudiantes()){
            System.out.println(e.getDni()+"\t\t"+ e.getNombre()+"\t\t"+e.getApellido()+"\t\t"+e.getFechaNac());
        }
    }
    public void agregar(){
        System.out.print("Ingrese dni: ");
        int dni = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Ingrese fecha de nacimiento (dd/mm/yyyy): ");
        String fechaNac = sc.nextLine();
        dao.crearEstudiante(new Estudiante(dni, nombre, apellido, fechaNac));
    }
    public void actualizar(){
        System.out.print("Ingrese dni: ");
        int dni = sc.nextInt();
        sc.nextLine();
        System.out.print("Ingrese nuevo nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese nuevo apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Ingrese nueva fecha de nacimiento (dd/mm/yyyy): ");
        String fechaNac = sc.nextLine();
        dao.actualizarEstudiante(dni, new Estudiante(dni, nombre, apellido, fechaNac));
    }
    public void buscar(){
        System.out.print("Ingrese dni: ");
        int dni = sc.nextInt();
        sc.nextLine();
        System.out.println(dao.buscarPorDni(dni));
    }
    public void eliminar(){
        System.out.print("Ingrese dni: ");
        int dni = sc.nextInt();
        sc.nextLine();

        if (dao.existe(dni)) {
            // Eliminar todas las relaciones del estudiante primero
            daoRelaciones.eliminarTodasRelacionesEstudiante(dni);
            // Luego eliminar el estudiante
            dao.eliminarEstudiante(dni);
            System.out.println("Estudiante y todas sus relaciones eliminadas.");
        } else {
            System.out.println("ERROR: El estudiante no existe.");
        }
    }

    private void verMateriasEstudiante() {
        System.out.print("Ingrese DNI del estudiante: ");
        int dni = sc.nextInt();
        sc.nextLine();

        if (!dao.existe(dni)) {
            System.out.println("ERROR: El estudiante no existe.");
            return;
        }

        var materias = daoRelaciones.obtenerMateriasPorEstudiante(dni);
        if (materias.isEmpty()) {
            System.out.println("El estudiante no está asignado a ninguna materia.");
        } else {
            System.out.println("\nMaterias del estudiante DNI " + dni + ":");
            System.out.println("Cantidad de materias: " + materias.size());
            for (Integer codMateria : materias) {
                System.out.println("- Código de materia: " + codMateria);
            }
        }
    }
}
