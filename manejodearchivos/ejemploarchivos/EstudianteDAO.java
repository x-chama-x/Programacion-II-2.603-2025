package ejemploarchivos;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class EstudianteDAO {

    // Metodo para verificar si un DNI ya existe
    public boolean existeDni(String dni) {
        List<Estudiante> lista = leerEstudiantes();
        for (Estudiante e : lista) {
            if (e.getDni().equals(dni)) {
                return true;
            }
        }
        return false;
    }

    // CREATE - Modificado para validar DNI duplicado
    public boolean crearEstudiante(Estudiante e) {
        if (existeDni(e.getDni())) {
            return false; // DNI ya existe, no se puede crear
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BD.getArchivo(), true))) {
            bw.write(e.toString());
            bw.newLine();
            return true; // Creado exitosamente
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // READ
    public List<Estudiante> leerEstudiantes() {
        List<Estudiante> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BD.getArchivo()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lista.add(Estudiante.fromString(linea));
            }
        } catch (IOException ex) {
            // Si no existe el archivo, se devuelve lista vacía
        }
        return lista;
    }

    // READ BY DNI
    public Estudiante buscarEstudiantePorDni(String dniBuscado) {
        List<Estudiante> lista = leerEstudiantes();
        for (Estudiante e : lista) {
            if (e.getDni().equals(dniBuscado)) {
                return e;
            }
        }
        return null; // Si no se encuentra el estudiante
    }

    // UPDATE - Modificado para validar DNI duplicado
    public boolean actualizarEstudiante(String nombreBuscado, Estudiante nuevo) {
        List<Estudiante> lista = leerEstudiantes();
        boolean estudianteEncontrado = false;

        // Verificar si el nuevo DNI ya existe en otro estudiante
        for (Estudiante e : lista) {
            if (!e.getNombre().equalsIgnoreCase(nombreBuscado) && e.getDni().equals(nuevo.getDni())) {
                return false; // DNI ya existe en otro estudiante
            }
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BD.getArchivo()))) {
            for (Estudiante e : lista) {
                if (e.getNombre().equalsIgnoreCase(nombreBuscado)) {
                    bw.write(nuevo.toString());
                    estudianteEncontrado = true;
                } else {
                    bw.write(e.toString());
                }
                bw.newLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
        return estudianteEncontrado;
    }

    // DELETE
    public void eliminarEstudiante(String nombreBuscado) {
        List<Estudiante> lista = leerEstudiantes();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BD.getArchivo()))) {
            for (Estudiante e : lista) {
                if (!e.getNombre().equalsIgnoreCase(nombreBuscado)) {
                    bw.write(e.toString());
                    bw.newLine();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
