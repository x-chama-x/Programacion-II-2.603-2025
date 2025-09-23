package frontend;

import backend.Controllers.DAOOficiales;
import backend.Controllers.DAOSoldados;
import backend.Controllers.ValidadorDNI;
import backend.Models.Oficial;
import backend.Models.Soldado;
import java.util.List;
import java.util.Scanner;

public class MenuOficial implements MenuConector {

    private Scanner scanner;
    private DAOOficiales daoOficiales;
    private DAOSoldados daoSoldados;
    private boolean menuActivo;

    public MenuOficial() {
        this.scanner = new Scanner(System.in);
        this.daoOficiales = new DAOOficiales();
        this.daoSoldados = new DAOSoldados();
        this.menuActivo = true;
    }

    @Override
    public void IniciarMenu() {
        while (menuActivo) {
            mostrarMenuOficial();
            int opcion = leerOpcion();
            procesarOpcion(opcion);
        }
    }

    private void mostrarMenuOficial() {
        System.out.println("\n========================================");
        System.out.println("         GESTIÓN DE OFICIALES");
        System.out.println("========================================");
        System.out.println("1. Ingresar nuevo oficial");
        System.out.println("2. Consultar oficial por DNI");
        System.out.println("3. Consultar oficial por ID");
        System.out.println("4. Listar todos los oficiales");
        System.out.println("5. Modificar datos de oficial");
        System.out.println("6. Eliminar oficial");
        //System.out.println("7. Buscar oficiales por rango"); // todo lo comentado no esta implementado
        //System.out.println("8. Ver soldados bajo mando de oficial");
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
                ingresarOficial();
                break;
            case 2:
                consultarOficialPorDni();
                break;
            case 3:
                consultarOficialPorId();
                break;
            case 4:
                listarTodosLosOficiales();
                break;
            case 5:
                modificarOficial();
                break;
            case 6:
                eliminarOficial();
                break;
            case 7:
                //buscarOficialesPorRango();
                break;
            case 8:
                //verSoldadosBajoMando();
                break;
            case 0:
                volverAlMenuPrincipal();
                break;
            default:
                System.out.println("Opción inválida. Por favor, intente de nuevo.");
                pausar();
        }
    }

    private void ingresarOficial() {
        System.out.println("\n--- INGRESAR NUEVO OFICIAL ---");

        System.out.print("Ingrese DNI: ");
        String dni = scanner.nextLine().trim();

        if (dni.isEmpty()) {
            System.out.println("Error: El DNI no puede estar vacío.");
            pausar();
            return;
        }

        // Usar el ValidadorDNI para verificaciones más completas
        ValidadorDNI validador = new ValidadorDNI();

        if (!validador.esFormatoValido(dni)) {
            System.out.println("Error: El formato del DNI no es válido. Debe tener entre 7 y 8 dígitos numéricos.");
            pausar();
            return;
        }

        // Obtener listas para validación
        List<Oficial> oficiales = daoOficiales.leerOficiales();
        List<Soldado> soldados = daoSoldados.leerSoldados();

        if (validador.existeDNI(dni, soldados, oficiales)) {
            System.out.println("Error: El DNI " + dni + " ya está registrado en el sistema.");
            System.out.println(validador.obtenerInfoDNI(dni, soldados, oficiales));
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

        System.out.print("Ingrese rango (Teniente, Capitán, Mayor, Coronel, General): ");
        String rango = scanner.nextLine().trim();

        if (rango.isEmpty()) {
            System.out.println("Error: El rango no puede estar vacío.");
            pausar();
            return;
        }

        Oficial nuevoOficial = new Oficial(dni, nombre, apellido, rango);
        daoOficiales.crearOficial(nuevoOficial);

        System.out.println("¡Oficial ingresado exitosamente!");
        pausar();
    }

    private void consultarOficialPorDni() {
        System.out.println("\n--- CONSULTAR OFICIAL POR DNI ---");
        System.out.print("Ingrese DNI del oficial: ");
        String dni = scanner.nextLine().trim();

        if (dni.isEmpty()) {
            System.out.println("Error: El DNI no puede estar vacío.");
            pausar();
            return;
        }

        Oficial oficial = daoOficiales.obtenerPorDni(dni);

        if (oficial != null) {
            mostrarDetallesOficial(oficial);
            mostrarSoldadosBajoMando(oficial.getId());
        } else {
            System.out.println("No se encontró ningún oficial con el DNI: " + dni);
        }

        pausar();
    }

    private void consultarOficialPorId() {
        System.out.println("\n--- CONSULTAR OFICIAL POR ID ---");
        System.out.print("Ingrese ID del oficial: ");
        int id = leerEntero();

        if (id <= 0) {
            System.out.println("Error: El ID debe ser un número positivo.");
            pausar();
            return;
        }

        Oficial oficial = daoOficiales.obtenerPorId(id);

        if (oficial != null) {
            mostrarDetallesOficial(oficial);
            mostrarSoldadosBajoMando(oficial.getId());
        } else {
            System.out.println("No se encontró ningún oficial con el ID: " + id);
        }

        pausar();
    }

    private void listarTodosLosOficiales() {
        System.out.println("\n--- LISTA DE TODOS LOS OFICIALES ---");
        List<Oficial> oficiales = daoOficiales.leerOficiales();

        if (oficiales.isEmpty()) {
            System.out.println("No hay oficiales registrados en el sistema.");
        } else {
            System.out.println("\n" + String.format("%-5s %-12s %-20s %-20s %-15s %-10s",
                "ID", "DNI", "NOMBRE", "APELLIDO", "RANGO", "SOLDADOS"));
            System.out.println("--------------------------------------------------------------------------------");

            for (Oficial oficial : oficiales) {
                List<Soldado> soldados = daoSoldados.obtenerSoldadosPorOficial(oficial.getId());
                System.out.printf("%-5d %-12s %-20s %-20s %-15s %-10d%n",
                    oficial.getId(),
                    oficial.getDni(),
                    oficial.getNombre(),
                    oficial.getApellido(),
                    oficial.getRango(),
                    soldados.size());
            }

            System.out.println("\nTotal de oficiales: " + oficiales.size());
        }

        pausar();
    }

    private void modificarOficial() {
        System.out.println("\n--- MODIFICAR OFICIAL ---");
        System.out.print("Ingrese DNI del oficial a modificar: ");
        String dni = scanner.nextLine().trim();

        if (dni.isEmpty()) {
            System.out.println("Error: El DNI no puede estar vacío.");
            pausar();
            return;
        }

        Oficial oficialExistente = daoOficiales.obtenerPorDni(dni);

        if (oficialExistente == null) {
            System.out.println("No se encontró ningún oficial con el DNI: " + dni);
            pausar();
            return;
        }

        System.out.println("\nDatos actuales del oficial:");
        mostrarDetallesOficial(oficialExistente);

        System.out.println("\nIngrese los nuevos datos (presione Enter para mantener el valor actual):");

        System.out.print("Nuevo nombre [" + oficialExistente.getNombre() + "]: ");
        String nuevoNombre = scanner.nextLine().trim();
        if (nuevoNombre.isEmpty()) {
            nuevoNombre = oficialExistente.getNombre();
        }

        System.out.print("Nuevo apellido [" + oficialExistente.getApellido() + "]: ");
        String nuevoApellido = scanner.nextLine().trim();
        if (nuevoApellido.isEmpty()) {
            nuevoApellido = oficialExistente.getApellido();
        }

        System.out.print("Nuevo rango [" + oficialExistente.getRango() + "]: ");
        String nuevoRango = scanner.nextLine().trim();
        if (nuevoRango.isEmpty()) {
            nuevoRango = oficialExistente.getRango();
        }

        Oficial oficialModificado = new Oficial(oficialExistente.getId(), dni, nuevoNombre, nuevoApellido, nuevoRango);
        daoOficiales.actualizarOficial(dni, oficialModificado);

        System.out.println("¡Oficial modificado exitosamente!");
        pausar();
    }

    private void eliminarOficial() {
        System.out.println("\n--- ELIMINAR OFICIAL ---");
        System.out.print("Ingrese DNI del oficial a eliminar: ");
        String dni = scanner.nextLine().trim();

        if (dni.isEmpty()) {
            System.out.println("Error: El DNI no puede estar vacío.");
            pausar();
            return;
        }

        Oficial oficial = daoOficiales.obtenerPorDni(dni);

        if (oficial == null) {
            System.out.println("No se encontró ningún oficial con el DNI: " + dni);
            pausar();
            return;
        }

        System.out.println("\nDatos del oficial a eliminar:");
        mostrarDetallesOficial(oficial);

        // Verificar si tiene soldados bajo su mando
        List<Soldado> soldadosBajoMando = daoSoldados.obtenerSoldadosPorOficial(oficial.getId());
        if (!soldadosBajoMando.isEmpty()) {
            System.out.println("\nADVERTENCIA: Este oficial tiene " + soldadosBajoMando.size() + " soldado(s) bajo su mando.");
            System.out.println("Al eliminar el oficial, estos soldados quedarán sin oficial asignado.");
        }

        System.out.print("\n¿Está seguro que desea eliminar este oficial? (S/N): ");
        String confirmacion = scanner.nextLine().trim().toLowerCase();

        if (confirmacion.equals("s") || confirmacion.equals("si")) {
            // Liberar soldados antes de eliminar oficial
            if (!soldadosBajoMando.isEmpty()) {
                for (Soldado soldado : soldadosBajoMando) {
                    soldado.setOficialId(0);
                    daoSoldados.actualizarSoldadoPorId(soldado.getId(), soldado);
                }
                System.out.println("Se liberaron " + soldadosBajoMando.size() + " soldado(s) del oficial eliminado.");
            }

            daoOficiales.eliminarOficial(dni);
            System.out.println("¡Oficial eliminado exitosamente!");
        } else {
            System.out.println("Operación cancelada.");
        }

        pausar();
    }

    private void buscarOficialesPorRango() {
        System.out.println("\n--- BUSCAR OFICIALES POR RANGO ---");
        System.out.println("Rangos disponibles: Teniente, Capitán, Mayor, Coronel, General");
        System.out.print("Ingrese rango: ");
        String rango = scanner.nextLine().trim();

        if (rango.isEmpty()) {
            System.out.println("Error: El rango no puede estar vacío.");
            pausar();
            return;
        }

        List<Oficial> oficiales = daoOficiales.obtenerOficialesPorRango(rango);

        if (oficiales.isEmpty()) {
            System.out.println("No se encontraron oficiales con el rango: " + rango);
        } else {
            System.out.println("\nOficiales con rango " + rango + ":");
            System.out.println(String.format("%-5s %-12s %-20s %-20s %-10s",
                "ID", "DNI", "NOMBRE", "APELLIDO", "SOLDADOS"));
            System.out.println("--------------------------------------------------------------------");

            for (Oficial oficial : oficiales) {
                List<Soldado> soldados = daoSoldados.obtenerSoldadosPorOficial(oficial.getId());
                System.out.printf("%-5d %-12s %-20s %-20s %-10d%n",
                    oficial.getId(),
                    oficial.getDni(),
                    oficial.getNombre(),
                    oficial.getApellido(),
                    soldados.size());
            }

            System.out.println("\nTotal de oficiales con rango " + rango + ": " + oficiales.size());
        }

        pausar();
    }

    private void verSoldadosBajoMando() {
        System.out.println("\n--- VER SOLDADOS BAJO MANDO ---");
        System.out.print("Ingrese DNI del oficial: ");
        String dni = scanner.nextLine().trim();

        if (dni.isEmpty()) {
            System.out.println("Error: El DNI no puede estar vacío.");
            pausar();
            return;
        }

        Oficial oficial = daoOficiales.obtenerPorDni(dni);

        if (oficial == null) {
            System.out.println("No se encontró ningún oficial con el DNI: " + dni);
            pausar();
            return;
        }

        System.out.println("\nOficial: " + oficial.getNombreCompleto() + " (" + oficial.getRango() + ")");
        mostrarSoldadosBajoMando(oficial.getId());

        pausar();
    }

    private void mostrarDetallesOficial(Oficial oficial) {
        System.out.println("\n--- DETALLES DEL OFICIAL ---");
        System.out.println("ID: " + oficial.getId());
        System.out.println("DNI: " + oficial.getDni());
        System.out.println("Nombre: " + oficial.getNombre());
        System.out.println("Apellido: " + oficial.getApellido());
        System.out.println("Rango: " + oficial.getRango());

        List<Soldado> soldados = daoSoldados.obtenerSoldadosPorOficial(oficial.getId());
        System.out.println("Soldados bajo su mando: " + soldados.size());
    }

    private void mostrarSoldadosBajoMando(int oficialId) {
        List<Soldado> soldados = daoSoldados.obtenerSoldadosPorOficial(oficialId);

        if (soldados.isEmpty()) {
            System.out.println("\nNo tiene soldados bajo su mando.");
        } else {
            System.out.println("\n--- SOLDADOS BAJO SU MANDO ---");
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
        }
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
