package ejemploarchivos;

import java.io.File;

public class BD {
    private static final String ARCHIVO = "estudiantes.txt";
    private static final String ARCHIVO_MATERIAS = "materias.txt";

    public static void inicializarBD() {
        try {
            File archivo = new File(ARCHIVO);
            File archivoMaterias = new File(ARCHIVO_MATERIAS);
            if (!archivo.exists() || !archivoMaterias.exists()) {
                if (archivo.createNewFile()) {
                    System.out.println("Archivos creado... ");
                } else {
                    System.out.println("Archivos existente: ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File getArchivo() {
        return new File(ARCHIVO);
    }

    public static File getArchivoMaterias() {
        return new File(ARCHIVO_MATERIAS);
    }
}
