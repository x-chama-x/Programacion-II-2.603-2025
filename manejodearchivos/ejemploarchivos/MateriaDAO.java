package ejemploarchivos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MateriaDAO {

    // metodo para verificar si una materia existe por su codigo
    public boolean existeMateria(String codigo) {
        List<Materia> lista = leerMaterias();
        for (Materia m : lista) {
            if (m.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }


    // CREATE
    public boolean crearMateria(Materia m) {
        if (existeMateria(m.getCodigo())) {
            return false; // Materia ya existe, no se puede crear
        }
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BD.getArchivoMaterias(), true))) {
            bw.write(m.toString());
            bw.newLine();
            return true; // Creado exitosamente
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // READ
    private List<Materia> leerMaterias() {
        List<Materia> lista = new ArrayList<>();
        try (var br = new java.io.BufferedReader(new java.io.FileReader(BD.getArchivoMaterias()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    lista.add(new Materia(partes[0], partes[1]));
                }
            }
        } catch (IOException ex) {
            // Si no existe el archivo, se devuelve lista vacía
        }
        return lista;
    }

    // READ BY CODIGO
    public Materia buscarMateriaPorCodigo(String codigoBuscado) {
        List<Materia> lista = leerMaterias();
        for (Materia m : lista) {
            if (m.getCodigo().equals(codigoBuscado)) {
                return m;
            }
        }
        return null; // Si no se encuentra la materia
    }

    // UPDATE - Modificado para validar codigo duplicado
    public boolean actualizarMateria(String codigoBuscado, Materia nueva) {
        List<Materia> lista = leerMaterias();
        boolean materiaEncontrada = false;
        for (int i = 0; i < lista.size(); i++) {
            Materia m = lista.get(i);
            if (m.getCodigo().equals(codigoBuscado)) {
                // Verificar si el nuevo código ya existe en otra materia
                if (!codigoBuscado.equals(nueva.getCodigo()) && existeMateria(nueva.getCodigo())) {
                    return false; // Nuevo código ya existe, no se puede actualizar
                }
                lista.set(i, nueva);
                materiaEncontrada = true;
                break;
            }
        }
        if (materiaEncontrada) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(BD.getArchivoMaterias()))) {
                for (Materia m : lista) {
                    bw.write(m.toString());
                    bw.newLine();
                }
                return true; // Actualizado exitosamente
            } catch (IOException ex) {
                ex.printStackTrace();
                return false;
            }
        } else {
            return materiaEncontrada;
        }
    }

    // DELETE - Eliminar materia por codigo (usar el metodo existeMateria para verificar si existe)
    public boolean eliminarMateria(String codigo) {
        if (!existeMateria(codigo)) {
            return false; // Materia no existe, no se puede eliminar
        }

        List<Materia> lista = leerMaterias();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BD.getArchivoMaterias()))) {
            for (Materia m : lista) {
                if (!m.getCodigo().equals(codigo)) {
                    bw.write(m.toString());
                    bw.newLine();
                }
            }
            return true; // Eliminado exitosamente
        } catch (IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
