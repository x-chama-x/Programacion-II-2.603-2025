package ejercicio1fabpelotas;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Tecnico extends Empleado {
    private ArrayList<MaquinaTecnicoJornada> historialAsignaciones;

    public Tecnico(Integer DNI, String nomApe, ArrayList<Integer> telefonos) {
        super(DNI, nomApe, telefonos);
        this.historialAsignaciones = new ArrayList<>();
    }

    public void agregarAsignacion(MaquinaTecnicoJornada asignacion) {
        if (asignacion.getTecnico() != this) {
            throw new IllegalArgumentException("La asignación no corresponde a este técnico");
        }

        // Validar que no haya conflictos de horario
        if (tieneConflictoHorario(asignacion)) {
            throw new IllegalArgumentException("Conflicto de horario con asignación existente");
        }

        historialAsignaciones.add(asignacion);
        System.out.println("Técnico " + this.getNomApe() + " asignado a máquina " +
                asignacion.getMaquina().getNs() + " en turno " +
                asignacion.getJornada().getTurno());
    }

    public void removerAsignacion(MaquinaTecnicoJornada asignacion) {
        historialAsignaciones.remove(asignacion);
        System.out.println("Asignación removida para técnico " + this.getNomApe());
    }

    public boolean estaDisponible(Date feInicio, Date feFin, String turno) {
        for (MaquinaTecnicoJornada asignacion : historialAsignaciones) {
            Jornada jornada = asignacion.getJornada();

            // Verificar si hay solapamiento de fechas y mismo turno
            if (Objects.equals(jornada.getTurno(), turno) &&
                    haysolapamientoFechas(feInicio, feFin, jornada.getFecInicio(), jornada.getFecFin())) {
                return false; // Ya tiene asignación en ese período
            }
        }
        return true; // Está disponible
    }

    // verifica si hay conflicto de horario con una nueva asignación
    private boolean haysolapamientoFechas(Date fechaInicio, Date fechaFin, Date fecInicio, Date fecFin) {
        return (fechaInicio.before(fecFin) && fechaFin.after(fecInicio));
    }

    private boolean tieneConflictoHorario(MaquinaTecnicoJornada nuevaAsignacion) {
        Jornada nuevaJornada = nuevaAsignacion.getJornada();

        for (MaquinaTecnicoJornada asignacionExistente : historialAsignaciones) {
            Jornada jornadaExistente = asignacionExistente.getJornada();

            // Mismo turno y fechas que se solapan = conflicto
            if (Objects.equals(jornadaExistente.getTurno(), nuevaJornada.getTurno()) &&
                    haysolapamientoFechas(nuevaJornada.getFecInicio(), nuevaJornada.getFecFin(),
                            jornadaExistente.getFecInicio(), jornadaExistente.getFecFin())) {
                return true;
            }
        }
        return false;
    }


}
