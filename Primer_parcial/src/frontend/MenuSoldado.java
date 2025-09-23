package frontend;

import backend.Controllers.DAOSoldados;
import backend.Controllers.DAOOficiales;
import backend.Controllers.ValidadorDNI;
import backend.Models.Soldado;
import java.util.List;
import java.util.Scanner;

public class MenuSoldado implements MenuConector {

    private Scanner scanner;
    private DAOSoldados daoSoldados;
    private boolean menuActivo;

    public MenuSoldado() {
        this.scanner = new Scanner(System.in);
        this.daoSoldados = new DAOSoldados();
        this.menuActivo = true;
    }

    @Override
    public void IniciarMenu() {
        while (menuActivo) {
            mostrarMenuSoldado();
            int opcion = leerOpcion();
            procesarOpcion(opcion);
        }
    }

    private void mostrarMenuSoldado() {
        System.out.println("\n========================================");
        System.out.println("         GESTIÓN DE SOLDADOS");
        System.out.println("========================================");
        System.out.println("1. Ingresar nuevo soldado");
        System.out.println("2. Consultar soldado por DNI");
        System.out.println("3. Consultar soldado por ID");
        System.out.println("4. Listar todos los soldados");
        System.out.println("5. Modificar datos de soldado");
        System.out.println("6. Eliminar soldado");
        //System.out.println("7. Buscar soldados por cuartel");
        //System.out.println("8. Buscar soldados por oficial");
        System.out.println("0. Volver al menú principal");
        System.out.println("========================================");
        System.out.print("Seleccione una opción: ");
    }

    private int leerOpcion() {
        try {
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                ingresarSoldado();
                break;
            case 2:
                consultarSoldadoPorDni();
                break;
            case 3:
                consultarSoldadoPorId();
                break;
            case 4:
                listarTodosLosSoldados();
                break;
            case 5:
                modificarSoldado();
                break;
            case 6:
                eliminarSoldado();
                break;
            case 7:
                //buscarSoldadosPorCuartel(); Todo lo comentado no está bien implementado
                break;
            case 8:
                //buscarSoldadosPorOficial(); Todo lo comentado no está bien implementado
                break;
            case 0:
                volverAlMenuPrincipal();
                break;
            default:
                System.out.println("Opción inválida. Por favor, intente de nuevo.");
                pausar();
        }
    }

    private void ingresarSoldado() {
        System.out.println("\n--- INGRESAR NUEVO SOLDADO ---");

        System.out.print("Ingrese DNI: ");
        String dni = scanner.nextLine().trim();

        if (dni.isEmpty()) {
            System.out.println("Error: El DNI no puede estar vacío.");
            pausar();
            return;
        }

        if (daoSoldados.existe(dni)) {
            System.out.println("Error: Ya existe un soldado con el DNI " + dni);
            pausar();
            return;
        }

        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine().trim();

        if (nombre.isEmpty()) {
            System.out.println("Error: El nombre no puede estar vacío.");
            pausar();
            return;
        }

        System.out.print("Ingrese apellido: ");
        String apellido = scanner.nextLine().trim();

        if (apellido.isEmpty()) {
            System.out.println("Error: El apellido no puede estar vacío.");
            pausar();
            return;
        }

        System.out.print("Ingrese código de cuartel (0 si no tiene): ");
        int cuartelCodigo = leerEntero();

        System.out.print("Ingrese ID del oficial (0 si no tiene): ");
        int oficialId = leerEntero();

        Soldado nuevoSoldado = new Soldado(dni, nombre, apellido, cuartelCodigo, oficialId);
        daoSoldados.crearSoldado(nuevoSoldado);

        System.out.println("¡Soldado ingresado exitosamente!");
        pausar();
    }

    private void consultarSoldadoPorDni() {
        System.out.println("\n--- CONSULTAR SOLDADO POR DNI ---");
        System.out.print("Ingrese DNI del soldado: ");
        String dni = scanner.nextLine().trim();

        if (dni.isEmpty()) {
            System.out.println("Error: El DNI no puede estar vacío.");
            pausar();
            return;
        }

        Soldado soldado = daoSoldados.obtenerPorDni(dni);

        if (soldado != null) {
            mostrarDetallesSoldado(soldado);
        } else {
            System.out.println("No se encontró ningún soldado con el DNI: " + dni);
        }

        pausar();
    }

    private void consultarSoldadoPorId() {
        System.out.println("\n--- CONSULTAR SOLDADO POR ID ---");
        System.out.print("Ingrese ID del soldado: ");
        int id = leerEntero();

        if (id <= 0) {
            System.out.println("Error: El ID debe ser un número positivo.");
            pausar();
            return;
        }

        Soldado soldado = daoSoldados.obtenerPorId(id);

        if (soldado != null) {
            mostrarDetallesSoldado(soldado);
        } else {
            System.out.println("No se encontró ningún soldado con el ID: " + id);
        }

        pausar();
    }

    private void listarTodosLosSoldados() {
        System.out.println("\n--- LISTA DE TODOS LOS SOLDADOS ---");
        List<Soldado> soldados = daoSoldados.leerSoldados();

        if (soldados.isEmpty()) {
            System.out.println("No hay soldados registrados en el sistema.");
        } else {
            System.out.println("\n" + String.format("%-5s %-12s %-20s %-20s %-10s %-10s",
                "ID", "DNI", "NOMBRE", "APELLIDO", "CUARTEL", "OFICIAL"));
            System.out.println("--------------------------------------------------------------------------------");

            for (Soldado soldado : soldados) {
                System.out.printf("%-5d %-12s %-20s %-20s %-10d %-10d%n",
                    soldado.getId(),
                    soldado.getDni(),
                    soldado.getNombre(),
                    soldado.getApellido(),
                    soldado.getCuartelCodigo(),
                    soldado.getOficialId());
            }

            System.out.println("\nTotal de soldados: " + soldados.size());
        }

        pausar();
    }

    private void modificarSoldado() {
        System.out.println("\n--- MODIFICAR SOLDADO ---");
        System.out.print("Ingrese DNI del soldado a modificar: ");
        String dni = scanner.nextLine().trim();

        if (dni.isEmpty()) {
            System.out.println("Error: El DNI no puede estar vacío.");
            pausar();
            return;
        }

        Soldado soldadoExistente = daoSoldados.obtenerPorDni(dni);

        if (soldadoExistente == null) {
            System.out.println("No se encontró ningún soldado con el DNI: " + dni);
            pausar();
            return;
        }

        System.out.println("\nDatos actuales del soldado:");
        mostrarDetallesSoldado(soldadoExistente);

        System.out.println("\nIngrese los nuevos datos (presione Enter para mantener el valor actual):");

        System.out.print("Nuevo nombre [" + soldadoExistente.getNombre() + "]: ");
        String nuevoNombre = scanner.nextLine().trim();
        if (nuevoNombre.isEmpty()) {
            nuevoNombre = soldadoExistente.getNombre();
        }

        System.out.print("Nuevo apellido [" + soldadoExistente.getApellido() + "]: ");
        String nuevoApellido = scanner.nextLine().trim();
        if (nuevoApellido.isEmpty()) {
            nuevoApellido = soldadoExistente.getApellido();
        }

        System.out.print("Nuevo código de cuartel [" + soldadoExistente.getCuartelCodigo() + "]: ");
        String cuartelInput = scanner.nextLine().trim();
        int nuevoCuartelCodigo = cuartelInput.isEmpty() ? soldadoExistente.getCuartelCodigo() : Integer.parseInt(cuartelInput);

        System.out.print("Nuevo ID del oficial [" + soldadoExistente.getOficialId() + "]: ");
        String oficialInput = scanner.nextLine().trim();
        int nuevoOficialId = oficialInput.isEmpty() ? soldadoExistente.getOficialId() : Integer.parseInt(oficialInput);

        Soldado soldadoModificado = new Soldado(soldadoExistente.getId(), dni, nuevoNombre, nuevoApellido, nuevoCuartelCodigo, nuevoOficialId);
        daoSoldados.actualizarSoldado(dni, soldadoModificado);

        System.out.println("¡Soldado modificado exitosamente!");
        pausar();
    }

    private void eliminarSoldado() {
        System.out.println("\n--- ELIMINAR SOLDADO ---");
        System.out.print("Ingrese DNI del soldado a eliminar: ");
        String dni = scanner.nextLine().trim();

        if (dni.isEmpty()) {
            System.out.println("Error: El DNI no puede estar vacío.");
            pausar();
            return;
        }

        Soldado soldado = daoSoldados.obtenerPorDni(dni);

        if (soldado == null) {
            System.out.println("No se encontró ningún soldado con el DNI: " + dni);
            pausar();
            return;
        }

        System.out.println("\nDatos del soldado a eliminar:");
        mostrarDetallesSoldado(soldado);

        System.out.print("\n¿Está seguro que desea eliminar este soldado? (S/N): ");
        String confirmacion = scanner.nextLine().trim().toLowerCase();

        if (confirmacion.equals("s") || confirmacion.equals("si")) {
            daoSoldados.eliminarSoldado(dni);
            System.out.println("¡Soldado eliminado exitosamente!");
        } else {
            System.out.println("Operación cancelada.");
        }

        pausar();
    }

    private void buscarSoldadosPorCuartel() {
        System.out.println("\n--- BUSCAR SOLDADOS POR CUARTEL ---");
        System.out.print("Ingrese código del cuartel: ");
        int cuartelCodigo = leerEntero();

        if (cuartelCodigo <= 0) {
            System.out.println("Error: El código del cuartel debe ser un número positivo.");
            pausar();
            return;
        }

        List<Soldado> soldados = daoSoldados.obtenerSoldadosPorCuartel(cuartelCodigo);

        if (soldados.isEmpty()) {
            System.out.println("No se encontraron soldados asignados al cuartel con código: " + cuartelCodigo);
        } else {
            System.out.println("\nSoldados asignados al cuartel " + cuartelCodigo + ":");
            System.out.println(String.format("%-5s %-12s %-20s %-20s %-10s",
                "ID", "DNI", "NOMBRE", "APELLIDO", "OFICIAL"));
            System.out.println("--------------------------------------------------------------------");

            for (Soldado soldado : soldados) {
                System.out.printf("%-5d %-12s %-20s %-20s %-10d%n",
                    soldado.getId(),
                    soldado.getDni(),
                    soldado.getNombre(),
                    soldado.getApellido(),
                    soldado.getOficialId());
            }

            System.out.println("\nTotal de soldados en el cuartel: " + soldados.size());
        }

        pausar();
    }

    private void buscarSoldadosPorOficial() {
        System.out.println("\n--- BUSCAR SOLDADOS POR OFICIAL ---");
        System.out.print("Ingrese ID del oficial: ");
        int oficialId = leerEntero();

        if (oficialId <= 0) {
            System.out.println("Error: El ID del oficial debe ser un número positivo.");
            pausar();
            return;
        }

        List<Soldado> soldados = daoSoldados.obtenerSoldadosPorOficial(oficialId);

        if (soldados.isEmpty()) {
            System.out.println("No se encontraron soldados asignados al oficial con ID: " + oficialId);
        } else {
            System.out.println("\nSoldados bajo el mando del oficial " + oficialId + ":");
            System.out.println(String.format("%-5s %-12s %-20s %-20s %-10s",
                "ID", "DNI", "NOMBRE", "APELLIDO", "CUARTEL"));
            System.out.println("--------------------------------------------------------------------");

            for (Soldado soldado : soldados) {
                System.out.printf("%-5d %-12s %-20s %-20s %-10d%n",
                    soldado.getId(),
                    soldado.getDni(),
                    soldado.getNombre(),
                    soldado.getApellido(),
                    soldado.getCuartelCodigo());
            }

            System.out.println("\nTotal de soldados bajo su mando: " + soldados.size());
        }

        pausar();
    }

    private void mostrarDetallesSoldado(Soldado soldado) {
        System.out.println("\n--- DETALLES DEL SOLDADO ---");
        System.out.println("ID: " + soldado.getId());
        System.out.println("DNI: " + soldado.getDni());
        System.out.println("Nombre: " + soldado.getNombre());
        System.out.println("Apellido: " + soldado.getApellido());
        System.out.println("Código de Cuartel: " + soldado.getCuartelCodigo());
        System.out.println("ID del Oficial: " + soldado.getOficialId());
    }

    private void volverAlMenuPrincipal() {
        System.out.println("\nVolviendo al menú principal...");
        menuActivo = false;
    }

    private int leerEntero() {
        try {
            String input = scanner.nextLine().trim();
            return input.isEmpty() ? 0 : Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Error: Por favor ingrese un número válido.");
            return 0;
        }
    }

    private void pausar() {
        System.out.print("\nPresione Enter para continuar...");
        scanner.nextLine();
    }
}
