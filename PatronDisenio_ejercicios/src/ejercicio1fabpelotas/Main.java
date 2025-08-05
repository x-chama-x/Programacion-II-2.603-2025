package ejercicio1fabpelotas;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        // Obtener instancia de la fábrica
        FabricaGolazo fabrica = FabricaGolazo.getInstance();

        // Crear técnicos
        ArrayList<Integer> telefonos1 = new ArrayList<>();
        telefonos1.add(123456789);
        Tecnico tecnico1 = new Tecnico(12345678, "Juan Pérez", telefonos1);

        ArrayList<Integer> telefonos2 = new ArrayList<>();
        telefonos2.add(987654321);
        Tecnico tecnico2 = new Tecnico(87654321, "María García", telefonos2);

        // Crear administrador
        ArrayList<Integer> telefonosAdmin = new ArrayList<>();
        telefonosAdmin.add(1567431245);
        Administrador admin = new Administrador(4356187, "Carlos López", telefonosAdmin);
        fabrica.agregarAdministrador(admin);

        // Crear fechas para las jornadas
        Calendar cal = Calendar.getInstance();
        cal.set(2025, Calendar.AUGUST, 5);
        Date fechaInicio = cal.getTime();

        cal.set(2025, Calendar.AUGUST, 10);
        Date fechaFin = cal.getTime();

        // Pruebas de asignaciones
        System.out.println("\n=== Pruebas de asignaciones ===");

        // Primera asignación
        boolean resultado = admin.asignarTecnicoAMaquina(
            tecnico1,
            fabrica.getPlantas().get(0).getMaquinas().get(0),
            fechaInicio,
            fechaFin,
            "Mañana"
        );
        System.out.println("Resultado primera asignación: " + resultado);

        // Intento de asignación con conflicto (mismo técnico, misma fecha y turno)
        resultado = admin.asignarTecnicoAMaquina(
            tecnico1,
            fabrica.getPlantas().get(0).getMaquinas().get(1),
            fechaInicio,
            fechaFin,
            "Mañana"
        );
        System.out.println("Resultado segunda asignación (conflicto): " + resultado);

        // Asignación válida (mismo técnico, diferente turno)
        resultado = admin.asignarTecnicoAMaquina(
            tecnico1,
            fabrica.getPlantas().get(0).getMaquinas().get(1),
            fechaInicio,
            fechaFin,
            "Tarde"
        );
        System.out.println("Resultado tercera asignación (diferente turno): " + resultado);

        // Asignación válida (diferente técnico)
        resultado = admin.asignarTecnicoAMaquina(
            tecnico2,
            fabrica.getPlantas().get(0).getMaquinas().get(2),
            fechaInicio,
            fechaFin,
            "Mañana"
        );
        System.out.println("Resultado cuarta asignación (diferente técnico): " + resultado);

        System.out.println("\n=== Estado final de asignaciones ===");
        mostrarAsignacionesActuales(fabrica);

        // Fabricar algunas pelotas
        fabrica.fabricarPelotas(5);
        System.out.println("\nCantidad de pelotas fabricadas: " + fabrica.getCantidadPelotasFabricadas());
    }

    private static void mostrarAsignacionesActuales(FabricaGolazo fabrica) {
        System.out.println("Asignaciones registradas:");
        for (MaquinaTecnicoJornada asignacion : fabrica.getAsignaciones()) {
            System.out.println("Técnico: " + asignacion.getTecnico().getNomApe() +
                             " - Máquina: " + asignacion.getMaquina().getNs() +
                             " - Turno: " + asignacion.getJornada().getTurno());
        }
    }
}