package ejercicio1fabpelotas;

import java.util.ArrayList;
import java.util.Date;

public class Administrador extends Empleado {
    public Administrador(Integer DNI, String nomApe, ArrayList<Integer> telefonos) {
        super(DNI, nomApe, telefonos);
    }

    public boolean asignarTecnicoAMaquina(Tecnico tecnico, Maquina maquina, Date feInicio, Date feFin, String turno) {
        // Validar que los parámetros no sean nulos
        if (tecnico == null || maquina == null || feInicio == null || feFin == null || turno == null) {
            System.out.println("Error: Parámetros inválidos en la asignación");
            return false;
        }

        // Validar disponibilidad del técnico
        if (!tecnico.estaDisponible(feInicio, feFin, turno)) {
            System.out.println("Error: El técnico " + tecnico.getNomApe() + " no está disponible en esas fechas/turno");
            return false;
        }

        // Validar estado de la máquina
        if (!maquina.esOperativa()) {
            System.out.println("Error: La máquina " + maquina.getNs() + " no está operativa");
            return false;
        }

        // Crear la jornada y asignación
        Jornada jornada = new Jornada(feInicio, feFin, turno);
        MaquinaTecnicoJornada asignacion = new MaquinaTecnicoJornada(tecnico, maquina, jornada);

        // Intentar registrar la asignación
        if (registrarNuevaAsignacion(asignacion)) {
            System.out.println("Asignación exitosa: Técnico " + tecnico.getNomApe() +
                             " asignado a máquina " + maquina.getNs() +
                             " en turno " + turno);
            return true;
        }
        return false;
    }

    private boolean registrarNuevaAsignacion(MaquinaTecnicoJornada asignacion) {
        try {
            // Registrar en el técnico
            asignacion.getTecnico().agregarAsignacion(asignacion);
            // Si no hubo error, registrar en la fábrica
            FabricaGolazo.getInstance().registrarAsignacion(asignacion);
            return true;
        } catch (IllegalArgumentException e) {
            System.out.println("Error al registrar asignación: " + e.getMessage());
            return false;
        }
    }

    public boolean asignarJornada(ArrayList<MaquinaTecnicoJornada> jornadas) {
        if (jornadas == null || jornadas.isEmpty()) {
            System.out.println("Error: La lista de jornadas está vacía");
            return false;
        }

        for (MaquinaTecnicoJornada asignacion : jornadas) {
            if (!validarYRegistrarAsignacion(asignacion)) {
                // Si falla una asignación, revertir las anteriores
                revertirAsignaciones(jornadas, asignacion);
                return false;
            }
        }
        return true;
    }

    private boolean validarYRegistrarAsignacion(MaquinaTecnicoJornada asignacion) {
        Tecnico tecnico = asignacion.getTecnico();
        Jornada jornada = asignacion.getJornada();
        Maquina maquina = asignacion.getMaquina();

        if (!tecnico.estaDisponible(jornada.getFecInicio(), jornada.getFecFin(), jornada.getTurno())) {
            System.out.println("Error: El técnico " + tecnico.getNomApe() + " no está disponible");
            return false;
        }

        if (!maquina.esOperativa()) {
            System.out.println("Error: La máquina " + maquina.getNs() + " no está operativa");
            return false;
        }

        return registrarNuevaAsignacion(asignacion);
    }

    private void revertirAsignaciones(ArrayList<MaquinaTecnicoJornada> jornadas, MaquinaTecnicoJornada asignacionFallida) {
        for (MaquinaTecnicoJornada asignacion : jornadas) {
            if (asignacion == asignacionFallida) {
                break;
            }
            asignacion.getTecnico().removerAsignacion(asignacion);
            FabricaGolazo.getInstance().removerAsignacion(asignacion);
        }
    }

    public void cambiarEstadoMaquina(Maquina maquina, boolean estado) {
        if (maquina == null) {
            System.out.println("Error: Máquina inválida");
            return;
        }

        maquina.setEstado(estado);
        if (!estado) {
            System.out.println("Máquina " + maquina.getNs() + " marcada como fuera de servicio");
        } else {
            System.out.println("Máquina " + maquina.getNs() + " marcada como operativa");
            if (maquina.getMaquinaReemplazo() != null) {
                maquina.quitarReemplazo();
                System.out.println("Se ha quitado la máquina de reemplazo");
            }
        }
    }

    public void asignarMaquinaReemplazo(Maquina maquinaFueraServicio, Maquina maquinaReemplazo) {
        if (maquinaFueraServicio == null || maquinaReemplazo == null) {
            System.out.println("Error: Máquinas inválidas");
            return;
        }

        if (maquinaFueraServicio.esOperativa()) {
            System.out.println("Error: La máquina " + maquinaFueraServicio.getNs() + " está operativa y no necesita reemplazo");
            return;
        }

        maquinaFueraServicio.asignarReemplazo(maquinaReemplazo);
    }
}
