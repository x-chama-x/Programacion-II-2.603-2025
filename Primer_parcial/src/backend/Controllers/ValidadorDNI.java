package backend.Controllers;

import backend.Models.Oficial;
import backend.Models.Soldado;
import java.util.List;

public class ValidadorDNI {
    public boolean existeDNI(String dni, List<Soldado> soldados, List<Oficial> oficiales) {
        if (dni == null || dni.trim().isEmpty()) {
            return false;
        }

        String dniLimpio = dni.trim();

        // Verificar en soldados
        for (Soldado soldado : soldados) {
            if (soldado.getDni().equals(dniLimpio)) {
                return true;
            }
        }

        // Verificar en oficiales
        for (Oficial oficial : oficiales) {
            if (oficial.getDni().equals(dniLimpio)) {
                return true;
            }
        }

        return false;
    }

    public boolean esDNIDisponibleParaActualizacion(String dni, List<Soldado> soldados, List<Oficial> oficiales, String tipoEntidad, int idEntidad) {
        if (dni == null || dni.trim().isEmpty()) {
            return false;
        }

        String dniLimpio = dni.trim();

        // Verificar en soldados (excepto si es el mismo soldado)
        if (!tipoEntidad.equals("SOLDADO")) {
            for (Soldado soldado : soldados) {
                if (soldado.getDni().equals(dniLimpio)) {
                    return false;
                }
            }
        } else {
            // Es un soldado, verificar que no exista otro soldado con el mismo DNI
            for (Soldado soldado : soldados) {
                if (soldado.getDni().equals(dniLimpio) && soldado.getId() != idEntidad) {
                    return false;
                }
            }
        }

        // Verificar en oficiales (excepto si es el mismo oficial)
        if (!tipoEntidad.equals("OFICIAL")) {
            for (Oficial oficial : oficiales) {
                if (oficial.getDni().equals(dniLimpio)) {
                    return false;
                }
            }
        } else {
            // Es un oficial, verificar que no exista otro oficial con el mismo DNI
            for (Oficial oficial : oficiales) {
                if (oficial.getDni().equals(dniLimpio) && oficial.getId() != idEntidad) {
                    return false;
                }
            }
        }

        return true;
    }
    public String obtenerInfoDNI(String dni, List<Soldado> soldados, List<Oficial> oficiales) {
        if (dni == null || dni.trim().isEmpty()) {
            return "DNI inválido";
        }

        String dniLimpio = dni.trim();

        for (Soldado soldado : soldados) {
            if (soldado.getDni().equals(dniLimpio)) {
                return "DNI pertenece al soldado: " + soldado.getNombreCompleto() + " (ID: " + soldado.getId() + ")";
            }
        }

        for (Oficial oficial : oficiales) {
            if (oficial.getDni().equals(dniLimpio)) {
                return "DNI pertenece al oficial: " + oficial.getNombreCompleto() + " - " + oficial.getRango() + " (ID: " + oficial.getId() + ")";
            }
        }

        return "DNI disponible";
    }

    public boolean esFormatoValido(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            return false;
        }

        String dniLimpio = dni.trim();

        // Verificar que tenga entre 7 y 8 dígitos (formato típico de DNI)
        if (dniLimpio.length() < 7 || dniLimpio.length() > 8) {
            return false;
        }

        // Verificar que solo contenga números
        return dniLimpio.matches("\\d+");
    }

    public boolean esValidoYDisponible(String dni, List<Soldado> soldados, List<Oficial> oficiales) {
        return esFormatoValido(dni) && !existeDNI(dni, soldados, oficiales);
    }
}
