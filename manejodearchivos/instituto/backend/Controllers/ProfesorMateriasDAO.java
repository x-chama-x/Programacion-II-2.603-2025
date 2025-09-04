package instituto.backend.Controllers;

import instituto.Conections.BDProfesorMaterias;
import instituto.backend.Models.ProfesorMaterias;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfesorMateriasDAO {

    public void crearRelacion(ProfesorMaterias pm) {
        if (!existe(pm.getDniProfesor(), pm.getCodMateria())) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDProfesorMaterias.getARCHIVO(), true))) {
                bw.write(pm.toString());
                bw.newLine();
            } catch (IOException ex) {
                System.out.println("ERROR: " + ex.getMessage());
            } finally {
                System.out.println("Relación profesor-materia agregada...");
            }
        } else {
            System.out.println("ERROR: Relación ya existente");
        }
    }

    public boolean existe(int dniProfesor, int codMateria) {
        List<ProfesorMaterias> lista = leerRelaciones();
        for (ProfesorMaterias pm : lista) {
            if (pm.getDniProfesor() == dniProfesor && pm.getCodMateria() == codMateria) {
                return true;
            }
        }
        return false;
    }

    public List<ProfesorMaterias> leerRelaciones() {
        List<ProfesorMaterias> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BDProfesorMaterias.getARCHIVO()))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lista.add(ProfesorMaterias.fromString(linea));
            }
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        return lista;
    }

    public List<Integer> obtenerMateriasPorProfesor(int dniProfesor) {
        List<Integer> materias = new ArrayList<>();
        List<ProfesorMaterias> relaciones = leerRelaciones();
        for (ProfesorMaterias pm : relaciones) {
            if (pm.getDniProfesor() == dniProfesor) {
                materias.add(pm.getCodMateria());
            }
        }
        return materias;
    }

    public List<Integer> obtenerProfesoresPorMateria(int codMateria) {
        List<Integer> profesores = new ArrayList<>();
        List<ProfesorMaterias> relaciones = leerRelaciones();
        for (ProfesorMaterias pm : relaciones) {
            if (pm.getCodMateria() == codMateria) {
                profesores.add(pm.getDniProfesor());
            }
        }
        return profesores;
    }

    public void eliminarRelacion(int dniProfesor, int codMateria) {
        List<ProfesorMaterias> lista = leerRelaciones();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDProfesorMaterias.getARCHIVO()))) {
            for (ProfesorMaterias pm : lista) {
                if (!(pm.getDniProfesor() == dniProfesor && pm.getCodMateria() == codMateria)) {
                    bw.write(pm.toString());
                    bw.newLine();
                }
            }
            System.out.println("Relación eliminada...");
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void eliminarTodasRelacionesProfesor(int dniProfesor) {
        List<ProfesorMaterias> lista = leerRelaciones();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDProfesorMaterias.getARCHIVO()))) {
            for (ProfesorMaterias pm : lista) {
                if (pm.getDniProfesor() != dniProfesor) {
                    bw.write(pm.toString());
                    bw.newLine();
                }
            }
            System.out.println("Todas las relaciones del profesor eliminadas...");
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void eliminarTodasRelacionesMateria(int codMateria) {
        List<ProfesorMaterias> lista = leerRelaciones();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(BDProfesorMaterias.getARCHIVO()))) {
            for (ProfesorMaterias pm : lista) {
                if (pm.getCodMateria() != codMateria) {
                    bw.write(pm.toString());
                    bw.newLine();
                }
            }
            System.out.println("Todas las relaciones de la materia eliminadas...");
        } catch (IOException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
}
