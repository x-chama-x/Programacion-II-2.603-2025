package ejemploarchivos;

import java.io.File;

public class BD {
    private static final String ARCHIVO = "estudiantes.txt";

    public static void inicializarBD() {
        try {
            File archivo = new File(ARCHIVO);
            if (!archivo.exists()) {
                if (archivo.createNewFile()) {
                    System.out.println("Archivo creado... ");
                } else {
                    System.out.println("Archivo existente: ");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public static File getArchivo() {
        return new File(ARCHIVO);
    }
}
