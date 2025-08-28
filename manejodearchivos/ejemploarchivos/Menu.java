package ejemploarchivos;

import java.util.Scanner;

public class Menu {
    private EstudianteDAO dao;
    private int op;
    Scanner sc = new Scanner(System.in);

    public Menu() {
        dao = new EstudianteDAO();
    }

    public void iniciarMenu() {
        do {
            System.out.println("\n--- Menú de Estudiantes ---");
            System.out.println("1. Agregar estudiante");
            System.out.println("2. Listar estudiantes");
            System.out.println("3. Buscar estudiante por DNI");
            System.out.println("4. Actualizar estudiante");
            System.out.println("5. Eliminar estudiante");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            op = sc.nextInt();
            sc.nextLine(); // Limpiar el buffer

            switch (op) {
                case 1 -> agregar();
                case 2 -> listar();
                case 3 -> buscarPorDni();
                case 4 -> actualizar();
                case 5 -> eliminar();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción no válida.");
            }
        } while (op != 0);
    }

    private void buscarPorDni() {
        System.out.print("Ingrese el DNI del estudiante a buscar: ");
        String dni = sc.nextLine();
        Estudiante estudiante = dao.buscarEstudiantePorDni(dni);
        if (estudiante != null) {
            System.out.println("Estudiante encontrado:");
            System.out.println("DNI: " + estudiante.getDni());
            System.out.println("Nombre: " + estudiante.getNombre());
            System.out.println("Apellido: " + estudiante.getApellido());
            System.out.println("Fecha de Nacimiento: " + estudiante.getFechaNac());
        } else {
            System.out.println("No se encontró ningún estudiante con el DNI: " + dni);
        }
    }

    private void eliminar() {
        System.out.print("Ingrese el nombre del estudiante a eliminar: ");
        String nombreBuscado = sc.nextLine();
        dao.eliminarEstudiante(nombreBuscado);
        System.out.println("Estudiante eliminado (si existía).");
    }

    private void actualizar() {
        System.out.print("Ingrese el nombre del estudiante a actualizar: ");
        String nombreBuscado = sc.nextLine();

        // Verificar si el estudiante existe
        boolean estudianteExiste = false;
        for (Estudiante e : dao.leerEstudiantes()) {
            if (e.getNombre().equalsIgnoreCase(nombreBuscado)) {
                estudianteExiste = true;
                break;
            }
        }

        if (!estudianteExiste) {
            System.out.println("No se encontró ningún estudiante con el nombre: " + nombreBuscado);
            return;
        }

        System.out.print("Ingrese nuevo DNI: ");
        String dni = sc.nextLine();
        System.out.print("Ingrese nuevo nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese nuevo apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Ingrese nueva fecha de nacimiento (DD/MM/AAAA): ");
        String fechaNac = sc.nextLine();

        boolean exito = dao.actualizarEstudiante(nombreBuscado, new Estudiante(dni, nombre, apellido, fechaNac));
        if (exito) {
            System.out.println("Estudiante actualizado exitosamente.");
        } else {
            System.out.println("ERROR: No se pudo actualizar. Posibles causas:");
            System.out.println("- El DNI ya pertenece a otro estudiante");
            System.out.println("- Error en el archivo");
        }
    }

    private void listar() {
        System.out.println("\n=== Lista de estudiantes ===");
        var estudiantes = dao.leerEstudiantes();
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
        } else {
            System.out.println("DNI\t\tNombre\t\tApellido\t\tFecha Nac.");
            System.out.println("--------------------------------------------------------");
            for (Estudiante e : estudiantes) {
                System.out.printf("%-10s\t%-10s\t%-10s\t%-10s%n",
                    e.getDni(), e.getNombre(), e.getApellido(), e.getFechaNac());
            }
        }
    }

    private void agregar() {
        System.out.print("Ingrese DNI: ");
        String dni = sc.nextLine();

        // Verificar si el DNI ya existe
        if (dao.existeDni(dni)) {
            System.out.println("ERROR: Ya existe un estudiante con el DNI " + dni);
            System.out.println("No se puede agregar el estudiante.");
            return;
        }

        System.out.print("Ingrese nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Ingrese apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Ingrese fecha de nacimiento (DD/MM/AAAA): ");
        String fechaNac = sc.nextLine();

        boolean exito = dao.crearEstudiante(new Estudiante(dni, nombre, apellido, fechaNac));
        if (exito) {
            System.out.println("Estudiante agregado exitosamente.");
        } else {
            System.out.println("Error al agregar el estudiante.");
        }
    }
}
