package instituto.backend.Controllers;

import instituto.Conections.BDMateriasEstudiantes;
import instituto.backend.Models.MateriasEstudiantes;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MateriasEstudiantesDAO {

    public void crearRelacion(MateriasEstudiantes me) {
        if (!existe(me.getCodMateria(), me.getCodEstudiante())) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDMateriasEstudiantes.getARCHIVO(), true))) {
                bw.write(me.toString());
                bw.newLine();
            } catch (IOException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            } finally {
                System.out.println("Relación materia-estudiante agregada...");
            }
        } else {
            System.out.println("ERROR: Relación ya existente");
        }
    }

    public boolean existe(int codMateria, int codEstudiante) {
        List<MateriasEstudiantes> lista = leerRelaciones();
        for (MateriasEstudiantes me : lista) {
            if (me.getCodMateria() == codMateria && me.getCodEstudiante() == codEstudiante) {
                return true;
            }
        }
        return false;
    }

    public List<MateriasEstudiantes> leerRelaciones() {
        List<MateriasEstudiantes> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BDMateriasEstudiantes.getARCHIVO()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lista.add(MateriasEstudiantes.fromString(linea));
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        return lista;
    }

    public List<Integer> obtenerEstudiantesPorMateria(int codMateria) {
        List<Integer> estudiantes = new ArrayList<>();
        List<MateriasEstudiantes> relaciones = leerRelaciones();
        for (MateriasEstudiantes me : relaciones) {
            if (me.getCodMateria() == codMateria) {
                estudiantes.add(me.getCodEstudiante());
            }
        }
        return estudiantes;
    }

    public List<Integer> obtenerMateriasPorEstudiante(int codEstudiante) {
        List<Integer> materias = new ArrayList<>();
        List<MateriasEstudiantes> relaciones = leerRelaciones();
        for (MateriasEstudiantes me : relaciones) {
            if (me.getCodEstudiante() == codEstudiante) {
                materias.add(me.getCodMateria());
            }
        }
        return materias;
    }

    public void eliminarRelacion(int codMateria, int codEstudiante) {
        List<MateriasEstudiantes> lista = leerRelaciones();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDMateriasEstudiantes.getARCHIVO()))) {
            for (MateriasEstudiantes me : lista) {
                if (!(me.getCodMateria() == codMateria && me.getCodEstudiante() == codEstudiante)) {
                    bw.write(me.toString());
                    bw.newLine();
                }
            }
            System.out.println("Relación eliminada...");
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void eliminarTodasRelacionesEstudiante(int codEstudiante) {
        List<MateriasEstudiantes> lista = leerRelaciones();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDMateriasEstudiantes.getARCHIVO()))) {
            for (MateriasEstudiantes me : lista) {
                if (me.getCodEstudiante() != codEstudiante) {
                    bw.write(me.toString());
                    bw.newLine();
                }
            }
            System.out.println("Todas las relaciones del estudiante eliminadas...");
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void eliminarTodasRelacionesMateria(int codMateria) {
        List<MateriasEstudiantes> lista = leerRelaciones();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDMateriasEstudiantes.getARCHIVO()))) {
            for (MateriasEstudiantes me : lista) {
                if (me.getCodMateria() != codMateria) {
                    bw.write(me.toString());
                    bw.newLine();
                }
            }
            System.out.println("Todas las relaciones de la materia eliminadas...");
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}
