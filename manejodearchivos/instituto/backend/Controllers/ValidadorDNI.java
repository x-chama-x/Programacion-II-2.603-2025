package instituto.backend.Controllers;

/**
 * Clase para validar que no existan DNIs duplicados entre estudiantes y profesores
 */
public class ValidadorDNI {

    private static final DAOEstudiantes daoEstudiantes = new DAOEstudiantes();
    private static final DAOProfesores daoProfesores = new DAOProfesores();

    /**
     * Verifica si un DNI ya existe en el sistema (estudiantes o profesores)
     * @param dni El DNI a verificar
     * @return true si el DNI ya existe, false en caso contrario
     */
    public static boolean dniExisteEnSistema(int dni) {
        return daoEstudiantes.existe(dni) || daoProfesores.existe(dni);
    }

    /**
     * Verifica si se puede registrar un estudiante con el DNI dado
     * @param dni El DNI del estudiante a registrar
     * @return true si se puede registrar (DNI no existe), false en caso contrario
     */
    public static boolean puedeRegistrarEstudiante(int dni) {
        return !dniExisteEnSistema(dni);
    }

    /**
     * Verifica si se puede registrar un profesor con el DNI dado
     * @param dni El DNI del profesor a registrar
     * @return true si se puede registrar (DNI no existe), false en caso contrario
     */
    public static boolean puedeRegistrarProfesor(int dni) {
        return !dniExisteEnSistema(dni);
    }

    /**
     * Obtiene información sobre dónde está registrado un DNI
     * @param dni El DNI a buscar
     * @return String con información del tipo de registro
     */
    public static String obtenerTipoRegistro(int dni) {
        if (daoEstudiantes.existe(dni)) {
            return "ESTUDIANTE";
        }
        if (daoProfesores.existe(dni)) {
            return "PROFESOR";
        }
        return "NO_REGISTRADO";
    }
}
